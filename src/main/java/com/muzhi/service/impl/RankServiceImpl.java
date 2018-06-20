package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.RankDao;
import com.muzhi.model.Rank;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigRate;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.ArticleService;
import com.muzhi.service.BuildMaxLevelException;
import com.muzhi.service.BuildService;
import com.muzhi.service.CookBookNService;
import com.muzhi.service.FoodcenterService;
import com.muzhi.service.RankService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.ResultFormatUtil;

@Service
public class RankServiceImpl implements RankService{
	@Autowired
	private UserService userService;
	@Autowired
	private RankDao rankDao;
	@Autowired
	private InitConfig initConfig;
	@Autowired
	private TaskService taskService;
	@Autowired
	private BuildService buildService;
	@Autowired
	private CookBookNService cookBookNSerivce;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private FoodcenterService foodcenterService;

	@Override
	public void initRank(Integer uid) {
		ConfigRate configRate = initConfig.getInstance().getRateMap().get(LEVEL_STATE.INIT_LEVEL.getNum());

		insert(configRate, uid);
		taskService.insertList(uid, configRate.getTask());
	}

	@Override
	public void insert(ConfigRate configRate, Integer uid) {
		rankDao.insert(new Rank(uid, configRate));
	}

	@Override
	public Rank query(Integer uid) {
		Rank rank = rankDao.selectByPrimaryKey(uid);
		String taskList = rank.getTask();
		rank.setTaskList(taskService.query(uid, taskList));
		return rank;
	}

	@Override
	public void update(Integer uid) throws BuildMaxLevelException, TaskNotAllFinishException {
		Rank rank = rankDao.selectByPrimaryKey(uid);

		if (taskService.isAllFinish(uid, rank.getTask())) {
			Rank rankNew = new Rank(uid, initConfig.getInstance().getRateMap().get(rank.getRateLevel() + 1));
			rankDao.updateByPrimaryKey(rankNew);
			taskService.update(uid, rankNew.getTask());
			
		} else {
			throw new TaskNotAllFinishException();
		}
	}

	@Override
	public Rank refresh(String token) {
		User user = userService.getUserByToken(token);
		// 刷新 5,6,7,8,
		buildService.getBuildAll(user);
		articleService.getArticleInfo(token);
		foodcenterService.getBookResearch(token);
		cookBookNSerivce.getCookBook(user);
		return query(user.getId());
	}
}
