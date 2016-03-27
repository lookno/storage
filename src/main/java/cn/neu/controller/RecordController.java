package cn.neu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.neu.bean.OutputRecord;
import cn.neu.bean.Record;
import cn.neu.dto.GoodsDto;
import cn.neu.dto.OutputParamsDto;
import cn.neu.dto.ProfitParamsDto;
import cn.neu.dto.RecordDto;
import cn.neu.service.IRecordService;
import cn.neu.utils.CsvFileWriter;
import cn.neu.vo.ProfitVo;
import cn.neu.vo.RecordVo;

@RestController
@RequestMapping("/record")
public class RecordController {
	@Resource
	private IRecordService iRecordService;
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> addRecord(@RequestBody Record record) throws Exception {
		log.info("into RecordController.addRecord() , param: " + record);
		iRecordService.insertRecord(record);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> modifyRecord(@RequestBody Record record) throws Exception {
		log.info("into RecordController.modifyRecord() , param: " + record);
		iRecordService.updateRecord(record);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> listRecord(@ModelAttribute RecordDto recordDto) throws Exception {
		log.info("into RecordController.listRecord() , param: " + recordDto);
		System.out.println(recordDto);
		List<Record> recordList = iRecordService.listRecord(recordDto);
		RecordVo RecordVo = new RecordVo();
		RecordVo.setRecords(recordList);
		RecordVo.setCount(recordList.size());
		RecordVo.setPage(recordDto.getPage());
		return new ResponseEntity<Object>(RecordVo, HttpStatus.OK);
	}

	// time时间这样传localhost:8080/storage/record/profit?s_time=2016-01-01
	// 00:00:00&e_time=2017-01-01 00:00:00
	@RequestMapping(value = "/profit", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> profit(@ModelAttribute ProfitParamsDto profitParamsDto) throws Exception {
		log.info("into RecordController.profit() , param: " + profitParamsDto);
		List<Record> recordList = iRecordService.profit(profitParamsDto);
		ProfitVo profitVo = new ProfitVo();
		profitVo.setRecordList(recordList);
		profitVo.setE_time(profitParamsDto.getE_time());
		profitVo.setS_time(profitParamsDto.getS_time());
		double cost = 0, earn = 0;
		for (Record r : recordList) {
			if (r.getType() == 1) {// 出库
				earn += r.getPrice();
			}
			if (r.getType() == 2) {// 花销
				cost += r.getPrice();
			}
		}
		profitVo.setCost(cost);
		profitVo.setEarn(earn);
		return new ResponseEntity<Object>(profitVo, HttpStatus.OK);
	}

	@RequestMapping(value = "/output", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> output(@ModelAttribute OutputParamsDto outputParamsDto) throws Exception {
		log.info("into RecordController.profit() , param: " + outputParamsDto);
		List<OutputRecord> outputList = iRecordService.output(outputParamsDto);
		String address = outputParamsDto.getFileAddr() == null
				? "c:\\" + new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + "-账务记录.csv"
				: outputParamsDto.getFileAddr() + new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + "-账务记录.csv";
		CsvFileWriter.writeCsvFile(outputList, CsvFileWriter.OUTPUT_RECORD_FILE_HEADER, address);
		Map<String, String> map = new HashMap<>();
		map.put("msg", "导出csv文件成功");
		map.put("location", address);
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/input", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> input(@ModelAttribute RecordDto recordDto) throws Exception {
		log.info("into RecordController.input() , param: " + recordDto);
		iRecordService.batchInsertRecords(recordDto.getFileAddr());
		Map<String, String> map = new HashMap<>();
		map.put("msg", "导入csv文件成功");
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
