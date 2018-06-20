package com.muzhi.util;

import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.Result;

public class ResultFormatUtil extends ResultUtil{

	public static Result success(Object obj, String typeName) {
		Result result = new Result();
	     result.setCode(0);
	     result.setErrMsg("成功");
	     JSONObject jsonObject = new JSONObject();
	     jsonObject.put(typeName, obj);
	     result.setData(jsonObject);
	     return result;
	}
}
