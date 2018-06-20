package com.muzhi.service.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.service.UserService;
import com.muzhi.util.ResultUtil;

@Component
public class GameInterceptor implements Filter {

	@Autowired
	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		userService = (UserService) ctx.getBean("userServiceImpl");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String method = ((HttpServletRequest) request).getMethod();
		String token = null;
		if (method.equalsIgnoreCase("get")) {
			token = request.getParameter("token");
			System.out.println("gettoken>>>>>>>>>>>>>>>>>>>" + token);
			if (null==token) {
				chain.doFilter(request, response);
			}else {
				User user = userService.getUserByToken(token);
				if (null == user) {
					Result error = ResultUtil.error(1, "登录已经过期，请重新登录");
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().print(JSON.toJSON(error));
				}else {
					chain.doFilter(request, response);
				}
			}
			
		}
		if (method.equalsIgnoreCase("post")) {
			MyRequestWrapper myRequestWrapper = new MyRequestWrapper((HttpServletRequest) request);
			String body = myRequestWrapper.getBody();
			if (StringUtils.isEmpty(body)) {
				chain.doFilter(myRequestWrapper, response);
			} else {
				//System.out.println("parmeter>>>>>>>>>>>>" + body);
				JSONObject parseObject = JSON.parseObject(body);
				token = parseObject.getString("token");
				System.out.println("token>>>>>>>>>>" + token);
				if (null == token) {
					chain.doFilter(myRequestWrapper, response);
				}else {
					User user = userService.getUserByToken(token);
					if (null == user) {
						Result error = ResultUtil.error(1, "登录已经过期，请重新登录");
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().print(JSON.toJSON(error));
					}else {
						chain.doFilter(myRequestWrapper, response);
					}
				}
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
