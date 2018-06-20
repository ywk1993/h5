package TestService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.google.gson.Gson;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigAdvancedProp;
import com.muzhi.model.configbean.ConfigArticle;
import com.muzhi.model.configbean.ConfigCookcenter;
import com.muzhi.model.configbean.ConfigFacilities;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigLove;
import com.muzhi.model.configbean.ConfigManor;
import com.muzhi.model.configbean.ConfigOrder;
import com.muzhi.model.configbean.ConfigRate;
import com.muzhi.model.configbean.ConfigResearch;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.model.configbean.ConfigRole;
import com.muzhi.model.configbean.ConfigSkill;
import com.muzhi.model.configbean.ConfigStrength;
import com.muzhi.model.configbean.ConfigTask;
import com.muzhi.model.configbean.Configadvertise;
import com.muzhi.model.configbean.Configfame;
import com.muzhi.redis.service.ResearchRedisService;
import com.muzhi.service.ConfigService;
import com.muzhi.service.SaleFoodlService;
import com.muzhi.service.SignboardService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-service-impl.xml" })
public class ServiceTest {

	private final static String TOKEN = "18bc0f03-1983-4911-8698-4b053d4431c9";
	@Autowired
	UserService userService;

	@Test
	public void userLogin() {
		//Result userLogin = userService.userLogin("yy", "123456");
		//System.out.println(new Gson().toJson(userLogin.getData()));
	}

	@Test
	public void getUserByToken() {
		User user = userService.getUserByToken(TOKEN);
		System.out.println(user);
	}

	@Autowired
	SaleFoodlService saleFoodlService;

	@Test
	public void saleInfo() {
		Result saleInfo = saleFoodlService.saleInfo(TOKEN, 1);
		System.out.println(new Gson().toJson(saleInfo));
	}

	@Test
	public void sellSale() {
		Result saleInfo = saleFoodlService.sellSale(TOKEN, 1, 1,1);
		System.out.println(new Gson().toJson(saleInfo));
	}

	@Test
	public void discountSale() {
		Result saleInfo = saleFoodlService.discountSale(TOKEN, 1);
		System.out.println(new Gson().toJson(saleInfo));
	}

	@Test
	public void risePriceSale() {
		Result saleInfo = saleFoodlService.risePriceSale(TOKEN, 1);
		System.out.println(new Gson().toJson(saleInfo));
	}
	@Autowired
	ResearchRedisService researchRedisService;
	@Test
	public void reacharredis() {
		researchRedisService.setTime(1);
		researchRedisService.expireToken(1, 1200000);
		Long expire = researchRedisService.getExpire(1);
		System.out.println(expire);
		researchRedisService.expireToken(1, 1);
	    expire = researchRedisService.getExpire(1);
		System.out.println(expire);
		
	}

	@Test
	public void complimentSale() {
		Result saleInfo = saleFoodlService.complimentSale(TOKEN, 1);
		System.out.println(new Gson().toJson(saleInfo));
	}

	@Autowired
	SignboardService signboardService;

	@Test
	public void insertSignboard() {
		User user = new User();
		user.setId(1);
		signboardService.insertSignboard(user);
	}

	@Test
	public void init() {
		/*ConfigCookcenter configCookcenter = InitConfig.getInstance().getCookcenterMapMap().get(1);
		System.out.println(configCookcenter);*/
	}
	
	@Autowired
	ConfigService configService;
	@Test
	public void configsss() {
		List<ConfigArticle> configArticle = configService.getConfigArticle();
		System.out.println(configArticle.size());
		List<ConfigCookcenter> configCookcenter = configService.getConfigCookcenter();
		System.out.println(configCookcenter.size());
		List<ConfigFacilities> configFacilities = configService.getConfigFacilities();
		System.out.println(configFacilities.size());
		List<ConfigFood> configFood = configService.getConfigFood();
		System.out.println(configFood.size());
		List<ConfigManor> configManor = configService.getConfigManor();
		System.out.println(configManor.size());
		List<ConfigOrder> configOrder = configService.getConfigOrder();
		System.out.println(configOrder.size());
		List<ConfigRate> configRate = configService.getConfigRate();
		System.out.println(configRate.size());
		List<ConfigResearch> configResearch = configService.getConfigResearch();
		System.out.println(configResearch.size());
		List<ConfigResources> configResources = configService.getConfigResources();
		System.out.println(configResources.size());
		List<ConfigRestaurant> configRestaurant = configService.getConfigRestaurant();
		System.out.println(configRestaurant.size());
		List<ConfigRole> configRole = configService.getConfigRole();
		System.out.println(configRole.size());
		List<ConfigSkill> configSkill = configService.getConfigSkill();
		System.out.println(configSkill.size());
		List<ConfigStrength> configStrength = configService.getConfigStrength();
		System.out.println(configStrength.size());
		List<ConfigTask> configTask = configService.getConfigTask();
		System.out.println(configTask.size());
		
		List<ConfigAdvancedProp> configAdvancedProp = configService.getConfigAdvancedProp();
		System.out.println(configAdvancedProp.size());
		List<ConfigLove> configLove = configService.getConfigLove();
		System.out.println(configLove.size());
		List<Configfame> configfame = configService.getConfigfame();
		System.out.println(configfame.size());
		List<Configadvertise> configadvertise = configService.getConfigadvertise();
		System.out.println(configadvertise.size());
	}
	@Test
	public void configinsert() {
		List<ConfigAdvancedProp> ConfigLoves =new ArrayList<>();
		ConfigAdvancedProp ConfigRestaurant = new ConfigAdvancedProp();
		ConfigRestaurant.setDescribe("caonima");
		ConfigRestaurant.setId(3001);
		ConfigRestaurant.setLogo("hheh");
		ConfigRestaurant.setName("hh");
		ConfigRestaurant.setPropLevel(1);
		ConfigRestaurant.setPropQualify(1);
		ConfigRestaurant.setPropType(1);
		ConfigAdvancedProp ConfigRestaurant1 = new ConfigAdvancedProp();
		ConfigRestaurant1.setDescribe("caonima");
		ConfigRestaurant1.setId(3002);
		ConfigRestaurant1.setLogo("hheh");
		ConfigRestaurant1.setName("hh");
		ConfigRestaurant1.setPropLevel(1);
		ConfigRestaurant1.setPropQualify(1);
		ConfigRestaurant1.setPropType(1);
		ConfigAdvancedProp ConfigRestaurant2 = new ConfigAdvancedProp();
		ConfigRestaurant2.setDescribe("caonima");
		ConfigRestaurant2.setId(3003);
		ConfigRestaurant2.setLogo("hheh");
		ConfigRestaurant2.setName("hh");
		ConfigRestaurant2.setPropLevel(1);
		ConfigRestaurant2.setPropQualify(1);
		ConfigRestaurant2.setPropType(1);
		ConfigLoves.add(ConfigRestaurant);
		ConfigLoves.add(ConfigRestaurant1);
		ConfigLoves.add(ConfigRestaurant2);
		configService.insertConfigAdvancedProp(ConfigLoves);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public static final int MAX_VALUE = 0xffffffff;

	static int add(int a, int b) {
		int number = 0;
		for (int i = 0; i <= a; i++) {
			if (String.valueOf(i).contains(String.valueOf(b))) {
				number++;
			}
		}
		return number;
	}

	// [1, 3, 5, 7],
	// [2, 4, 7, 8],
	// [3, 5, 9, 10]
	public static void main(String[] args) {
		int[][] matrix = { { 1, 3, 4, 7 }, { 2, 4, 7, 8 }, { 4, 5, 9, 10 } };
		int matrixCount = matrixCount(matrix, 4);
		System.out.println(matrixCount);

	}

	public static List<Interval> intervals(List<Interval> intervals, Interval newInterval) {
		List<Interval> list = new ArrayList<>();
		if (intervals.isEmpty()) {
			list.add(newInterval);
			return list;
		}
		if (newInterval == null) {
			return intervals;
		}
		for (int i = 0; i < intervals.size(); i++) {
			int x = newInterval.start;
			int y = newInterval.end;
			int a = intervals.get(i).start;
			int b = intervals.get(i).end;
			// [a,b]当前的区间 [x,y]插入的区间
			if (y < a) {// x y <a b ==>(x,y) (a,b) 入list 剩下的加入循环加入list列表跳出循环
				list.add(newInterval);
				list.addAll(intervals.subList(i, intervals.size()));
				break;
			}
			if (x < a && a <= y && y <= b) {// x <a <=y<= b ==>(x,b)入list 剩下的加入循环加入list列表跳出循环
				list.add(new Interval(x, b));
				list.addAll(intervals.subList(i + 1, intervals.size()));
				break;
			}
			if (x < a && y > b) {// x <a b< y ==>(x,y)不到循环的最后不入list继续循环 到了加入列表
				if (i != intervals.size() - 1) {
					newInterval.start = x;
					newInterval.end = y;
				} else {
					list.add(new Interval(x, y));
				}

			}
			if (x >= a && y <= b) {// a <=x y<= b ==>(a,b)入list 剩下的加入循环加入list列表跳出循环
				list.add(intervals.get(i));
				list.addAll(intervals.subList(i + 1, intervals.size()));
				break;
			}
			if (x >= a && x <= b && y > b) {// a <=x<= b <y ==>(a,y)不到循环的最后不入list继续循环 到了加入列表
				if (i != intervals.size() - 1) {
					newInterval.start = a;
					newInterval.end = y;
				} else {
					list.add(new Interval(a, y));
				}
			}
			if (x > b) {// a<b<x<y ==>(x,y) (a,b) (a,b)入list (x,y)不到循环的最后不入list继续循环 到了加入列表
				list.add(intervals.get(i));
				if (i != intervals.size() - 1) {
					newInterval.start = x;
					newInterval.end = y;
				} else {
					list.add(new Interval(x, y));
				}
			}
		}
		return list;

	}

	// [1, 3, 4, 7],
	// [2, 4, 7, 8],
	// [4, 5, 9, 10]
	public static int matrixCount(int[][] matrix, int number) {
		int count = 0;
		int end = 0;
		for (int i = 0; i < matrix.length; i++) {
			int start = 0;
			if (i == 0 || end == (-1)) {
				end = matrix[i].length;
			}
			while (start <= end) {
				int middle = (start + end) / 2;
				if (number < matrix[i][middle]) {
					end = middle - 1;
				} else if (number > matrix[i][middle]) {
					start = middle + 1;
				} else if (number == matrix[i][middle]) {
					end=middle;
					count++;
					break;
				}else {
					end=-1;
				}
			}
		}
		return count;
	}

	public static int binSearch(int srcArray[], int start, int end, int key) {
		int mid = (end - start) / 2 + start;
		if (srcArray[mid] == key) {
			return mid;
		}
		if (start >= end) {
			return -1;
		} else if (key > srcArray[mid]) {
			return binSearch(srcArray, mid + 1, end, key);
		} else if (key < srcArray[mid]) {
			return binSearch(srcArray, start, mid - 1, key);
		}
		return -1;
	}

}

class Interval {
	public int start;
	public int end;

	public Interval(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

}
