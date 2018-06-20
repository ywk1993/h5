package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.TaskDao;
import com.muzhi.model.Task;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.configbean.ConfigTask;
import com.muzhi.service.TaskService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.LogStrBuffer;
import com.muzhi.util.StringUtil;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private InitConfig initConfig;
	
	@Override
	public void insertList(List<Task> taskList) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insertList(Integer uid, String task) {
		List<Integer> strToList = strToList(task);
		Map<Integer, ConfigTask> taskMap = initConfig.getInstance().getTaskMap();
		
		List<Task> list = new ArrayList<Task>();
		for (Integer i : strToList) {
			ConfigTask configTask = taskMap.get(i);
			list.add(new Task(uid, configTask));
		}
		taskDao.insertList(list);
		
	}

	@Override
	public List<Task> query(Integer uid, String task) {
		Task record = new Task();
		record.setId(uid);
		record.setStatus(1); // effect tasks
		return taskDao.selectList(record);
	}

	@Override
	public List<Task> query(Integer uid, List<Integer> taskList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAllFinish(Integer uid, List<Integer> taskIdList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAllFinish(Integer uid, String task) {
		boolean isAllFinished = true;
		List<Task> query = query(uid, task);
		
		for (Task t : query) {
			String parament = t.getParament();
			Integer aimNum = 0;
			
			// zhichi 
			if (isInteger(parament)) {
				aimNum = Integer.parseInt(parament);
			} else {
				aimNum = new ArrayList<Integer>(StringUtil.getCurrencyType(t.getParament()).values()).get(0);
			}
			if (t.getUserNum() < aimNum) {
				LogStrBuffer.addInforLog("not_finish_info : {\nuser:" + uid + ",\ntask:" + t.getTid() + ",\ncurrentNum:" + t.getUserNum() + ",\naimNum:" + aimNum + "\n}");
				isAllFinished = false;
				break;
			}
		}
		return isAllFinished;
	}

	@Override
	public void update(List<Task> taskList) {
		taskDao.updateList(taskList);
	}

	@Override
	public void update(Integer uid, String task) {
		// solve the previous user data
		Task oldTask = new Task();
		oldTask.setId(uid);
		oldTask.setStatus(0);
		taskDao.updateSelective(oldTask);
		// insert the nextData
		insertList(uid, task);
	}

	@Override
	public List<Integer> strToList(String task) {
		List<Integer> list = new ArrayList<Integer>();
		String[] split = task.split(",");
		for (int i = 0; i < split.length; i ++) {
			list.add(Integer.parseInt(split[i]));
		}
		return list;
	}
	
	 public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }

	@Override
	public void updateTaskNum(Integer uid, Integer type, Integer num) {
		List<Task> query = query(uid, "");
		for (Task task : query) {
			if (task.getCondition().equals(type)) {
				task.setUserNum(num);
			}
		}
		taskDao.updateList(query);
	}

	@Override
	public void updateTaskNumAutoAddtion(Integer uid, Integer type, Integer xvalue) {
		List<Task> query = query(uid, "");
		for (Task task : query) {
			String[] split = task.getParament().split(",");
			int parseInt = Integer.parseInt(split[0]);
			if (task.getCondition().equals(type)&&parseInt==xvalue.intValue()) {
				task.setUserNum(task.getUserNum() + 1);
			}
		}
		taskDao.updateList(query);
	}

	@Override
	public void updateTaskNumAutoAddtion(Integer uid, Integer type, Map<Integer, Integer> param) {
		List<Task> query = query(uid, "");
		for (Task task : query) {
			String[] split = task.getParament().split(",");
			int xValue = Integer.parseInt(split[0]);
			if (task.getCondition().equals(type)
					&&param.containsKey(xValue)) {
				task.setUserNum(param.get(xValue));
			}
		}
		taskDao.updateList(query);
	}

	@Override
	public void updateTaskNum(Integer uid, Integer type) {
		List<Task> query = query(uid, "");
		for (Task task : query) {
			if (task.getCondition().equals(type)) {
				task.setUserNum(task.getUserNum() + 1);
			}
		}
		taskDao.updateList(query);
	}
}