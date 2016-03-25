package cn.neu.dto;

public class ProfitParamsDto {
	private String s_time;
	private String e_time;
	private int goods_id;
	private int type;

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String e_time) {
		this.e_time = e_time;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProfitParamsDto [s_time=" + s_time + ", e_time=" + e_time + ", goods_id=" + goods_id + ", type=" + type
				+ "]";
	}

}
