package cn.neu.dto;

public class ChangePwdDto {
	private String username;
	private String password;
	private String vCode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	@Override
	public String toString() {
		return "ChangePwdDto [username=" + username + ", password=" + password + ", vCode=" + vCode + "]";
	}

}
