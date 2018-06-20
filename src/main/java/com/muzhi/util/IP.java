package com.muzhi.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

//import dao.ModelipDao;
//import model.Modelip;
//import util.DebuUtil;
//import util.LogStrBuffer;


public class IP {
	
	static Logger logger = LoggerFactory.getLogger(IP.class);
	
	public static void main(String[] args){
//		getIPFromSrv("119.29.117.51");
		getIPFromSrv("10.0.0.97");
	}

	 public static String getIPFromSrv(String IP)
	 {
			String strUrl = "http://apis.haoservice.com/getLocationbyip";   //http://apis.haoservice.com/getLocationbyip?key=ccf428507bce4c4e9999e12d4d0f0ca5&ip=223.104.171.143
	    
	        Map<String,String> map=new HashMap<String,String>();    
	        map.put("ip", IP);     
	        map.put("key", "ccf428507bce4c4e9999e12d4d0f0ca5");
	        String msmrsq = "";
			try {
//				DebuUtil.log4("IP服务接口传入参数："+IP);
				System.out.println("IP服务接口传入参数："+IP);
				String list= HttpUtils.URLGet(strUrl, map, HttpUtils.URL_PARAM_DECODECHARSET_UTF8);
				System.out.println(list);
				JSONObject parseObject = JSON.parseObject(list);
				System.out.println(parseObject.get("result"));
				JSONObject jsonObject = JSON.parseObject(parseObject.get("result").toString());
				System.out.println(jsonObject.get("province"));
				System.out.println(list);
//				for(int i = 0; i < list.size() ; i++)
//				{
//					String str = (String) list.get(i);
//					msmrsq += str;
//					//DebuUtil.log4("调用IP服务接口回应结果："+msmrsq);
//					System.out.println("调用IP服务接口回应结果："+msmrsq);
//				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				LogStrBuffer.addWarnLog(DebuUtil.getStackStr(e.getStackTrace()));
			} 
//			LogStrBuffer.flushLog();
//			DebuUtil.log("msmrsq="+msmrsq);
			System.out.println("msmrsq="+msmrsq);
			
			return msmrsq;
	 }
	 /**
	  * 通过ip获取用户省份
	  * @param ip
	  * @return
	  */
	 public static String getProvinceByIP(String ip) {
		 String strUrl = "http://apis.haoservice.com/getLocationbyip";
		 
		 Map<String,String> map=new HashMap<String,String>();    
	        map.put("ip", ip);     
	        map.put("key", strUrl);
	        
         try {
			String list= HttpUtils.URLGet(strUrl, map, HttpUtils.URL_PARAM_DECODECHARSET_UTF8);
			JSONObject parseObject = JSON.parseObject(list);
			
			JSONObject jsonObject = JSON.parseObject(parseObject.get("result").toString());
			
			return jsonObject.get("province").toString();
		} catch (Exception e) {
//			e.printStackTrace();
			logger.info("ip获取失败");
		} 
		 return null;
	 }
	 
	 
/*	 public static String getAddr(String IP)
	 {
		 
		 String ret = "";
		 String retIp= "";
		 ModelipDao IPDao = new ModelipDao();
		 ret = IPDao.getIP(IP);
		 
		 
		 if(StringUtil.is_nullString(ret))
		 {
		    String msmrsq=getIPFromSrv(IP);
			//JSONObject json = JSONObject.fromObject(msmrsq);
			//int ErrCode = json.getInt("error_code");
		    JSONObject json = null;
			int ErrCode = -1;
			if(!StringUtil.is_nullString(msmrsq))
			{
				json = JSONObject.fromObject(msmrsq);
				ErrCode = json.getInt("error_code");
			}
			
			if(ErrCode == 0)
			{
				JSONObject result=(JSONObject)json.get("result");
				
				String city = result.getString("city");
				String province = result.getString("province");

				ret = province+"_"+city;
				if(ret.length() > 30)
				{
					ret = ret.substring(0, 30);
				}
				Modelip ip = new Modelip();
				ip.setIp(IP);
				ip.setAddr(ret);
				IPDao.add(ip);
			}
		 }
		 retIp = ret+"_"+IP;
		return retIp;
	 }*/
	 
}
