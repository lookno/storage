package cn.neu.dao;

import java.util.List;
import cn.neu.bean.Goods;
import cn.neu.dto.GoodsDto;

public interface GoodsDao {
	public void insertGoods(Goods goods);

	public void updateGoods(Goods goods);

	public List<Goods> listGoods(GoodsDto goodsDto);

	public void batchInsertGoods(List<Goods> list);
	
	public Integer getTotalNum(GoodsDto goodsDto);
	
	public Goods getGoodsById(int id);
	//public List<Goods> searchGoods(SearchDto searchDto);
	
	//public Integer getSearchCount(SearchDto searchDto);
}
