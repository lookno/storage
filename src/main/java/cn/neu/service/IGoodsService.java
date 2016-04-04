package cn.neu.service;

import java.util.List;
import cn.neu.bean.Goods;
import cn.neu.bean.OutputGoods;
import cn.neu.dto.GoodsDto;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;

public interface IGoodsService {
	public void insertGoods(Goods goods) throws ServerException;

	public void updateGoods(Goods goods) throws ServerException;

	public List<Goods> listGoods(GoodsDto goodsParams) throws ServerException;

	public List<OutputGoods> output(GoodsDto goodsDto) throws ServerException;

	public void batchInsertGoods(String fileName) throws ServerException, ServiceException;
	
	public Integer getTotalNum(GoodsDto goodsDto) throws ServerException, ServiceException;
}
