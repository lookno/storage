package cn.neu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.neu.bean.Goods;
import cn.neu.bean.OutputGoods;
import cn.neu.bean.OutputRecord;
import cn.neu.bean.Record;
import cn.neu.dao.GoodsDao;
import cn.neu.dao.RecordDao;
import cn.neu.dto.GoodsDto;
import cn.neu.dto.OutputParamsDto;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;
import cn.neu.service.IGoodsService;
import cn.neu.utils.CsvFileWriter;

@Service
public class GoodsServiceImpl implements IGoodsService {
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private RecordDao recordDao;
	private Logger log = Logger.getLogger(this.getClass());

	// 入库
	@Override
	public void insertGoods(Goods goods) throws ServerException {
		try {
			goodsDao.insertGoods(goods);

			Record record = new Record();
			record.setGoods_id(goods.getId());
			if (goods.getType() == 1) {
				record.setComment("生产入库");
				record.setType(3);
			} else if (goods.getType() == 2) {
				record.setComment("购买入库");
				record.setType(2);
				record.setPrice(goods.getPrice() * goods.getCount());
			}
			recordDao.insertRecord(record);

		} catch (Exception e) {
			log.error("GoodsServiceImpl.insertGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

	}

	// 出库
	@Override
	public void updateGoods(Goods goods) throws ServerException {
		try {
			goodsDao.updateGoods(goods);

			Record record = new Record();
			if (goods.getCount() < 0) {
				record.setGoods_id(goods.getId());
				record.setComment("商品出库");
				record.setType(1);
				record.setPrice(-goods.getPrice() * goods.getCount());
			} else {
				record.setGoods_id(goods.getId());
				record.setComment("生产入库");
				record.setType(3);
			}

			recordDao.insertRecord(record);

		} catch (Exception e) {
			log.error("GoodsServiceImpl.updateGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public List<Goods> listGoods(GoodsDto goodsDto) throws ServerException {
		if (goodsDto.getPage() <= 0)
			goodsDto.setPage(1);
		if (goodsDto.getPageSize() <= 0)
			goodsDto.setPageSize(20);
		goodsDto.setStart((goodsDto.getPage() - 1) * goodsDto.getPageSize());
		goodsDto.setLimit(goodsDto.getPageSize());
		List<Goods> goodsList = null;
		try {
			goodsList = goodsDao.listGoods(goodsDto);
		} catch (Exception e) {
			log.error("GoodsServiceImpl.listGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

		return goodsList;
	}

	@Override
	public List<OutputGoods> output(GoodsDto goodsDto) throws ServerException {
		List<Goods> outputs = null;
		try {
			outputs = goodsDao.listGoods(goodsDto);
		} catch (Exception e) {
			log.error("GoodsServiceImpl.listGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
		return transGoodsToOPGS(outputs);
	}

	private List<OutputGoods> transGoodsToOPGS(List<Goods> outputs) {
		List<OutputGoods> opgs = new ArrayList<>();
		for (Goods g : outputs) {
			OutputGoods opg = new OutputGoods();
			opg.setId(g.getId());
			opg.setCount(g.getCount());
			opg.setName(g.getName());
			opg.setPrice(g.getPrice());
			if (g.getType() == 1) {
				opg.setTypeName("生产的商品");
			} else if (g.getType() == 2) {
				opg.setTypeName("购入的商品");
			}
			opgs.add(opg);
		}
		return opgs;
	}

	@Override
	public void batchInsertGoods(String fileName) throws ServerException, ServiceException {
		List<Goods> list = null;
		try {
			list = CsvFileWriter.readGoods(fileName);
		} catch (ServiceException e1) {
			throw e1;
		} catch (Exception e2) {
			throw new ServerException("读取数据失败", e2);
		}

		goodsDao.batchInsertGoods(list);
	}

}
