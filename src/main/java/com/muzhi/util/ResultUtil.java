package com.muzhi.util;

import com.muzhi.model.Result;

/**
 * 
 * @author Yuwk
 *
 * 2017年11月13日
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setErrMsg("成功");
        result.setData(object);
        return result;
    }
    
    public static Result success(Object object, int code) {
        Result result = new Result();
        result.setCode(code);
        result.setErrMsg("成功");
        result.setData(object);
        return result;
    }
    
    public static Result success(Object object, int code, String setErrMsg) {
        Result result = new Result();
        result.setCode(code);
        result.setErrMsg(setErrMsg);
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setErrMsg(msg);
        return result;
    }
    public static Result error(Integer code, String msg,Object object) {
    	Result result = new Result();
    	result.setCode(code);
    	result.setErrMsg(msg);
    	result.setData(object);
    	return result;
    }
    
}
