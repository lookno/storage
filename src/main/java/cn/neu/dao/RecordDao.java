package cn.neu.dao;

import java.util.List;
import cn.neu.bean.OutputRecord;
import cn.neu.bean.Record;
import cn.neu.dto.GoodsDto;
import cn.neu.dto.OutputParamsDto;
import cn.neu.dto.ProfitParamsDto;
import cn.neu.dto.RecordDto;

public interface RecordDao {
	public void insertRecord(Record record);

	public void updateRecord(Record record);

	public List<Record> listRecord(RecordDto recordDto);

	public List<Record> profit(ProfitParamsDto profitParamsDto);

	public List<OutputRecord> output(OutputParamsDto outputParamsDto);

	public void batchInsertRecords(List<Record> list);

	public Integer getTotalNum(RecordDto RecordDto);
}
