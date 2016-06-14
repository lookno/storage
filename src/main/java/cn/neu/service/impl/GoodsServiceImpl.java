package cn.neu.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.neu.bean.GType;
import cn.neu.bean.Goods;
import cn.neu.bean.OutputGoods;
import cn.neu.bean.Record;
import cn.neu.dao.GTypeDao;
import cn.neu.dao.GoodsDao;
import cn.neu.dao.RecordDao;
import cn.neu.dto.GoodsDto;
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
	@Resource
	private GTypeDao gTypeDao;
	private Logger log = Logger.getLogger(this.getClass());

	// 入库
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void insertGoods(Goods goods) throws ServerException, ServiceException {
		try {
			goodsDao.insertGoods(goods);
			
			GType gt = gTypeDao.getGTypeById(goods.getType());

			Record record = new Record();
			record.setComment(gt.getG_name() + " 入库");
			record.setGoods_id(goods.getId());
			record.setType(2);
			record.setMode(gt.getWhen_in());
			if (gt.getWhen_in() != 0) {
				record.setPrice(goods.getPrice() * goods.getCount());
			}

			recordDao.insertRecord(record);

		} catch (Exception e) {
			log.error("GoodsServiceImpl.insertGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

	}

	// 入库/出库/修改商品
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateGoods(Goods goods) throws ServerException {
		try {
			goodsDao.updateGoods(goods);
			Goods goods2 = goodsDao.getGoodsById(goods.getId());
			GType gt = gTypeDao.getGTypeById(goods2.getType());
			Record record = new Record();
			record.setGoods_id(goods.getId());
			
			if (goods.getCount() < 0) {
				record.setMode(gt.getWhen_out());
				record.setComment(gt.getG_name() + " 出库");
				record.setType(1);
				if (gt.getWhen_out() != 0) {
					record.setPrice(Math.abs(goods2.getPrice() * goods.getCount()));
				}

			} else if (goods.getCount() > 0) {
				record.setMode(gt.getWhen_in());
				record.setComment(gt.getG_name() + " 入库");
				record.setType(2);
				if (gt.getWhen_in() != 0) {
					record.setPrice(Math.abs(goods2.getPrice() * goods.getCount()));
				}

			} else if (goods.getCount() == 0) {
				if (goods.getPrice() > 0) {
					record.setComment("修改商品价格为:" + goods.getPrice());
					record.setType(3);
				}
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
			goodsDto.setPageSize(16);
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
	public Integer getTotalNum(GoodsDto goodsDto) throws ServerException, ServiceException {
		int count;
		try {
			count = goodsDao.getTotalNum(goodsDto);
		} catch (Exception e) {
			log.error("GoodsServiceImpl.getTotalNum occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
		return count;
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

	/*
	 * @Override public List<Goods> searchGoods(SearchDto searchDto) throws
	 * ServerException, ServiceException { if (searchDto.getPage() <= 0)
	 * searchDto.setPage(1); if (searchDto.getPageSize() <= 0)
	 * searchDto.setPageSize(16); searchDto.setStart((searchDto.getPage() - 1) *
	 * searchDto.getPageSize()); searchDto.setLimit(searchDto.getPageSize());
	 * List<Goods> goodsList = null; try { goodsList =
	 * goodsDao.searchGoods(searchDto); } catch (Exception e) { log.error(
	 * "GoodsServiceImpl.searchGoods occurs an Exception: ", e); throw new
	 * ServerException("数据库异常,请稍后再试", e); }
	 * 
	 * return goodsList; }
	 * 
	 * @Override public Integer getSearchCount(SearchDto searchDto) throws
	 * ServerException, ServiceException { int count; try { count =
	 * goodsDao.getSearchCount(searchDto); } catch (Exception e) { log.error(
	 * "GoodsServiceImpl.getSearchCount occurs an Exception: ", e); throw new
	 * ServerException("数据库异常,请稍后再试", e); } return count; }
	 */

}
