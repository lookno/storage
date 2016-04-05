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
import cn.neu.bean.Goods;
import cn.neu.bean.OutputGoods;
import cn.neu.bean.OutputRecord;
import cn.neu.dto.GoodsDto;
import cn.neu.dto.OutputParamsDto;
import cn.neu.service.IGoodsService;
import cn.neu.utils.CsvFileWriter;
import cn.neu.vo.GoodsVo;

@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Resource
	private IGoodsService iGoodsService;
	private Logger log = Logger.getLogger(this.getClass());

	// 新增入库 添加新商品时使用这个接口
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> addGoods(@RequestBody Goods goods) throws Exception {
		log.info("into GoodsController.addGoods() , param: " + goods);
		System.out.println(goods);
		iGoodsService.insertGoods(goods);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	// 出库 和对已存在商品进行入库 使用这个接口
	// 出库时 把goods.count写为负数
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> modifyGoods(@RequestBody Goods goods) throws Exception {
		log.info("into GoodsController.modifyGoods() , param: " + goods);
		iGoodsService.updateGoods(goods);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	// page 和pageSize默认为1和20 type不传即查所有
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> listGoods(@ModelAttribute GoodsDto goodsDto) throws Exception {
		log.info("into GoodsController.listGoods() , param: " + goodsDto);
		List<Goods> goodsList = iGoodsService.listGoods(goodsDto);
		Integer count = iGoodsService.getTotalNum(goodsDto);
		GoodsVo goodsVo = new GoodsVo();
		goodsVo.setGoods(goodsList);
		goodsVo.setCount(count==null?0:count);
		goodsVo.setPage(goodsDto.getPage());
		return new ResponseEntity<Object>(goodsVo, HttpStatus.OK);
	}

	@RequestMapping(value = "/output", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> output(@ModelAttribute GoodsDto goodsDto) throws Exception {
		log.info("into GoodsController.output() , param: " + goodsDto);
		List<OutputGoods> outputList = iGoodsService.output(goodsDto);
		String address = goodsDto.getFileAddr() == null
				? "c:\\" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-库存记录.csv"
				: goodsDto.getFileAddr();
		CsvFileWriter.writeCsvFile(outputList, CsvFileWriter.GOODS_FILE_HEADER, address);
		Map<String, String> map = new HashMap<>();
		map.put("msg", "导出csv文件成功");
		map.put("location", address);
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> input(@ModelAttribute GoodsDto goodsDto) throws Exception {
		log.info("into GoodsController.input() , param: " + goodsDto);
		iGoodsService.batchInsertGoods(goodsDto.getFileAddr());
		Map<String, String> map = new HashMap<>();
		map.put("msg", "导入csv文件成功");
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
