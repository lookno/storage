package cn.neu.dao;

import cn.neu.bean.User;

public interface UserDao {
	//注册用户
	public void insert(User user);
	public User select(User user);
}
