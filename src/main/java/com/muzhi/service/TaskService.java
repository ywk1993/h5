package com.muzhi.service;

import java.util.List;
import java.util.Map;

import com.muzhi.model.Task;

/**
 * 任务
 * @author yany
 *
 */
public interface TaskService {
	public void updateTaskNum(Integer uid, Integer type);
	/**
	 * 更新uid用户的type任务执行数量为num
	 * @param uid
	 * @param type
	 * @param num
	 */
	public void updateTaskNum(Integer uid, Integer type, Integer num);
	/**
	 * 累加uid用户的type任务符合xvalue参数的执行次数
	 * @param uid
	 * @param type
	 * @param xvalue
	 */
	public void updateTaskNumAutoAddtion(Integer uid, Integer type, Integer xvalue);
	/**
	 * 设置符合条件任务的用户当前数值
	 * @param uid
	 * @param type
	 * @parma map<xvalue, yvalue> 即任务的x属性和y属性键值对
	 */
	public void updateTaskNumAutoAddtion(Integer uid, Integer type, Map<Integer, Integer> param);
	public void insertList(List<Task> taskList);
	/**
	 * 插入列表
	 * @param uid
	 * @param task
	 * @param init 是否为初始化
	 */
	public void insertList(Integer uid, String task);
	public List<Task> query(Integer uid, String task);
	/**
	 * query user's tasks
	 * @param uid
	 * @param taskList
	 * @return
	 */
	public List<Task> query(Integer uid, List<Integer> taskList);
	/**
	 * is the tasks from other module finished 
	 * @param uid
	 * @param taskIdList
	 * @return
	 */
	public boolean isAllFinish(Integer uid, List<Integer> taskIdList);
	/**
	 * is the tasks at config table finished    
	 * @param uid
	 * @param task
	 * @return
	 */
	public boolean isAllFinish(Integer uid, String task);
	/**
	 * update user's Tasks from other module
	 * @param taskList
	 */
	public void update(List<Task> taskList);
	/**
	 * update user's Tasks from config table
	 * @param uid
	 * @param task
	 * @param string 
	 */
	public void update(Integer uid, String task);
	/**
	 * no relationship to business solving
	 * @param task
	 * @return
	 */
	public List<Integer> strToList(String task);
	
}
