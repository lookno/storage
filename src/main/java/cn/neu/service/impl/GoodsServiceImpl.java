package cn.neu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.neu.bean.Goods;
import cn.neu.dao.GoodsDao;
import cn.neu.dto.GoodsDto;
import cn.neu.exception.ServerException;
import cn.neu.service.IGoodsService;

@Service
public class GoodsServiceImpl implements IGoodsService {
	@Resource
	private GoodsDao goodsDao;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void insertGoods(Goods goods) throws ServerException {
		try {
			goodsDao.insertGoods(goods);
		} catch (Exception e) {
			log.error("GoodsServiceImpl.insertGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

	}

	@Override
	public void updateGoods(Goods goods) throws ServerException {
		try {
			goodsDao.updateGoods(goods);

		} catch (Exception e) {
			log.error("GoodsServiceImpl.updateGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public List<Goods> listGoods(GoodsDto goodsParams) throws ServerException {
		goodsParams.setStart((goodsParams.getPage() - 1) * goodsParams.getPageSize());
		goodsParams.setLimit(goodsParams.getPageSize());
		List<Goods> goodsList = null;
		try {
			goodsList = goodsDao.listGoods(goodsParams);
		} catch (Exception e) {
			log.error("GoodsServiceImpl.listGoods occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

		return goodsList;
	}

}
