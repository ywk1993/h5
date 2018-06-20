package com.muzhi.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.muzhi.dao.IncomeDao;
import com.muzhi.dao.RankingDao;

/**
* @author ykw
* @version 创建时间：2018年6月13日 下午5:21:42
*/
@Component
public class RankingTask {
	@Autowired
	private IncomeDao incomeDao;
	@Autowired
	private RankingDao rankingDao;
	
	@Scheduled(cron="0 0/30 * * * ?")
	public void getRanking() {
		System.out.println("更新排行榜");
		rankingDao.updateIncome();
		rankingDao.updateRate();
		rankingDao.updateHaohua();
	}
	@Scheduled(cron="0 0 0 * * ?")
	public void updateIncome() {
		incomeDao.updateIncome();
		System.out.println("更新昨日收益");
	}
}
