package cn.neu.dto;

public class SearchDto {
	private int type;// 搜索的类型
	private String key;// 关键字
	private int page;
	private int start;
	private int limit;
	private int pageSize;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SearchDto [type=" + type + ", key=" + key + ", page=" + page + ", start=" + start + ", limit=" + limit
				+ ", pageSize=" + pageSize + "]";
	}

}
