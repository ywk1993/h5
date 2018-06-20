package com.muzhi.service.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.Result;
import com.muzhi.service.UserService;
import com.muzhi.service.WeixinService;

@Service
public class WeixinServiceImpl implements WeixinService {
	@Autowired
	UserService userService;
	
	private static String appid = "wxa4e3a9ac6d7a8293";
	private static String secret = "46abd6bdc4ee6ba40a05803da581e592";
	private static String js_code;
	public static String URL = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + js_code
			+ "&grant_type=authorization_code";
	
	public static final Logger log = LoggerFactory.getLogger("com.muzhi.service.impl.WeixinServiceImpl");			
	
	public static void main(String[] args) {
//		getOpenId1("033VyWH22PgPBX0QfiJ220CcI22VyWHl");
	}
	
	@Override
	public String getOpenId(String js_code) {
		WeixinServiceImpl.js_code = js_code;
		CloseableHttpClient client = HttpClients.createDefault();
		String URL = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + js_code
				+ "&grant_type=authorization_code";
		log.info("URL:" + URL);
		System.out.println("URL:" + URL);
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response;
        String openid = null;
		try {
			response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			System.out.println("result:" + result);
			JSONObject parseObject = JSON.parseObject(result);
			openid = parseObject.getString("openid");
			log.info("openid:" + openid);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("get openid occurs a error!\n" + e.getMessage());
		}
		return openid;
	}

	@Override
	public String queryAccount(String openid) {
		String loginFromWeinxin = userService.loginFromWeinxin(openid);
		return loginFromWeinxin;
	}

}
