package com.muzhi.service;

import com.muzhi.model.CookBench;
import com.muzhi.model.User;

/**
 * 灶台接口
 * @author yany
 *
 */
public interface CookBenchService {
	/**
	 * 获取灶台
	 * @param id
	 * @return
	 */
	public CookBench getCookBench(Integer id);

	public void cookBenchInit(User user);
	/**
	 * 跟新炤台
	 * @param cookBench
	 */
	public void updateCookBench(CookBench cookBench);
}
