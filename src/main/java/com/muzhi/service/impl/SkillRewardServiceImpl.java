package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.SkillrewardDao;
import com.muzhi.model.SkillReward;
import com.muzhi.service.SkillRewardService;
@Service
public class SkillRewardServiceImpl implements SkillRewardService {
	@Autowired
	private  SkillrewardDao skillrewardDao;
	@Override
	public boolean isReceive(Integer uid, Integer tid) {
		SkillReward receive = skillrewardDao.isReceive(uid, tid);
		if (receive==null) {
			return false;
		}
		return true;
	}

	@Override
	public void insert(SkillReward skillreward) {
		skillrewardDao.insert(skillreward);

	}

}
