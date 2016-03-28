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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.neu.bean.User;
import cn.neu.dto.ChangePwdDto;
import cn.neu.dto.EmailParamsDto;
import cn.neu.dto.TokenParamsDto;
import cn.neu.service.IEmailService;
import cn.neu.service.ITokenService;
import cn.neu.service.IUserService;
import cn.neu.utils.VerifyCodeUtil;
import cn.neu.utils.redis.IRedisClient;
import cn.neu.vo.UserVo;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService iUserService;
	@Resource
	private ITokenService iTokenService;
	@Resource
	private IEmailService iEmailService;
	@Resource
	private IRedisClient iRedisClient;
	private Logger log = Logger.getLogger(this.getClass());
	
	//不开放注册  只有super用户可以注册帐号
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> register(@RequestBody User user) throws Exception {
		log.info("into UserController.register() , param: " + user);
		iUserService.register(user);
		/*String token = UUID.randomUUID().toString();
		TokenParamsDto tpd = new TokenParamsDto();
		tpd.setUser_id(user.getId());
		tpd.setToken(token);
		tpd.setExpire_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000 * 1)));
		iTokenService.insertToken(tpd);
		UserVo uv = new UserVo();
		uv.setToken(token);
		uv.setUsername(user.getUsername());*/
		return new ResponseEntity<Object>(HttpStatus.OK);
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

	// 找回密码 验证用户信息 并发送验证码
	@RequestMapping(value = "/findpwd", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> findpwd(@RequestBody User user) throws Exception {
		log.info("into UserController.login() , param: " + user);
		int bool = iUserService.checkUserInfo(user);
		if (bool <= 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "用户信息错误");
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}
		// 将验证码存入redis中
		String vCode = VerifyCodeUtil.gen(6);

		if (!StringUtils.isEmpty(user.getEmail())) {

			Jedis jedis = iRedisClient.getJedis();
			jedis.setex(user.getUsername(), 60 * 3, vCode);// 设置有效时间为3分钟

			// 发送邮件验证码
			EmailParamsDto epd = new EmailParamsDto();
			epd.setToEmail(user.getEmail());
			epd.setTitle("找回密码");
			epd.setContent("您好,您的验证码为: " + vCode);
			iEmailService.sendEmail(epd);
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "邮件已发送");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} else if (!StringUtils.isEmpty(user.getPhone())) {
			// 发送短信验证码

			Jedis jedis = iRedisClient.getJedis();
			jedis.setex(user.getUsername(), 60 * 3, vCode);// 设置有效时间为3分钟

		}
		Map<String, Object> m = new HashMap<>();
		m.put("msg", "验证失败");
		return new ResponseEntity<Object>(m, HttpStatus.BAD_REQUEST);
	}

	// 验证用户验证码 如果正确 则重置密码
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> verify(@RequestBody ChangePwdDto changePwdDto) throws Exception {
		log.info("into UserController.verify() , param: " + changePwdDto);
		Jedis jedis = iRedisClient.getJedis();
		String vCodeInRedis = jedis.get(changePwdDto.getUsername());
		if (!StringUtils.isEmpty(vCodeInRedis)) {
			if (vCodeInRedis.equals(changePwdDto.getvCode())) {
				jedis.del(changePwdDto.getUsername());
				// 验证通过 修改密码
				User user = new User();
				user.setUsername(changePwdDto.getUsername());
				user.setPassword(changePwdDto.getPassword());
				iUserService.changePwd(user);
				Map<String, Object> map = new HashMap<>();
				map.put("msg", "重置密码成功,请重新登录");
				return new ResponseEntity<Object>(map, HttpStatus.OK);
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("msg", "验证码错误");
				return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "验证码已失效");
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}

	}
}
