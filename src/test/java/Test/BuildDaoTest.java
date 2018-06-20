package Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.muzhi.dao.FoodcenterDao;
import com.muzhi.dao.OrderDao;
import com.muzhi.model.Foodcenter;
import com.muzhi.model.Fridge;
import com.muzhi.model.Generator;
import com.muzhi.model.Manor;
import com.muzhi.model.ManorKey;
import com.muzhi.model.Order;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigManor;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.model.vo.Build;
import com.muzhi.service.BuildService;
import com.muzhi.service.BuildUpgradingException;
import com.muzhi.service.FoodcenterService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.GeneratorService;
import com.muzhi.service.InitService;
import com.muzhi.service.ManorService;
import com.muzhi.service.OrderService;
/**
 * 庄园测试
 * @author yany
 *
 */
public class BuildDaoTest extends BaseTest {
	@Autowired
	BuildService buildService;
	@Autowired
	InitService initService;
	@Autowired
	ManorService manorService;
	@Test
	public void buildDao() {
//		manorService.insert(new Manor(1, 1, 1, 1, 1, 1, 1));
//		Manor selectByPrimaryKey = manorService.selectByPrimaryKey(new ManorKey(136, 51));
//		selectByPrimaryKey.setLevel(2);
//		manorService.updateByPrimaryKey(selectByPrimaryKey);
		
		Fridge selectByPrimaryKey = fridgeDao.selectByPrimaryKey(1);
		selectByPrimaryKey.setBanana(100000);
		
		fridgeDao.updateByPrimaryKey(selectByPrimaryKey);
		
	}
	
	@Autowired
	FridgeService fridgeService;
	
	@Test
	public void collect() {
		User user = new User();
		user.setId(1);
		Fridge fridge = fridgeService.selectByPrimaryKey(user.getId());
		Fridge collect = buildService.collect(user, 2);
		System.out.println(collect);
	}
	
	@Test
	public void buildService() throws BuildUpgradingException {
		User userByToken = new User();
		userByToken.setId(155);
		initService.loginInit(155);
//		buildService.buildInit(userByToken);
		
		Build buildAll = buildService.getBuildAll(userByToken);
		/*
		buildService.isAllowUpgrade(userByToken, 1);
		buildService.isAllowUpgrade(userByToken, 2);
		buildService.isAllowUpgrade(userByToken, 3);
		buildService.isAllowUpgrade(userByToken, 4);
		buildService.isAllowUpgrade(userByToken, 5);
		buildService.isAllowUpgrade(userByToken, 6);
		buildService.isAllowUpgrade(userByToken, 7);*/
		
		Build buildAll1 = buildService.getBuildAll(userByToken);
		
	}
	
	@Test
	public void updateBuild() {
		User userByToken = new User();
		userByToken.setId(89);
		userByToken.setGold(10000);
		Build buildAll = buildService.getBuildAll(userByToken);
		System.out.println("餐厅升级剩余时间：" + buildAll.getManorRestaurant().getLeftTime());
//		try {
//			buildService.isAllowUpgrade(userByToken, 1);
//		} catch (BuildUpgradingException e) {
//			e.printStackTrace();
//		}
//		try {
//			Thread.sleep(2*1000);
//			System.out.println("餐厅升级剩余时间：" + buildService.getBuildAll(userByToken).getManorRestaurant().getLeftTime());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
	}
	
	@Test
	public void test() {
		User userByToken = new User();
		userByToken.setId(98);
		ManorKey manorKey = new ManorKey();
		manorKey.setId(userByToken.getId());;
		List<Manor> updateManorList = manorService.selectBySelectivePrimaryKey(manorKey);
	}
	
	@Autowired
	GeneratorService generatorService;
	@Test
	public void test1 () {
		long currentTimeMillis = System.currentTimeMillis();
		//generatorService.insert(new Generator(2, currentTimeMillis, currentTimeMillis, currentTimeMillis, currentTimeMillis, currentTimeMillis));
	}
	@Autowired
	FoodcenterService foodcenterService;
	@Test
	public void testFoodCenter() {
		foodcenterService.updateByPrimaryKeySelective(new Foodcenter(26, 2, 7, 0,10));
	}
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDao orderDao;
	@Test
	public void testOrder() {
		//List<Order> orders = orderService.getOrders(1, 1);
		/*Order order = new Order();
		order.setId(1);
		Date date = new Date();  
		Timestamp timeStamp = new Timestamp(date.getTime());  
		order.setEndTime(timeStamp);
		// 查询未到刷新点的订单， db_Start_timeStamp < timeStamp < db_End_timeStamp
		List<Order> orderList = orderDao.selectByObject(order);
		Order order2 = orderList.get(0);*/
		
		//System.out.println(orders);
	}
	@Test
	public void token() {
		//Result userLogin = userService.userLogin("yy", "123456");
		//System.out.println(userLogin);
	}
	
	
}
