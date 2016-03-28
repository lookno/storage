package cn.neu.dto;

public class TokenParamsDto {
	private String token;
	private int user_id;
	private String expire_time;
	private String current_time;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}

	public String getCurrent_time() {
		return current_time;
	}

	public void setCurrent_time(String current_time) {
		this.current_time = current_time;
	}

	@Override
	public String toString() {
		return "TokenParamsDto [token=" + token + ", user_id=" + user_id + ", expire_time=" + expire_time
				+ ", current_time=" + current_time + "]";
	}

}
