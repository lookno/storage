package cn.neu.service;

import cn.neu.bean.User;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;

public interface IUserService {
	public void register(User user) throws ServerException,ServiceException;
	public User login(User user) throws ServerException;
	public int checkUserInfo(User user) throws ServerException;
	public void changePwd(User user) throws ServerException;
	public int checkPermission(User user) throws ServerException;
	public User getUserById(int id) throws ServerException;
	public User getUserByName(String username) throws ServerException;
}
