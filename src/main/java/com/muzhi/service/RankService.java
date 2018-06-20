package com.muzhi.service;

import com.muzhi.model.Rank;
import com.muzhi.model.configbean.ConfigRate;
import com.muzhi.service.impl.TaskNotAllFinishException;

/**
 * @author yany 评级系统
 *
 */
public interface RankService{
	public void initRank(Integer uid);
	public Rank query(Integer uid);
	public void update(Integer uid) throws BuildMaxLevelException, TaskNotAllFinishException;
	public void insert(ConfigRate rate, Integer uid);
	public Rank refresh(String userByToken);
}
