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
import cn.neu.bean.Goods;
import cn.neu.dto.GoodsDto;
import cn.neu.service.IGoodsService;
import cn.neu.vo.GoodsVo;

@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Resource
	private IGoodsService iGoodsService;
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> addGoods(@RequestBody Goods goods) throws Exception {
		log.info("into GoodsController.addGoods() , param: " + goods);
		iGoodsService.insertGoods(goods);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> modifyGoods(@RequestBody Goods goods) throws Exception {
		log.info("into GoodsController.modifyGoods() , param: " + goods);
		iGoodsService.updateGoods(goods);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> listGoods(@ModelAttribute GoodsDto goodsDto) throws Exception {
		log.info("into GoodsController.listGoods() , param: " + goodsDto);
		List<Goods> goodsList = iGoodsService.listGoods(goodsDto);
		GoodsVo goodsVo = new GoodsVo();
		goodsVo.setGoods(goodsList);
		goodsVo.setCount(goodsList.size());
		goodsVo.setPage(goodsDto.getPage());
		return new ResponseEntity<Object>(goodsVo, HttpStatus.OK);
	}
}
