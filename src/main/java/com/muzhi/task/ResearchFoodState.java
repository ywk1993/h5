package com.muzhi.task;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.muzhi.model.CookBook;
import com.muzhi.service.CookBookSerivce;
/**
 * 
 * @author Yuwk
 *
 * 2018年1月9日
 */
@Service
public class ResearchFoodState implements Job {
	
	public Logger logger = LoggerFactory.getLogger(ResearchFoodState.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobDataMap jobDataMap = arg0.getMergedJobDataMap();
		CookBook cookBook = (CookBook) jobDataMap.get("cookBook");
		CookBookSerivce cookBookSerivce = (CookBookSerivce) jobDataMap.get("cookBookSerivce");
		cookBookSerivce.updateCookBook(cookBook);
		logger.info("研究完成操作");
		
	}
}
