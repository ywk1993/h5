package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.muzhi.dao.AsherDao;
import com.muzhi.dao.ChefDao;
import com.muzhi.dao.ConfigDao;
import com.muzhi.dao.CookBenchDao;
import com.muzhi.dao.CookBookDao;
import com.muzhi.dao.FridgeDao;
import com.muzhi.dao.FriendDao;
import com.muzhi.dao.InventoryDao;
import com.muzhi.dao.LoginDao;
import com.muzhi.dao.RestaurantDao;
import com.muzhi.dao.UserDao;
import com.muzhi.model.Asher;
import com.muzhi.model.Chef;
import com.muzhi.model.CookBench;
import com.muzhi.model.CookBook;
import com.muzhi.model.Fridge;
import com.muzhi.model.Friend;
import com.muzhi.model.Hall;
import com.muzhi.model.Inventory;
import com.muzhi.model.Login;
import com.muzhi.model.LoginResult;
import com.muzhi.model.Restaurant;
import com.muzhi.model.Result;
import com.muzhi.model.Signboard;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigSkill;
import com.muzhi.model.configbean.ConfigStrength;
import com.muzhi.model.vo.EmployManager;
import com.muzhi.model.vo.FoodDetail;
import com.muzhi.model.vo.MenuAll;
import com.muzhi.model.vo.Staff;
import com.muzhi.service.ConfigService;
import com.muzhi.service.CookBookNService;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.HallService;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.UserService;
import com.muzhi.util.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service-impl.xml"})
public class BaseTest {
	
	/*@Autowired
	TownService townService;
	
	@Test
    public void getTown() {
		System.out.println("suny");
		townService.getTown(null);
	}*/
	Logger logger = LoggerFactory.getLogger(BaseTest.class);
	@Test 
	public void test() {
		CookBench cookBench = new CookBench();
		cookBench.setId(1);
		cookBench.setId(1);
		cookBenchDao.updateCookBench(cookBench);
	}
	
	@Autowired
	UserDao userDao;
	@Test
	public void getUser() {
		User user = userDao.getUser(1);
		System.out.println(user);
		user.setLevel(2);
		userDao.updateUser(user);
		System.out.println("level:" + user.getLevel());
	}
	@Autowired
	FriendDao friendDao;
	@Test
	public void getFriend() {
		Friend friend = new Friend(1, null);
		friendDao.selectList(friend);
	}
	
	@Test
	/**
	 * 添加一个用户，维护10个好友关系
	 */
	public void insertUserList() {
		int friendNum = 10;
		int strangeNum = 10;
		int aroundNum = 10;
		for (int i = 1; i <= 100 ; i ++) {
			System.out.println("index: " + i);
			User user = new User( 0, 20, 0, 1, 0, 0, 100, 0);
			userDao.insert(user);
			List<Friend> friends = friendDao.selectList(new Friend(null, user.getId()));
			List<User> userList = userDao.selectList(new User());
			
			for (User userTemp : userList) {
				List<Friend> selectList = friendDao.selectList(new Friend(userTemp.getId(), null));
				if ( selectList.size() > 0 && selectList.size() < friendNum ) {
					friendDao.insert(new Friend(userTemp.getId(), user.getId()));
					friendDao.insert(new Friend(user.getId(), userTemp.getId()));
					break;
				} else {
					if (userList.size() == 2) {
						User user1 = userList.get(0);
						User user2 = userList.get(1);
						friendDao.insert(new Friend(user1.getId(), user2.getId()));
						friendDao.insert(new Friend(user2.getId(), user1.getId()));
						break;
					}
				}
			}
		}
		
	}
	@Test
	public void getUserIdList() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(165);
		arrayList.add(166);
		arrayList.add(167);
		List<User> selectByIdList = userDao.selectByIdList(arrayList);
		System.out.println(selectByIdList);
	}
	
	
	@Test
	public void selectUserList() {
		List<User> selectList = userDao.selectList(new User());
		System.out.println(selectList);
	}
	
	
	@Autowired
	FridgeDao fridgeDao;
	@Test
	public void getFridge() {
//		Fridge restaurant = fridgeDao.getFridge(154);
//		System.out.println(restaurant.getId());
	}
	@Test
	public void insertFridge() {
		Fridge fridge = new Fridge(1, 1, 100, 500, 500, 500 ,500, 500);
		fridgeDao.insert(fridge);
	}
	@Autowired
	CookBenchDao cookBenchDao;
	@Test
	public void getCookBench() {
		CookBench restaurant = cookBenchDao.getCookBench(1);
		System.out.println(restaurant.getId());
	}
	@Test
	public void insertCookBench() {
		CookBench cookBench = new CookBench(1, 1, 1);
		cookBenchDao.insert(cookBench);
	}
	
	@Autowired
	InventoryDao inventoryDao;
	@Test
	public void getInventory() {
		Inventory restaurant = inventoryDao.getInventoryWithOutFood(2);
		System.out.println(restaurant.getId());
	}
	@Autowired
	InventoryService inventoryService;
	@Test
	public void getInventoryByService() {
		Inventory inventory = inventoryService.getInventory(1);
		System.out.println(inventory.getId());
	}
	
	@Autowired
	ConfigDao configDao;
	@Autowired
	ConfigService configService;
	
	@Test
	public void getConfig() {
		List<ConfigFood> configFood = configDao.getConfigFood();
		List<ConfigSkill> configSkill = configDao.getConfigSkill();
		List<ConfigStrength> configStrength = configDao.getConfigStrength();
		System.out.println("1:" + configFood);
		System.out.println("2:" + configSkill);
		System.out.println("3:" + configStrength);
		
		/*Map<Integer, ConfigFood> configFood2 = configService.getConfigFood();
		System.out.println("4:" + configFood2);*/
	}
	
	@Autowired
	CookBookDao cookBookDao;
	@Test
	public void getCookBookDao() {
		ArrayList<CookBook> cookbookList = new ArrayList<>();
		cookbookList.add(new CookBook(1, 2, 3, 3, "11", 1,1,  2, 3,0));
		cookbookList.add(new CookBook(1, 3, 3, 3, "11", 1,1,  2, 3,0));
		cookBookDao.addList(cookbookList);
		CookBook cookBook = new CookBook();
		cookBook.setId(1);
		List<CookBook> list = cookBookDao.getList(cookBook);
		System.out.println("list:" + list);
	}
	
	@Test
	public void getCookBookIdDao() {
		List<CookBook> list = cookBookDao.getListByUserId(117);
		System.out.println("list:" + list);
	}
	
	@Test
	public void insertCoo() {
	/*	Map<Integer, ConfigFood> configFoodMap = configService.getConfigFood();*/
		/*System.out.println("configFoodMap:" + configFoodMap.size());
		Set<Entry<Integer, ConfigFood>> entrySet = configFoodMap.entrySet();*/
		
		/*List<CookBook> arrayList = new ArrayList<CookBook>();
		for (Entry<Integer, ConfigFood> entry : entrySet) {
				ConfigFood configFood = entry.getValue();
				CookBook cookBook = new CookBook();
				cookBook.setFoodId(configFood.getFoodid());
				cookBook.setFoodQualify(configFood.getFoodqualify());
				cookBook.setId(2);
				cookBook.setMakeTime(configFood.getMaketime());
				cookBook.setMetarial(configFood.getMetarial());
				cookBook.setPrice(configFood.getPrice());
				cookBook.setProcess(0);
				cookBook.setStatus(0);
				cookBook.setStrength(configFood.getStrength());
				arrayList.add(cookBook);
			
		} */
		/*cookBookDao.addList(arrayList);
		System.out.println("cookBook init success!");*/
	}
	
	
	// --------------------------------------------------------------ServiceTest----------------------------------------
	@Autowired
	CookBookNService cookBookNService;
	@Autowired
	CookBookSerivce cookBookService;
	@Test
	public void getCookBookNService() {
		/*Map<Integer, ConfigFood> configFoodMap = configService.getConfigFood();
		System.out.println("configFoodMap:" + configFoodMap.size());
		Set<Entry<Integer, ConfigFood>> entrySet = configFoodMap.entrySet();
		
		List<CookBook> arrayList = new ArrayList<CookBook>();
		for (Entry<Integer, ConfigFood> entry : entrySet) {
			ConfigFood configFood = entry.getValue();
			CookBook cookBook = new CookBook();
			cookBook.setFoodId(configFood.getFoodid());
			cookBook.setFoodQualify(configFood.getFoodqualify());
			cookBook.setId(2);
			cookBook.setMakeTime(configFood.getMaketime());
			cookBook.setMetarial(configFood.getMetarial());
			cookBook.setPrice(configFood.getPrice());
			cookBook.setProcess(0);
			cookBook.setStatus(0);
			cookBook.setStrength(configFood.getStrength());
			arrayList.add(cookBook);
		}
		
		cookBookService.addList(arrayList);
		System.out.println("success!");*/
		
	}
	@Test
	public void getMenuAll() {
		User user = new User();
		user.setId(1);
		user.setStrength(100);
		
		MenuAll cookBook = cookBookNService.getCookBook(user);
		System.out.println("cookBook:" + cookBook);
		
		//FoodDetail foodDetail = cookBookNService.getFoodDetail(1, 101001);
		//System.out.println("foodDetail:" + foodDetail);
	}
	
	@Autowired
	InteractiveService interactiveService;
	@Test
	public void interactive() {
		User user = new User();
		user.setId(155);
		List<EmployManager> employSource = interactiveService.employSource(user);
		System.out.println(employSource);
	}
	
	@Test
	public void getStaff() {
		User user = new User();
		user.setId(155);
		List<Staff> staff = (List<Staff>) interactiveService.getStaff(user);
		System.out.println(staff);
	}
	@Test
	public void employ() {
		User user = new User();
		user.setId(155);
		/*Integer employ = interactiveService.employ(user, 1, 3);
		System.out.println(employ);*/
	}
	
	@Autowired
	com.muzhi.dao.SignboardDao SignboardDao;
	@Test
	public void getSignboard() {
		Signboard Signboard = SignboardDao.getSignboard(1);
		System.out.println(Signboard);
	}
	
	@Autowired
	AsherDao asherDao;
	@Test
	public void getAsher() {
		List<Asher> recordList = new ArrayList<>();
		recordList.add(new Asher(1, 6, 1, "1", 1, 100, 0, 6));
		recordList.add(new Asher(1, 7, 1, "1", 1, 100, 0, 7));
		asherDao.insertList(recordList);
		List<Asher> selectList = asherDao.selectList(new Asher(1, 7, 1, "1", 1, 100, 0, 6));
		System.out.println(selectList);
	}
	
	@Autowired
	ChefDao chefDao;
	@Test
	public void getChef() {
		List<Chef> recordList = new ArrayList<>();
		recordList.add(new Chef(2, 6, 1, "1", 1, 1, 100, 0, 2));
		recordList.add(new Chef(2, 7, 1, "1", 1, 1, 100, 0, 3));
		chefDao.insertList(recordList);
		List<Chef> selectList = chefDao.selectList(new Chef(1, 7, 1, "1", 1, 1, 100, 0, 2));
		System.out.println(selectList);
	}

	@Autowired
	LoginDao loginDao;
	@Test
	public void getLogin() {
		Login login = new Login();
		login.setUsername("yy20180326");
		login.setPasswd("123456");
		Integer insert = loginDao.insert(login);
		System.out.println("loginId: " + login.getId());
		Login selectOne = loginDao.selectOne("yy20180326", "123456");
		System.out.println("login: " + selectOne);
	}
	@Autowired
	UserService userService;  
	@Test
	public void loginAll() {
		/*Result userLogin = userService.userLogin("yy", "123456");
		Object data = userLogin.getData();
		System.out.println(data);*/
	}
	@Autowired
	HallService hallService;
	@Test
	public void hall() {
		/*Result userLogin = userService.userLogin("yy", "123456");
		LoginResult data = (LoginResult)userLogin.getData();
		String token = data.getToken();
		User userByToken = userService.getUserByToken(token);
		Hall hall = hallService.getHall(userByToken);
		System.out.println(hall);*/
	}
	@Test
	public void test11() {
		int num = 0;
		for (int i = 0; i < 100; i++) {
			boolean flag = RandomUtil.getIsSuccess(5);
			if(flag) {
				num++;
				continue;
			}
		}
		System.out.println(num);
	}
}
