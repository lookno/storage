package cn.neu.dto;

public class RecordDto {
	private int page;
	private int pageSize;
	private int start;
	private int limit;
	private int type; // 0所有 1 出库 2花销 3入库
	private String fileAddr;
	private String key;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFileAddr() {
		return fileAddr;
	}

	public void setFileAddr(String fileAddr) {
		this.fileAddr = fileAddr;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "RecordDto [page=" + page + ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit
				+ ", type=" + type + ", fileAddr=" + fileAddr + ", key=" + key + "]";
	}

}
