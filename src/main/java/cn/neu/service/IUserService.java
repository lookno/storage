package cn.neu.service;

import cn.neu.bean.User;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;

public interface IUserService {
	public void register(User user) throws ServerException;
	public User login(User user) throws ServerException;
	
}
