package com.muzhi.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.muzhi.model.Reused;

/*import com.muzhi.model.bo.TownConstance;*/

/**
 * 字符串处理
 * 
 * @author yany
 *
 */
public class StringUtil {

	public static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * 切分配置数据 适用于一级的组合数据 例如（1，2，3，4）不适用于（1，2；2，3；3，4）
	 * 
	 * @param inputContent
	 * @return
	 */
	public static List<Integer> splitHandle(String inputContent) {
		String[] split = null;
		List<Integer> valueList = new ArrayList<Integer>();
		if (inputContent.contains(",")) {
			split = inputContent.trim().split(",");
		}
		if (inputContent.contains("，")) {
			split = inputContent.trim().split("，");
		}
		for (int i = 0; i < split.length; i++) {
			valueList.add(Integer.parseInt(split[i]));
		}
		return valueList;
	}

	/**
	 * 切分；隔开的数据
	 * 
	 * @param inputContent
	 * @return
	 */
	public static List<String> splitStrHandle(String inputContent) {

		String[] array = null;
		List<String> strings = new ArrayList<>();
		if (inputContent.contains("；")) {
			array = inputContent.trim().split("；");
		}
		if (inputContent.contains(";")) {
			array = inputContent.trim().split(";");
		}
		if (null == array || array.length == 0) {
			strings.add(inputContent);
		} else {
			strings = new ArrayList<>(Arrays.asList(array));
		}
		return strings;
	}

	/**
	 * 切分这种（1，2；2，3；3，4）封装成到map中 如果只有单条1，2也处理 map<key(货币代码),vaule(相应的数量)>
	 * 
	 * @param string
	 * @return
	 */
	public static Map<Integer, Integer> getCurrencyType(String inputContent) {

		List<String> strings = splitStrHandle(inputContent);
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < strings.size(); i++) {
			String newstring = strings.get(i);
			List<Integer> integers = splitHandle(newstring);
			if (integers.get(1) != 0) {
				map.put(new Integer(integers.get(0)), integers.get(1));
			}
		}
		return map;
	}
	
	/**
	 * 获取材料消耗
	 * @param str
	 * @return
	 */
	public static List<Reused> getMetarial(String str) {
		List<Reused> arrayList = new ArrayList<Reused>();
		List<String> splitStrHandle = StringUtil.splitStrHandle(str);
		for (String string : splitStrHandle) {
			Reused reused = new Reused(string);
			if (reused.getNumber()!=0) {
				arrayList.add(reused);
			}
			
		}
		return arrayList;

	}

}
