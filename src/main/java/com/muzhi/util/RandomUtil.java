package com.muzhi.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 
 * @author Yuwk
 *
 *         2017年12月1日
 */
public class RandomUtil {

	private static Random random = new Random();

	/**
	 * 获取一个整形的随机数
	 * 
	 * @param seed
	 * @return
	 */
	public static Integer getInteger(Integer seed) {
		if (seed <= 0) {
			return 0;
		}
		return random.nextInt(seed);
	}
	/**
	 * 权重随机
	 * 
	 * @param list
	 * @param ran
	 * @return
	 */
	public static Integer getWeight(Map<Integer, Integer> map, int random) {
		// map里放的配置表里面相应id值，和每个值对应的权重
		Integer sum = 0;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			sum += entry.getValue();
			if (random <= sum) {
				return entry.getKey();
			}
		}
		return null;
	}
	/**
	 * 根据几率算取是否成功 ，true成功，false失败
	 * @param odds 成功率
	 * @return
	 */
	public static boolean getIsSuccess(Integer odds) {
		Random random = new Random();  
		int i = random.nextInt(100)+1; //随机数为1-100 ,[1,100]
		if(i>=1 && i<=(odds)) {
			return true;
		}
		return false;
	}
}
