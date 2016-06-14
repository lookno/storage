package cn.neu.bean;

public class RType {
	private int id;
	private String r_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	@Override
	public String toString() {
		return "RType [id=" + id + ", r_name=" + r_name + "]";
	}

}
