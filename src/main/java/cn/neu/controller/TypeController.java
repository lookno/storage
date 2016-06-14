package cn.neu.controller;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.neu.bean.GType;
import cn.neu.bean.RType;
import cn.neu.service.ITypeService;

@RestController
@RequestMapping("/type")
public class TypeController {
	@Resource
	private ITypeService iTypeService;
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/g_add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> addGType(@RequestBody GType gType) throws Exception {
		log.info("into TypeController.addGType() , param: " + gType);
		iTypeService.addGType(gType);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/r_add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> addRType(@RequestBody RType rType) throws Exception {
		log.info("into TypeController.addRType() , param: " + rType);
		iTypeService.addRType(rType);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/g_list", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> listGType() throws Exception {
		log.info("into TypeController.listGType() , no param");
		List<GType> lists = iTypeService.getGTypes();
		return new ResponseEntity<Object>(lists, HttpStatus.OK);
	}

	@RequestMapping(value = "/r_list", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> listRType() throws Exception {
		log.info("into TypeController.listRType() , no param");
		List<RType> lists = iTypeService.getRTypes();
		return new ResponseEntity<Object>(lists, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/g_edit", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateGType(@RequestBody GType gType) throws Exception {
		log.info("into TypeController.updateGType() ,param: " + gType);
		iTypeService.updateGType(gType);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/r_edit", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateRType(@RequestBody RType rType) throws Exception {
		log.info("into TypeController.updateRType() ,param: " + rType);
		iTypeService.updateRType(rType);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
