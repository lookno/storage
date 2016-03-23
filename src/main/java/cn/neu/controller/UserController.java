package cn.neu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.neu.bean.User;
import cn.neu.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService iUserService;
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> register(@RequestBody User user) throws Exception {
		log.info("into UserController.register() , param: " + user);
		iUserService.register(user);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> login(@RequestBody User user) throws Exception {
		log.info("into UserController.login() , param: " + user);
		User user2 = iUserService.login(user);
		if(user2!=null){
			return new ResponseEntity<Object>(user2, HttpStatus.OK);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "用户名或密码错误");
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<Object> test() throws Exception {
		return new ResponseEntity<Object>("testOK", HttpStatus.OK);
	}
}
