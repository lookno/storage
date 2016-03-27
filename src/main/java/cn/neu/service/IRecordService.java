package cn.neu.service;

import java.util.List;

import cn.neu.bean.OutputRecord;
import cn.neu.bean.Record;
import cn.neu.dto.OutputParamsDto;
import cn.neu.dto.ProfitParamsDto;
import cn.neu.dto.RecordDto;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;

public interface IRecordService {
	public void insertRecord(Record record) throws ServerException;

	public void updateRecord(Record record) throws ServerException;

	public List<Record> listRecord(RecordDto recordDto) throws ServerException;

	public List<Record> profit(ProfitParamsDto profitParamsDto) throws ServerException;

	public List<OutputRecord> output(OutputParamsDto outputParamsDto) throws ServerException;

	public void batchInsertRecords(String fileName) throws ServerException, ServiceException;
}
