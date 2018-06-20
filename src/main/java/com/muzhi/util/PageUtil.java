package com.muzhi.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
	public static <T> List<T> dataPaging(List<T> lists,int pageNo,int dataSize){
		if(lists == null){//当传入过来的list集合为null时，先进行实例化
			lists = new ArrayList<T>();
			}
			if((Object)pageNo == null){//当传入过来的pageNo为null时，先进行赋值操作
			pageNo = 1;
			}
			if((Object)dataSize == null){//当传入过来的dataSize为null时，先进行赋值操作
			dataSize = 5;
			}
			if(pageNo <= 0){
			pageNo = 1;
			}
			int totalItems = lists.size();
			List<T> afterList = new ArrayList<T>();
			for 
			( int i = (pageNo-1)*dataSize; 
			i < (((pageNo -1)*dataSize) + dataSize > 
			totalItems ? totalItems:((pageNo -1)*dataSize) +dataSize);
			i++) {
			//然后将数据存入afterList中
			afterList.add(lists.get(i));
			}

			return afterList;
	}

}
