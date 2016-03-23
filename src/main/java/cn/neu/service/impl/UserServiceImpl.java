package cn.neu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.neu.bean.User;
import cn.neu.dao.UserDao;
import cn.neu.service.IUserService;
@Service
class UserServiceImpl implements IUserService{
	@Resource
	private UserDao userDao;
	@Override
	public void register(User user){
		userDao.insert(user);
	}

}
