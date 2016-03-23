package cn.neu.service.impl;

import cn.neu.bean.User;
import cn.neu.dao.IUserDao;
import cn.neu.service.IUserService;

class UserServiceImpl implements IUserService{
	private IUserDao iUserDao;
	@Override
	public void register(User user) {
		iUserDao.insert(user);
	}

}
