package cn.neu.vo;

public class UserVo {
	private String username;
	private String token;
	private int permission;

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

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "UserVo [username=" + username + ", token=" + token + ", permission=" + permission + "]";
	}
}
