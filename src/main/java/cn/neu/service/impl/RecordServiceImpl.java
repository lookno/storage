package cn.neu.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.neu.bean.OutputRecord;
import cn.neu.bean.Record;
import cn.neu.dao.GoodsDao;
import cn.neu.dao.RecordDao;
import cn.neu.dto.OutputParamsDto;
import cn.neu.dto.ProfitParamsDto;
import cn.neu.dto.RecordDto;
import cn.neu.exception.ServerException;
import cn.neu.exception.ServiceException;
import cn.neu.service.IRecordService;
import cn.neu.utils.CsvFileWriter;

@Service
public class RecordServiceImpl implements IRecordService {
	@Resource
	private RecordDao recordDao;
	@Resource
	private GoodsDao goodsDao;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertRecord(Record record) throws ServerException {
		try {
			recordDao.insertRecord(record);
		} catch (Exception e) {
			log.error("RecordServiceImpl.insertRecord occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
		if (recordDto.getPage() <= 0)
			recordDto.setPage(1);
		if (recordDto.getPageSize() <= 0)
			recordDto.setPageSize(16);
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
	public Integer getTotalNum(RecordDto recordDto) throws ServerException, ServiceException {
		Integer count = 0;
		try {
			count = recordDao.getTotalNum(recordDto);
		} catch (Exception e) {
			log.error("RecordServiceImpl.getTotalNum occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

		return count;
	}

/*	@Override
	public List<Record> searchRecord(SearchDto searchDto) throws ServerException, ServiceException {
		if (searchDto.getPage() <= 0)
			searchDto.setPage(1);
		if (searchDto.getPageSize() <= 0)
			searchDto.setPageSize(16);
		searchDto.setStart((searchDto.getPage() - 1) * searchDto.getPageSize());
		searchDto.setLimit(searchDto.getPageSize());
		List<Record> recordList = null;
		try {
			recordList = recordDao.searchRecord(searchDto);
		} catch (Exception e) {
			log.error("RecordServiceImpl.searchRecord occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
		return recordList;
	}

	@Override
	public Integer getSearchCount(SearchDto searchDto) throws ServerException, ServiceException {
		Integer count = 0;
		try {
			count = recordDao.getSearchCount(searchDto);
		} catch (Exception e) {
			log.error("RecordServiceImpl.getSearchCount occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}

		return count;
	}*/

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

	@Override
	public List<OutputRecord> output(OutputParamsDto outputParamsDto) throws ServerException {
		List<OutputRecord> outputs = null;
		try {
			outputs = recordDao.output(outputParamsDto);
		} catch (Exception e) {
			log.error("RecordServiceImpl.output occurs an Exception: ", e);
			throw new ServerException("数据库异常,请稍后再试", e);
		}
		for (OutputRecord or : outputs) {
			if (or.getType() == 0) {
				or.setTypeName("与商品出入库无关记录");
			} else if (or.getType() == 1) {
				or.setTypeName("销售出库记录");
			} else if (or.getType() == 2) {
				or.setTypeName("购入花销记录");
			} else if (or.getType() == 3) {
				or.setTypeName("生产入库记录");
			} else if (or.getType() == 4) {
				or.setTypeName("修改商品价格记录");
			}
		}
		return outputs;
	}

	@Override
	public void batchInsertRecords(String fileName) throws ServerException, ServiceException {
		List<Record> list = null;
		try {
			list = CsvFileWriter.readRecords(fileName, goodsDao);
		} catch (ServiceException e1) {
			throw e1;
		} catch (Exception e2) {
			throw new ServerException("读取数据失败", e2);
		}

		recordDao.batchInsertRecords(list);
	}

}
