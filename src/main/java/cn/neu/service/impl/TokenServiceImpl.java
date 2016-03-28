package cn.neu.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.neu.dao.TokenDao;
import cn.neu.dto.TokenParamsDto;
import cn.neu.exception.ServerException;
import cn.neu.service.ITokenService;

@Service
public class TokenServiceImpl implements ITokenService {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private TokenDao tokenDao;

	@Override
	public void insertToken(TokenParamsDto params) throws ServerException {
		try {
			tokenDao.insertToken(params);
		} catch (Exception e) {
			log.error("TokenServiceImpl.insert occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public int ifTokenValid(TokenParamsDto params) throws ServerException {
		int i;
		try {
			i = tokenDao.ifTokenValid(params);
		} catch (Exception e) {
			log.error("TokenServiceImpl.ifTokenValid occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
		return i;
	}

	@Override
	public void inValid(TokenParamsDto params) throws ServerException {
		try {
			tokenDao.inValid(params);
		} catch (Exception e) {
			log.error("TokenServiceImpl.inValid occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

	}

}
