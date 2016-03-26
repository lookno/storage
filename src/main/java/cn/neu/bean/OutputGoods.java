package cn.neu.bean;

public class OutputGoods {
	private String name;
	private int count;
	private String typeName;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OutputGoods [name=" + name + ", count=" + count + ", typeName=" + typeName + ", price=" + price + "]";
	}

}
