package cn.neu.vo;

public class UserVo {
	private String username;
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserVo [username=" + username + ", token=" + token + "]";
	}

}
