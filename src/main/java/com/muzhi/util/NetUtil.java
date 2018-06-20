package com.muzhi.util;

import javax.servlet.http.HttpServletRequest;

public class NetUtil {
	public  static String getIpAddr(HttpServletRequest request)  {  
		String ip = request.getHeader("x-forwarded-for");
		if (null != ip && ip.length() > 0) {
			String tmpIps[] = ip.split(",");
			for (String tmpIp : tmpIps) {
				tmpIp = tmpIp.trim();
				if (null != tmpIp && tmpIp.length() > 0 && !"unknown".equalsIgnoreCase(tmpIp)) {
					ip = tmpIp;
					break;
				}
			}
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	 } 
}
