package cn.neu.dto;

public class ChgPwdDto {
	public String oldPass;
	public String newPass;

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Override
	public String toString() {
		return "ChgPwdDto [oldPass=" + oldPass + ", newPass=" + newPass + "]";
	}

}
