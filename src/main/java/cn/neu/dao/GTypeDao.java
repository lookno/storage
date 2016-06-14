package cn.neu.dao;

import java.util.List;
import cn.neu.bean.GType;

public interface GTypeDao {
	public List<GType> getGTypes();
	public GType getGTypeById(int id);
	public void addGType(GType gType);
	public void updateGType(GType GType);
}
