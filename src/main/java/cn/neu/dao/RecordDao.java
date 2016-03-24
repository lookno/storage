package cn.neu.dao;

import java.util.List;

import cn.neu.bean.Goods;
import cn.neu.bean.Record;
import cn.neu.dto.GoodsDto;
import cn.neu.dto.RecordDto;

public interface RecordDao {
	public void insertRecord(Record record);
	public void updateRecord(Record record);
	public List<Record> listRecord(RecordDto recordDto);
}
