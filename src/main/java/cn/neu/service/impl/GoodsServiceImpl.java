package cn.neu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.neu.bean.Goods;
import cn.neu.bean.Record;
import cn.neu.dao.GoodsDao;
import cn.neu.dao.RecordDao;
import cn.neu.dto.GoodsDto;
import cn.neu.exception.ServerException;
import cn.neu.service.IGoodsService;

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
			if (goods.getType() == 0) {
				record.setComment("生产入库");
				record.setType(3);
			} else if (goods.getType() == 1) {
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
			record.setGoods_id(goods.getId());
			record.setComment("商品出库");
			record.setType(1);
			record.setPrice(goods.getPrice() * goods.getCount());
			recordDao.insertRecord(record);

		} catch (Exception e) {
			log.error("GoodsServiceImpl.updateGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public List<Goods> listGoods(GoodsDto goodsDto) throws ServerException {
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

}
