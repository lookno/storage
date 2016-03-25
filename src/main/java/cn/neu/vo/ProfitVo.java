package cn.neu.vo;

import java.util.List;

import cn.neu.bean.Record;

public class ProfitVo {
	private List<Record> recordList;
	private double cost;// 花费
	private double earn;// 毛利 两个相减为profit
	private String s_time;
	private String e_time;

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getEarn() {
		return earn;
	}

	public void setEarn(double earn) {
		this.earn = earn;
	}

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

	@Override
	public String toString() {
		return "ProfitVo [recordList=" + recordList + ", cost=" + cost + ", earn=" + earn + ", s_time=" + s_time
				+ ", e_time=" + e_time + "]";
	}

}
