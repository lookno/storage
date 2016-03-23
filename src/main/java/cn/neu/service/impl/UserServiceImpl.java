package cn.neu.service.impl;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import cn.neu.bean.User;
import cn.neu.dao.UserDao;
import cn.neu.exception.ServerException;
import cn.neu.service.IUserService;

@Service
class UserServiceImpl implements IUserService {
	@Resource
	private UserDao userDao;
	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public void register(User user) throws ServerException {
		try {
			userDao.insert(user);
		} catch (Exception e) {
			log.error("U.erServiceImpl.register occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

	}

	@Override
	public User login(User user) throws ServerException {
		try {
			return userDao.select(user);
		} catch (Exception e) {
			log.error("U.erServiceImpl.login occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

	}

}
