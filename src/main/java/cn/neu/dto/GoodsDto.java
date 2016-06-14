package cn.neu.dto;

public class GoodsDto {
	private int page;
	private int pageSize;
	private int start;
	private int limit;
	private int type; // 0代表列出生产的商品 1表示列出购买的商品
	private boolean listZero;// 表示是否列出数量为0的商品
	private String fileAddr;
	private String name;
	private int id;
	private String key;// 关键字

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

	public boolean isListZero() {
		return listZero;
	}

	public void setListZero(boolean listZero) {
		this.listZero = listZero;
	}

	public String getFileAddr() {
		return fileAddr;
	}

	public void setFileAddr(String fileAddr) {
		this.fileAddr = fileAddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "GoodsDto [page=" + page + ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit + ", type="
				+ type + ", listZero=" + listZero + ", fileAddr=" + fileAddr + ", name=" + name + ", id=" + id
				+ ", key=" + key + "]";
	}

}
