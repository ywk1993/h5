package com.muzhi.service;

import com.muzhi.model.SkillReward;

public interface SkillRewardService {

	
	/**
	 * 当前的奖励是否已经领取 true 领取 false 未领取
	 * @param uid
	 * @param tid
	 * @return
	 */
	public boolean isReceive(Integer uid,Integer tid);
   /**
    * 添加一条记录
    * @param skillreward 
    */
   public void insert(SkillReward skillreward);
}
