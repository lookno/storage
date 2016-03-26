package cn.neu.dao;

import java.util.List;

import cn.neu.bean.Goods;
import cn.neu.bean.OutputGoods;
import cn.neu.dto.GoodsDto;

public interface GoodsDao {
	public void insertGoods(Goods goods);
	public void updateGoods(Goods goods);
	public List<Goods> listGoods(GoodsDto goodsDto);
}
