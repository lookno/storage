package cn.neu.filter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import cn.neu.bean.User;
import cn.neu.dto.TokenParamsDto;
import cn.neu.exception.ServerException;
import cn.neu.service.ITokenService;
import cn.neu.service.IUserService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter implements Filter {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private ITokenService iTokenService;
	@Resource
	private IUserService iUserService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		String uri = hRequest.getRequestURI();
		System.out.println(uri);
		if (uri.startsWith("/storage/user/login") || uri.startsWith("/storage/user/findpwd")
				|| uri.startsWith("/storage/user/changepwd")) {
			chain.doFilter(request, hresponse);
			return;
		} else {
			String token = hRequest.getHeader("HTTP_TOKEN");
			if (token == null) {
				hresponse.setContentType("application/json");
				hresponse.getWriter().write("{\"msg\":\"请登录\"}");
				return;
			} else {
				// 验证token
				TokenParamsDto tpd = new TokenParamsDto();
				tpd.setToken(token);
				tpd.setCurrent_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				int bool = 0;
				try {
					bool = iTokenService.ifTokenValid(tpd);
				} catch (ServerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (bool <= 0) {
					hresponse.setContentType("application/json");
					hresponse.getWriter().write("{\"msg\":\"token过期，请重新登录\"}");
					return;
				} else {
					// 验证为登录合法用户
					// 判断路径
					if (onlyRead(uri)) {// 如果只是查看数据 不修改的话 或者是修改密码 或者是logout 放行
						chain.doFilter(request, hresponse);
						return;
					} else {
						// 查看权限
						int user_id = 0;
						try {
							user_id = iTokenService.getUserIdByToken(token);
						} catch (ServerException e) {
							hresponse.setContentType("application/json");
							hresponse.setStatus(500);
							hresponse.getWriter().write("{\"msg\":\"服务器异常,请稍后再试\"}");
							return;
						}

						User user = new User();
						user.setId(user_id);
						int permission = 0;
						try {
							permission = iUserService.checkPermission(user);
						} catch (ServerException e) {
							hresponse.setContentType("application/json");
							hresponse.setStatus(500);
							hresponse.getWriter().write("{\"msg\":\"服务器异常,请稍后再试\"}");
							return;
						}

						if (isNormalWrite(uri)) {
							if (permission == 1 || permission == 2) {
								chain.doFilter(hRequest, hresponse);
								return;
							} else {
								hresponse.setContentType("application/json");
								hresponse.setStatus(500);
								hresponse.getWriter().write("{\"msg\":\"权限不足,无法操作\"}");
								return;
							}
						}
						if (isSuperWrite(uri)) {
							if (permission == 1) {
								chain.doFilter(hRequest, hresponse);
								return;
							} else {
								hresponse.setContentType("application/json");
								hresponse.setStatus(500);
								hresponse.getWriter().write("{\"msg\":\"权限不足,无法操作\"}");
								return;
							}
						}
						
					}

				}
			}
		}

	}

	private boolean onlyRead(String uri) {
		return uri.startsWith("/storage/goods/list") || uri.startsWith("/storage/goods/output")
				|| uri.startsWith("/storage/record/list") || uri.startsWith("/storage/record/profit")
				|| uri.startsWith("/storage/record/output") || uri.startsWith("/storage/user/logout");
	}

	private boolean isNormalWrite(String uri) {
		return uri.startsWith("/storage/goods/add") || uri.startsWith("/storage/goods/modify")
				|| uri.startsWith("/storage/goods/input") || uri.startsWith("/storage/record/add")
				|| uri.startsWith("/storage/record/modify") || uri.startsWith("/storage/record/input");
	}

	private boolean isSuperWrite(String uri) {
		return uri.startsWith("/storage/user/register");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
