package com.muzhi.service;

import com.muzhi.model.Signboard;
import com.muzhi.model.User;

public interface SignboardService {
	/**
	 * 获取招牌信息
	 * @param id
	 * @return
	 */
	public Signboard getSignboard(Integer id);
	/**
	 * 跟新招牌信息
	 * @param signboard
	 */
	public void updateSignboard(Signboard signboard);
	/**
	 * 添加招牌信息
	 * @param signboard
	 */
	public void insertSignboard(User userInit);
	
}	
