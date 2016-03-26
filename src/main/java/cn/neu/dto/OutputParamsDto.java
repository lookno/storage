package cn.neu.dto;

public class OutputParamsDto {
	private int type;
	private String fileAddr;

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

	@Override
	public String toString() {
		return "OutputParamsDto [type=" + type + ", fileAddr=" + fileAddr + "]";
	}

}
