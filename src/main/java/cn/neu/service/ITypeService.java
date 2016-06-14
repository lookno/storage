package cn.neu.service;

import java.util.List;
import cn.neu.bean.GType;
import cn.neu.bean.RType;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;

public interface ITypeService {
	public List<GType> getGTypes() throws ServerException, ServiceException;

	public void addGType(GType gType) throws ServerException, ServiceException;;

	public List<RType> getRTypes() throws ServerException, ServiceException;

	public void addRType(RType rType) throws ServerException, ServiceException;

	public void updateGType(GType GType) throws ServerException, ServiceException;

	public void updateRType(RType rType) throws ServerException, ServiceException;
}
