package com.muzhi.task;

import java.util.Date;
import java.util.HashMap;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.model.CookBook;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.MakeFoodInfoService;

@Service
public class MakeStateJobManager {
	/**
	 * 初始化静态的任务调度容器，并启动
	 */
	static String filePath = "resource/quartz.properties";
	public static SchedulerFactory sf;
	public static Scheduler scheduler;
	
	static {
		try {
			sf = new StdSchedulerFactory(filePath);
			scheduler = sf.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private MakeFoodInfoService makeFoodInfoService;
	@Autowired
	private CookBookSerivce cookBookSerivce;

	
	public Logger logger = LoggerFactory.getLogger(MakeStateJobManager.class);
	
	
	
	/**
	 * 改变做菜的状态
	 * @param userid
	 * @param bufferid
	 * @param time
	 */
	public void makeFoodStateChange(int id,int index,Integer time) {
		try {
			String identity = id + "_" +index;
			logger.info("改变做菜状态"+"参数identity："+identity);
			HashMap<String, Object> JobData = new HashMap<>();
			JobData.put("id", id);
			JobData.put("index", index);
			JobData.put("makeFoodInfoService", makeFoodInfoService);
			JobDataMap jobDataMap = new JobDataMap(JobData);
			JobDetail job = JobBuilder.newJob(MakeFoodState.class).withIdentity(identity).usingJobData(jobDataMap).build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey(identity))
					.startAt(new Date(System.currentTimeMillis() + time)).build();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 改变做菜的状态
	 * @param userid
	 * @param bufferid
	 * @param time
	 */
	public void ResearchStateChange(CookBook cookBook,int id,Integer time) {
		try {
			String identity = id + "_cookCenter";
			logger.info("改变研究状态"+"参数identity："+identity);
			HashMap<String, Object> JobData = new HashMap<>();
			JobData.put("id", id);
			JobData.put("cookBook", cookBook);
			JobData.put("cookBookSerivce", cookBookSerivce);
			JobDataMap jobDataMap = new JobDataMap(JobData);
			JobDetail job = JobBuilder.newJob(ResearchFoodState.class).withIdentity(identity).usingJobData(jobDataMap).build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey(identity))
					.startAt(new Date(System.currentTimeMillis() + time)).build();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 判断标志的任务是否存在 true 存在  false不存在
	 * @param identity
	 * @return
	 */
	public boolean isTaskRemain(String identity) {
		JobDetail jobDetail = null;
		try {
			jobDetail = scheduler.getJobDetail(new JobKey(identity));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		if (jobDetail != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 停止计时
	 */
	public void stopTime(String identity) {
		try {
			logger.info(identity);
			TriggerKey triggerKey = TriggerKey.triggerKey(identity);
            scheduler.pauseTrigger(triggerKey);// 停止触发器  
            scheduler.unscheduleJob(triggerKey);// 移除触发器  
            scheduler.deleteJob(JobKey.jobKey(identity));// 删除任务  
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 刷新计时任务
	 */
	public void resumeTime(String identity,Integer time) {
		logger.info(identity);
		TriggerKey triggerKey = TriggerKey.triggerKey(identity);
		try {
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey(identity))
					.startAt(new Date(System.currentTimeMillis() + time)).build();
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			
			e.printStackTrace();
		}
	}
}
