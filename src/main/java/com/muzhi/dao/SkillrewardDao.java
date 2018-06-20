package com.muzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.SkillReward;

public interface SkillrewardDao {
	/**
	 * 当前的奖励是否已经领取
	 * @param uid
	 * @param tid
	 * @return
	 */
	SkillReward isReceive(@Param("uid")Integer uid,@Param("tid")Integer tid);
   /**
    * 添加一条记录
    * @param skillreward
    */
   void insert(SkillReward skillreward);
}