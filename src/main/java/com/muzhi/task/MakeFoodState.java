package com.muzhi.task;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.muzhi.service.MakeFoodInfoService;

/**
 * 
 * @author Yuwk
 *
 * 2018年1月9日
 */
@Service
public class MakeFoodState implements Job {
	
	public Logger logger = LoggerFactory.getLogger(MakeFoodState.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		JobDataMap jobDataMap = arg0.getMergedJobDataMap();
		Integer id = (Integer) jobDataMap.get("id");
		Integer index = (Integer) jobDataMap.get("index");
		MakeFoodInfoService makeFoodInfoService = (MakeFoodInfoService) jobDataMap.get("makeFoodInfoService");
		makeFoodInfoService.updateState(id, 2, index);
		logger.info("做菜时间到改变状态为完成");
		
		
	}
}
