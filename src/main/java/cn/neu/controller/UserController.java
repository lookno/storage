package cn.neu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.neu.bean.User;
import cn.neu.dto.TokenParamsDto;
import cn.neu.service.ITokenService;
import cn.neu.service.IUserService;
import cn.neu.vo.UserVo;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService iUserService;
	@Resource
	private ITokenService iTokenService;
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> register(@RequestBody User user) throws Exception {
		log.info("into UserController.register() , param: " + user);
		iUserService.register(user);
		String token = UUID.randomUUID().toString();
		TokenParamsDto tpd = new TokenParamsDto();
		tpd.setUser_id(user.getId());
		tpd.setToken(token);
		tpd.setExpire_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000 * 1)));
		iTokenService.insertToken(tpd);
		UserVo uv = new UserVo();
		uv.setToken(token);
		uv.setUsername(user.getUsername());
		return new ResponseEntity<Object>(uv, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> login(@RequestBody User user) throws Exception {
		log.info("into UserController.login() , param: " + user);
		User user2 = iUserService.login(user);
		if (user2 != null) {
			String token = UUID.randomUUID().toString();
			TokenParamsDto tpd = new TokenParamsDto();
			tpd.setUser_id(user2.getId());
			tpd.setToken(token);
			tpd.setExpire_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000 * 1)));
			iTokenService.insertToken(tpd);
			UserVo uv = new UserVo();
			uv.setToken(token);
			uv.setUsername(user2.getUsername());
			return new ResponseEntity<Object>(uv, HttpStatus.OK);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "用户名或密码错误");
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

	// 找回密码
	@RequestMapping(value = "/findpwd", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> findpwd(@RequestBody User user) throws Exception {
		log.info("into UserController.login() , param: " + user);
		User user2 = iUserService.login(user);
		if (user2 != null) {
			return new ResponseEntity<Object>(user2, HttpStatus.OK);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "用户名或密码错误");
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> logout(HttpServletRequest request) throws Exception {
		log.info("into UserController.logout() ,no param: ");
		String token = request.getHeader("HTTP_TOKEN");
		TokenParamsDto tpd = new TokenParamsDto();
		tpd.setToken(token);
		iTokenService.inValid(tpd);
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "退出成功");
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<Object> test() throws Exception {
		return new ResponseEntity<Object>("testOK", HttpStatus.OK);
	}
}
