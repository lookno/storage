package cn.neu.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import cn.neu.bean.GType;
import cn.neu.bean.RType;
import cn.neu.dao.GTypeDao;
import cn.neu.dao.RTypeDao;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;
import cn.neu.service.ITypeService;

@Service
public class TypeServiceImpl implements ITypeService {
	@Resource
	private RTypeDao rTypeDao;
	@Resource
	private GTypeDao gTypeDao;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<GType> getGTypes() throws ServerException, ServiceException {
		List<GType> list = null;
		try {
			list = gTypeDao.getGTypes();
		} catch (Exception e) {
			log.error("TypeServiceImpl.getGTypes occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

		return list;
	}

	@Override
	public void addGType(GType gType) throws ServerException, ServiceException {
		try {
			gTypeDao.addGType(gType);
		} catch (Exception e) {
			log.error("TypeServiceImpl.addGType occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public List<RType> getRTypes() throws ServerException, ServiceException {
		List<RType> list = null;
		try {
			list = rTypeDao.getRTypes();
		} catch (Exception e) {
			log.error("TypeServiceImpl.getRTypes occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

		return list;
	}

	@Override
	public void addRType(RType rType) throws ServerException, ServiceException {
		try {
			rTypeDao.addRType(rType);
		} catch (Exception e) {
			log.error("TypeServiceImpl.addRType occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public void updateGType(GType GType) throws ServerException, ServiceException {
		try {
			gTypeDao.updateGType(GType);
		} catch (Exception e) {
			log.error("TypeServiceImpl.updateGType occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public void updateRType(RType rType) throws ServerException, ServiceException {
		try {
			rTypeDao.updateRType(rType);
		} catch (Exception e) {
			log.error("TypeServiceImpl.updateRType occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

	}

}
