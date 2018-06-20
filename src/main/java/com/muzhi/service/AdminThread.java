package com.muzhi.service;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.muzhi.model.LoginResult;
import com.muzhi.model.Result;
import com.muzhi.util.HttpUtils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

/**
* @author ykw
* @version 创建时间：2018年5月22日 下午4:51:44
*/
public class AdminThread implements Runnable{
	
	private HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	
	private JoinPoint joinPoint;
	
	private ManagerService managerService;
	
	private Object val;
	
	Logger logger = LoggerFactory.getLogger(AdminThread.class);
	
	public AdminThread(JoinPoint joinPoint,ManagerService managerService,Object val) {
		this.joinPoint = joinPoint;
		this.managerService = managerService;
		this.val = val;
	}
	
	@Override
	public void run() {
		try {
			//Thread.sleep(5000);
			//获取客户端传过来的机型
			String ua = request.getHeader("User-Agent");
			System.out.println(request.toString());
			UserAgent userAgent = UserAgent.parseUserAgentString(ua);  
			Browser browser = userAgent.getBrowser(); 
			//获取客户端传过来的ip
			String loginIp = HttpUtils.getIpAddr(request);
			String phoneModel = browser.toString();
			//获取token
			Object parament =((Result) val).getData();
			String token =((LoginResult) parament).getToken();
			
			System.out.println(loginIp+"======"+phoneModel);
			Object[] args = joinPoint.getArgs();
			managerService.loginAfter(args,loginIp,phoneModel,token);
			
			System.out.println("多线程执行完毕+++++++++");
		} catch (Exception e) {
			//e.printStackTrace();
			logger.info("远程接口调用失败！！！");
		}
		
	}


}
