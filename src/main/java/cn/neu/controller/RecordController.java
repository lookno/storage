package cn.neu.controller;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.neu.bean.Record;
import cn.neu.dto.RecordDto;
import cn.neu.service.IRecordService;
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
}
