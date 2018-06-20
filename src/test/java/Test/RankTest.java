package Test;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.muzhi.dao.RankDao;
import com.muzhi.dao.TaskDao;
import com.muzhi.model.Rank;
import com.muzhi.model.Result;
import com.muzhi.model.Task;
import com.muzhi.service.BuildMaxLevelException;
import com.muzhi.service.FoodService;
import com.muzhi.service.RankService;
import com.muzhi.service.impl.TaskNotAllFinishException;

import Test.BaseTest;

/**
 * 评级系统
 * @author yany
 */
public class RankTest extends BaseTest{
	@Autowired
	TaskDao taskDao;
	@Test
	public void taskDao() {
		List<Task> recordList = new ArrayList<Task>();
		Task task = new Task();
		task.setId(1);
		task.setTid(1);
		task.setStatus(1);
		task.setCondition(1);
		task.setDescribe("");
		task.setParament("");
		task.setUserNum(1);
		recordList.add(task);
//		taskDao.insert(task);
		taskDao.insertList(recordList );
	}
	@Autowired
	RankDao rankDao;
	@Test
	public void rankDao() {
		Rank record = new Rank();
		record.setId(1);
		record.setLogo("");
		record.setName("");
		record.setNeedgold(1);
		record.setStartLevel(1);
		record.setTask("");
		rankDao.insert(record );
	}
	
	@Autowired
	RankService rankService;
	@Test
	public void rankService() throws BuildMaxLevelException, TaskNotAllFinishException {
//		rankService.initRank(2);
//		rankService.query(2);
		Rank query = rankService.query(172);
		List<Task> taskList = query.getTaskList();
		taskList.get(1).setUserNum(123456);;
		taskDao.updateList(taskList);
		System.out.println(rankService.query(172));
		
//		rankService.update(2);
	}
	@Autowired
	FoodService foodService;
	@Test
	public void bean() {
		foodService.makeFood("181cea0b-926f-4e7e-ba8a-38b0e163cdfd", 101001, 0);
	}
}
