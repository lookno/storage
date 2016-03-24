package cn.neu.vo;

import java.util.List;

import cn.neu.bean.Record;

public class RecordVo {
	private List<Record> records;
	private int page;// 表示当前页数
	private int count;// 表示页数的大小 比如 20 13

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
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
		return "RecordVo [records=" + records + ", page=" + page + ", count=" + count + "]";
	}

}
