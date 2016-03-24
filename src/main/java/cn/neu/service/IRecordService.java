package cn.neu.service;

import java.util.List;

import cn.neu.bean.Goods;
import cn.neu.bean.Record;
import cn.neu.dto.GoodsDto;
import cn.neu.dto.RecordDto;
import cn.neu.exception.ServerException;

public interface IRecordService {
	public void insertRecord(Record record) throws ServerException;
	public void updateRecord(Record record) throws ServerException;
	public List<Record> listRecord(RecordDto recordDto) throws ServerException;
}
