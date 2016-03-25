package cn.neu.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import cn.neu.bean.Record;
import cn.neu.dao.RecordDao;
import cn.neu.dto.ProfitParamsDto;
import cn.neu.dto.RecordDto;
import cn.neu.exception.ServerException;
import cn.neu.service.IRecordService;

@Service
public class RecordServiceImpl implements IRecordService {
	@Resource
	private RecordDao recordDao;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void insertRecord(Record record) throws ServerException {
		try {
			recordDao.insertRecord(record);
		} catch (Exception e) {
			log.error("RecordServiceImpl.insertRecord occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public void updateRecord(Record record) throws ServerException {
		try {
			recordDao.updateRecord(record);
		} catch (Exception e) {
			log.error("RecordServiceImpl.updateRecord occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	public List<Record> listRecord(RecordDto recordDto) throws ServerException {
		recordDto.setStart((recordDto.getPage() - 1) * recordDto.getPageSize());
		recordDto.setLimit(recordDto.getPageSize());
		List<Record> recordList = null;
		try {
			recordList = recordDao.listRecord(recordDto);
		} catch (Exception e) {
			log.error("RecordServiceImpl.listRecord occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
		return recordList;
	}

	@Override
	public List<Record> profit(ProfitParamsDto profitParamsDto) throws ServerException {
		List<Record> records = null;
		try {
			records = recordDao.profit(profitParamsDto);
		} catch (Exception e) {
			log.error("RecordServiceImpl.profit occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
		
		return records;
	}

}
