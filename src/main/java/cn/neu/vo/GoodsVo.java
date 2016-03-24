package cn.neu.vo;

import java.util.List;

import cn.neu.bean.Goods;

public class GoodsVo {
	private List<Goods> goods;
	private int page;// 表示当前页数
	private int count;// 表示页数的大小 比如 20 13

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "GoodsVo [goods=" + goods + ", page=" + page + ", count=" + count + "]";
	}

}
