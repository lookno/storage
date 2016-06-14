package cn.neu.dao;

import java.util.List;
import cn.neu.bean.RType;

public interface RTypeDao {
	public List<RType> getRTypes();
	public void addRType(RType rType);
	public void updateRType(RType rType);
}
