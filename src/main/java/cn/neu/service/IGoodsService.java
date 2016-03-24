package cn.neu.service;

import java.util.List;

import cn.neu.bean.Goods;
import cn.neu.dto.GoodsDto;
import cn.neu.exception.ServerException;

public interface IGoodsService {
	public void insertGoods(Goods goods) throws ServerException;
	public void updateGoods(Goods goods) throws ServerException;
	public List<Goods> listGoods(GoodsDto goodsParams) throws ServerException;
}
