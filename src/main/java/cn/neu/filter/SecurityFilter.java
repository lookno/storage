package cn.neu.filter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.neu.dao.TokenDao;
import cn.neu.dto.TokenParamsDto;
import cn.neu.exception.ServerException;
import cn.neu.service.ITokenService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityFilter implements Filter {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private ITokenService iTokenService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		String uri = hRequest.getRequestURI();
		System.out.println(uri);
		if (uri.contains("login") || uri.contains("register") || uri.contains("findpwd")) {
			chain.doFilter(request, response);
		} else {
			String token = hRequest.getHeader("HTTP_TOKEN");
			if (token == null) {
				response.setContentType("application/json");
				response.getWriter().write("{\"msg\":\"请登录\"}");
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
				if(bool<=0){
					response.setContentType("application/json");
					response.getWriter().write("{\"msg\":\"token过期，请重新登录\"}");
				}else{
					chain.doFilter(request, response);
				}
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
