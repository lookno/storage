package cn.neu.bean;

public class GType {
	private int id;
	private String g_name;
	private int when_in;
	private int when_out;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public int getWhen_in() {
		return when_in;
	}

	public void setWhen_in(int when_in) {
		this.when_in = when_in;
	}

	public int getWhen_out() {
		return when_out;
	}

	public void setWhen_out(int when_out) {
		this.when_out = when_out;
	}

	@Override
	public String toString() {
		return "GType [id=" + id + ", g_name=" + g_name + ", when_in=" + when_in + ", when_out=" + when_out + "]";
	}

}
