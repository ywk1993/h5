package com.muzhi.service.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.muzhi.model.configbean.ConfigFacilities;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigLove;
import com.muzhi.model.configbean.ConfigAdvancedProp;
import com.muzhi.model.configbean.ConfigArticle;
import com.muzhi.model.configbean.ConfigCookcenter;
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
import com.muzhi.service.ConfigService;
import com.muzhi.service.MakeFoodInfoService;
import com.muzhi.service.exception.LoadException;
import com.muzhi.task.MakeStateJobManager;
import com.muzhi.util.ImportExeclUtil;
import com.muzhi.util.LogStrBuffer;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

@Component
public class InitConfig implements ApplicationListener<ApplicationContextEvent> {

	private volatile long lastmodify = 0;
	private volatile boolean flag = true;
	private Thread sqlThread = null;
	private Map<String, List<Object>> maps;
	/**
	 * 设施id和设施等级对应唯一的数据 使用示例 key =Integer.parseInt(configFacilitie.getFacilitiesid()
	 * + "" + configFacilitie.getLevel());
	 * InitConfig.getInstance.getfacilitiesMap().get(key);
	 */
	private Map<Integer, ConfigFacilities> facilitiesMap;// 命名约定 包含类名除去config的部分可以不区分大小写
	/**
	 * 设施id 对应多条不同等级的数据list存储 key =configFacilitie.getFacilitiesid();
	 * InitConfig.getInstance.getfacilitiesMap().get(key);
	 */
	private Map<Integer, List<ConfigFacilities>> facilitiesListMap;
	/**
	 * 唯一的食物id对应唯一的配置数据
	 */
	private Map<Integer, ConfigFood> foodMap;

	/**
	 * 熟练度奖励食物id 对应多条不同等级的数据list存储
	 */
	private Map<Integer, List<ConfigSkill>> skillListMap;

	/**
	 * 数练度奖励食物id 对应多条不同等级的数据list存储
	 */
	private Map<Integer, List<ConfigStrength>> strengthListMap;

	/**
	 * 菜品id和研究等级等级对应唯一的数据 使用示例
	 */
	private Map<Integer, ConfigResearch> researchMap;
	/**
	 * 菜品id 对应多条不同等级的数据list存储
	 */
	private Map<Integer, List<ConfigResearch>> researchListMap;
	/**
	 * 餐厅等级对应唯一的数据
	 */
	private Map<Integer, ConfigRestaurant> restaurantMap;
	/**
	 * 美食研究中心等级对应唯一的数据
	 */
	private Map<Integer, ConfigCookcenter> cookcenterMap;
	/**
	 * 建筑id和等级等级对应唯一的数据 使用示例
	 */
	private Map<Integer, ConfigManor> manorMap;
	/**
	 * 建筑id 对应多条不同等级的数据list存储
	 */
	private Map<Integer, List<ConfigManor>> manorListMap;
	/**
	 * 建筑id和等级等级对应唯一的数据 使用示例
	 */
	private Map<Integer, ConfigResources> resourcesMap;
	/**
	 * 建筑id 对应多条不同等级的数据list存储
	 */
	private Map<Integer, List<ConfigResources>> resourcesListMap;
	/**
	 * 根据任务等级获取任务数据
	 */
	private Map<Integer, List<ConfigOrder>> orderListMap;
	/**
	 * 根据任务表的tid获取任务数据
	 */
	private Map<Integer, ConfigOrder> orderMap;
	/**
	 * 根据id获取摆件信息
	 */
	private Map<Integer, ConfigArticle> articleMap;
	/**
	 * 根据任务id获取数据
	 */
	private Map<Integer, ConfigTask> taskMap;
	/**
	 * 根据评级等级获取数据
	 */
	private Map<Integer, ConfigRate> rateMap;
	/**
	 * 根据道具id获取数据
	 */
	private Map<Integer, ConfigAdvancedProp> advancedpropMap;
	/**
	 * 根据彩品的等级获取爱心的奖励值
	 */
	private Map<Integer, ConfigLove> loveMap;
	/**
	 * 角色
	 */
	private List<ConfigRole> roleList;
	/**
	 * 宣传编号
	 */
	private Map<Integer, Configadvertise> advertiseMap;
	/**
	 * 名气等级
	 */
	private Map<Integer, Configfame> fameMap;
	/**
	 * 名气等级
	 */
	private List<Configfame> fameList;

	@Autowired
	private MakeFoodInfoService makeFoodInfoService;
	@Autowired
	private ConfigService configService;

	// 私有的构造方法
	private InitConfig() {

	}

	// 静态公共的获取实例方法
	public static InitConfig getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {

		if (event instanceof ContextRefreshedEvent && event.getApplicationContext().getParent() == null) {
			try {
				LogStrBuffer.startLogging(LogStrBuffer.logLevelInfor, LogStrBuffer.LogTypeFile);
				LogStrBuffer.addInforLog("-----所有Bean载入完成---");
				sqlThread = new Thread(new SqlHandler());
				load();
				flag = true;
				Thread thread = new Thread(new Polling());
				thread.setName("init");
				// thread.setDaemon(true);
				thread.start();
				LogStrBuffer.flushLog();
			} catch (Exception e) {
				System.out.println("配置文件加载错误>>>>>>>>>>>");
				e.printStackTrace();
				System.exit(0);
			}

		}

		if (event instanceof ContextClosedEvent && event.getApplicationContext().getParent() == null) {
			try {
				makeFoodInfoService.soloveBug();
				AbandonedConnectionCleanupThread.shutdown();
				LogStrBuffer.LogStrMap.remove();
				MakeStateJobManager.scheduler.shutdown();
				flag = false;
				Thread.sleep(1000);
			} catch (SchedulerException | InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("停止>>>>>>>>>>>>>>");
		}

	}

	// 加载数据
	private void load() throws Exception {

		URL url = this.getClass().getClassLoader().getResource("/excelfile");
		String filePath = "";
		if (url == null) {// 单元测试和项目启动类加载不一样 导致单元测试是不能在编译后的目录中找到配置文件
			String projectURL = System.getProperty("user.dir");
			filePath = projectURL + "\\src\\main\\resources\\excelfile";

		} else {
			filePath = url.getPath();
		}
		Map<String, String> map = getProperties();
		File file = new File(filePath);
		lastmodify = file.lastModified();// 当前版本的名称eg:config.xls
		File[] files = file.listFiles();
		InitConfig config = InitConfig.getInstance();
		Method[] methods = config.getClass().getMethods();
		int count = 0;
		maps = new HashMap<>();
		for (File file2 : files) {
			String className = map.get(file2.getName().substring(0, file2.getName().length() - 5));
			if (className != null) {
				try {
					InputStream in = new FileInputStream(file2);
					Workbook wb = ImportExeclUtil.chooseWorkbook(file2.getName(), in);
					Object object = Class.forName(className).newInstance();
					List<Object> readDateListT = ImportExeclUtil.readDateListT(0, wb, object, 2, 0);
					for (Method method : methods) {
						method.setAccessible(true);
						if (method.getName().startsWith("set") && method.getName().toLowerCase()
								.contains(object.getClass().getSimpleName().substring(6).toLowerCase())) {
							method.invoke(config, readDateListT);
						}
					}
					count++;
					maps.put(className, readDateListT);
				} catch (Exception e) {
					System.out.println(className+"：加载项目失败");
				}
			}
		}
		if (map.size() != count) {
			System.out.println("请检查相关配置:" + "\r\n" + "应该加载数量:" + map.size() + "  " + "实际加载数量:" + count);
			throw new LoadException();
		}
		try {
			sqlThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 读取配置文件
	public Map<String, String> getProperties() throws Exception {

		URL url = this.getClass().getClassLoader().getResource("/resource/config.properties");
		String filePath = "";
		if (url == null) {// 单元测试和项目启动类加载不一样 导致单元测试是不能在编译后的目录中找到配置文件
			String projectURL = System.getProperty("user.dir");
			filePath = projectURL + "\\src\\main\\resources\\resource\\config.properties";

		} else {
			filePath = url.getPath();
		}
		InputStream in = new FileInputStream(new File(filePath));
		Properties properties = new Properties();
		properties.load(in);
		Set<String> stringPropertyNames = properties.stringPropertyNames();
		Map<String, String> map = new HashMap<>();
		for (String string : stringPropertyNames) {
			map.put(string, properties.getProperty(string));
		}
		return map;

	}

	public static void main(String[] args) throws Exception {
		Map<String, String> properties = InitConfig.getInstance().getProperties();
		String substring = "爱心.xlsx".substring(0, "爱心.xlsx".length() - 5);
		System.out.println(substring);
		for (Entry<String, String> string : properties.entrySet()) {
			System.out.println(string.getKey() + ">>>" + string.getValue());
		}
		System.out.println("com.muzhi.model.configbean.ConfigDialog".substring(27));
	}

	// 修改文件重新加载可以不重启服务器
	public class Polling implements Runnable {

		@Override
		public void run() {
			while (flag) {
				try {
					Thread.sleep(5000);
					URL url = this.getClass().getClassLoader().getResource("/excelfile");
					if (url != null) {
						String filePath = url.getPath();
						File file = new File(filePath);
						// System.out.println(file.lastModified()+"?????????????????????????????????????????????");
						File[] files = file.listFiles();
						if (files.length != 0 && lastmodify != file.lastModified()) {
							load();
							System.out.println("-----重新加载完成>>>>>>>>>>---");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("重新加载失败>>>>>>>");
				}
			}
		}

	}

	// 同步数据库处理
	public class SqlHandler implements Runnable {

		@Override
		public void run() {
			for (Entry<String, List<Object>> entry : maps.entrySet()) {
				try {
					Method[] methods = configService.getClass().getMethods();
					for (Method method2 : methods) {
						if (method2.getName().equals("insert" + entry.getKey().substring(27))) {
							method2.setAccessible(true);
							method2.invoke(configService, entry.getValue());
						}
					}	
				} catch (Exception e) {
					System.out.println(entry.getKey().substring(27)+"数据更新异常");
					e.printStackTrace();
				}
			}
			System.out.println("数据更新完成");
		}

	}

	// 枚举单例
	private static enum Singleton {
		INSTANCE;
		private InitConfig initConfig;

		private Singleton() {
			initConfig = new InitConfig();
		}

		public InitConfig getInstance() {
			return initConfig;
		}

	}

	// ConfigFacilities封装设施配置表
	public Map<Integer, ConfigFacilities> getFacilitiesMap() {
		if (facilitiesMap.isEmpty()) {
			setFacilitiesMap(configService.getConfigFacilities());
		}
		return facilitiesMap;
	}

	public void setFacilitiesMap(List<ConfigFacilities> configfacilities) {
		Map<Integer, ConfigFacilities> facilitiesMap = new HashMap<>(512, 0.75f);
		for (ConfigFacilities configFacilitie : configfacilities) {
			Integer key = Integer.parseInt(configFacilitie.getFacilitiesid() + "" + configFacilitie.getLevel());
			facilitiesMap.put(key, configFacilitie);
		}
		this.facilitiesMap = facilitiesMap;
	}

	public Map<Integer, List<ConfigFacilities>> getFacilitiesListMap() {
		if (facilitiesListMap.isEmpty()) {
			setFacilitiesListMap(configService.getConfigFacilities());
		}
		return facilitiesListMap;
	}

	public void setFacilitiesListMap(List<ConfigFacilities> configfacilities) {
		Map<Integer, List<ConfigFacilities>> facilitiesListMap = new HashMap<>(32, 0.75f);
		for (ConfigFacilities configFacilitie : configfacilities) {
			if (facilitiesListMap.containsKey(configFacilitie.getFacilitiesid())) {
				facilitiesListMap.get(configFacilitie.getFacilitiesid()).add(configFacilitie);
			} else {
				List<ConfigFacilities> list = new ArrayList<>();
				list.add(configFacilitie);
				facilitiesListMap.put(configFacilitie.getFacilitiesid(), list);
			}
		}
		this.facilitiesListMap = facilitiesListMap;
	}

	// 食物属性配置
	public Map<Integer, ConfigFood> getFoodMap() {
		if (foodMap.isEmpty()) {
			setFoodMap(configService.getConfigFood());
		}
		return foodMap;
	}

	public void setFoodMap(List<ConfigFood> configfoods) {
		Map<Integer, ConfigFood> foodMap = new HashMap<>(128, 0.75f);
		for (ConfigFood configFood : configfoods) {
			foodMap.put(configFood.getFoodid(), configFood);
		}
		this.foodMap = foodMap;
	}

	// 熟练度属性配置
	public Map<Integer, List<ConfigSkill>> getSkillListMap() {
		if (skillListMap.isEmpty()) {
			setSkillListMap(configService.getConfigSkill());
		}
		return skillListMap;
	}

	public void setSkillListMap(List<ConfigSkill> configskills) {
		Map<Integer, List<ConfigSkill>> skillListMap = new HashMap<>(512, 0.75f);
		for (ConfigSkill configskill : configskills) {
			if (skillListMap.containsKey(configskill.getFoodid())) {
				skillListMap.get(configskill.getFoodid()).add(configskill);
			} else {
				List<ConfigSkill> list = new ArrayList<>();
				list.add(configskill);
				skillListMap.put(configskill.getFoodid(), list);
			}
		}
		this.skillListMap = skillListMap;
	}

	// 厨力属性配置
	public Map<Integer, List<ConfigStrength>> getStrengthListMap() {
		if (strengthListMap.isEmpty()) {
			setStrengthListMap(configService.getConfigStrength());
		}
		return strengthListMap;
	}

	public void setStrengthListMap(List<ConfigStrength> Strengths) {
		Map<Integer, List<ConfigStrength>> strengthListMap = new HashMap<>(512, 0.75f);
		for (ConfigStrength strength : Strengths) {
			if (strengthListMap.containsKey(strength.getFoodid())) {
				strengthListMap.get(strength.getFoodid()).add(strength);
			} else {
				List<ConfigStrength> list = new ArrayList<>();
				list.add(strength);
				strengthListMap.put(strength.getFoodid(), list);
			}
		}
		this.strengthListMap = strengthListMap;
	}

	// 菜品研究配置
	public Map<Integer, ConfigResearch> getResearchMap() {
		if (researchMap.isEmpty()) {
			setResearchMap(configService.getConfigResearch());
		}
		return researchMap;
	}

	public void setResearchMap(List<ConfigResearch> researchlists) {
		Map<Integer, ConfigResearch> researchMap = new HashMap<>(512, 0.75f);
		for (ConfigResearch researchlist : researchlists) {
			Integer key = Integer.parseInt(researchlist.getFoodid() + "" + researchlist.getLevel());
			researchMap.put(key, researchlist);
		}
		this.researchMap = researchMap;
	}

	public Map<Integer, List<ConfigResearch>> getResearchListMap() {
		if (researchListMap.isEmpty()) {
			setResearchListMap(configService.getConfigResearch());
		}
		return researchListMap;
	}

	public void setResearchListMap(List<ConfigResearch> researchlists) {
		Map<Integer, List<ConfigResearch>> researchListMap = new HashMap<>(512, 0.75f);
		for (ConfigResearch researchlist : researchlists) {
			if (researchListMap.containsKey(researchlist.getFoodid())) {
				researchListMap.get(researchlist.getFoodid()).add(researchlist);
			} else {
				List<ConfigResearch> list = new ArrayList<>();
				list.add(researchlist);
				researchListMap.put(researchlist.getFoodid(), list);
			}
		}
		this.researchListMap = researchListMap;
	}

	// 餐厅配置
	public Map<Integer, ConfigRestaurant> getRestaurantMap() {
		if (restaurantMap.isEmpty()) {
			setRestaurantMap(configService.getConfigRestaurant());
		}
		return restaurantMap;
	}

	public void setRestaurantMap(List<ConfigRestaurant> restaurantlists) {
		Map<Integer, ConfigRestaurant> restaurantMap = new HashMap<>(512, 0.75f);
		for (ConfigRestaurant restaurantlist : restaurantlists) {
			restaurantMap.put(restaurantlist.getLevel(), restaurantlist);
		}
		this.restaurantMap = restaurantMap;
	}

	// 美食中心
	public Map<Integer, ConfigCookcenter> getCookcenterMap() {
		if (cookcenterMap.isEmpty()) {
			setCookcenterMap(configService.getConfigCookcenter());
		}
		return cookcenterMap;
	}

	public void setCookcenterMap(List<ConfigCookcenter> foodcenterlists) {
		Map<Integer, ConfigCookcenter> foodcenterMap = new HashMap<>(512, 0.75f);
		for (ConfigCookcenter foodcenterlist : foodcenterlists) {
			foodcenterMap.put(foodcenterlist.getLevel(), foodcenterlist);
		}
		this.cookcenterMap = foodcenterMap;
	}

	// 庄园配置
	public Map<Integer, ConfigManor> getManorMap() {
		if (manorMap.isEmpty()) {
			setManorMap(configService.getConfigManor());
		}
		return manorMap;
	}

	public void setManorMap(List<ConfigManor> manorlists) {
		Map<Integer, ConfigManor> manorMap = new HashMap<>(512, 0.75f);
		for (ConfigManor manorlist : manorlists) {
			Integer key = Integer.parseInt(manorlist.getBuildid() + "" + manorlist.getLevel());
			manorMap.put(key, manorlist);
		}
		this.manorMap = manorMap;
	}

	public Map<Integer, List<ConfigManor>> getManorListMap() {
		if (manorListMap.isEmpty()) {
			setManorListMap(configService.getConfigManor());
		}
		return manorListMap;
	}

	public void setManorListMap(List<ConfigManor> manorlists) {
		Map<Integer, List<ConfigManor>> manorListMap = new HashMap<>(512, 0.75f);
		for (ConfigManor manorlist : manorlists) {
			if (manorListMap.containsKey(manorlist.getBuildid())) {
				manorListMap.get(manorlist.getBuildid()).add(manorlist);
			} else {
				List<ConfigManor> list = new ArrayList<>();
				list.add(manorlist);
				manorListMap.put(manorlist.getBuildid(), list);
			}
		}
		this.manorListMap = manorListMap;
	}

	// 资源建筑配置
	public Map<Integer, ConfigResources> getResourcesMap() {
		if (resourcesMap.isEmpty()) {
			setResourcesMap(configService.getConfigResources());
		}
		return resourcesMap;
	}

	public void setResourcesMap(List<ConfigResources> resourceslists) {
		Map<Integer, ConfigResources> resourcesMap = new HashMap<>(512, 0.75f);
		for (ConfigResources resourceslist : resourceslists) {
			Integer key = Integer.parseInt(resourceslist.getBuildid() + "" + resourceslist.getLevel());
			resourcesMap.put(key, resourceslist);
		}
		this.resourcesMap = resourcesMap;
	}

	public Map<Integer, List<ConfigResources>> getResourcesListMap() {
		if (resourcesListMap.isEmpty()) {
			setResourcesListMap(configService.getConfigResources());
		}
		return resourcesListMap;
	}

	public void setResourcesListMap(List<ConfigResources> resourceslists) {
		Map<Integer, List<ConfigResources>> resourcesListMap = new HashMap<>(512, 0.75f);
		for (ConfigResources resourceslist : resourceslists) {
			if (resourcesListMap.containsKey(resourceslist.getBuildid())) {
				resourcesListMap.get(resourceslist.getBuildid()).add(resourceslist);
			} else {
				List<ConfigResources> list = new ArrayList<>();
				list.add(resourceslist);
				resourcesListMap.put(resourceslist.getBuildid(), list);
			}
		}
		this.resourcesListMap = resourcesListMap;
	}

	// 订单配置数据
	public Map<Integer, List<ConfigOrder>> getOrderListMap() {
		if (orderListMap.isEmpty()) {
			setOrderListMap(configService.getConfigOrder());
		}
		return orderListMap;
	}

	public void setOrderListMap(List<ConfigOrder> orderlists) {
		Map<Integer, List<ConfigOrder>> orderListMap = new HashMap<>(512, 0.75f);
		for (ConfigOrder orderlist : orderlists) {
			if (orderListMap.containsKey(orderlist.getTaskLevel())) {
				orderListMap.get(orderlist.getTaskLevel()).add(orderlist);
			} else {
				List<ConfigOrder> list = new ArrayList<>();
				list.add(orderlist);
				orderListMap.put(orderlist.getTaskLevel(), list);
			}
		}
		this.orderListMap = orderListMap;
	}

	public Map<Integer, ConfigOrder> getOrderMap() {
		if (orderMap.isEmpty()) {
			setOrderMap(configService.getConfigOrder());
		}
		return orderMap;
	}

	public void setOrderMap(List<ConfigOrder> orderlists) {
		Map<Integer, ConfigOrder> orderMap = new HashMap<>(1024, 0.75f);
		for (ConfigOrder configOrder : orderlists) {
			orderMap.put(configOrder.getId(), configOrder);
		}
		this.orderMap = orderMap;
	}

	// 摆件信息
	public Map<Integer, ConfigArticle> getArticleMap() {
		if (articleMap.isEmpty()) {
			setArticleMap(configService.getConfigArticle());
		}
		return articleMap;
	}

	public void setArticleMap(List<ConfigArticle> configlists) {
		Map<Integer, ConfigArticle> articleMap = new HashMap<>(512, 0.75f);
		for (ConfigArticle configlist : configlists) {
			articleMap.put(configlist.getId(), configlist);
		}
		this.articleMap = articleMap;
	}

	// 评级系统 任务配置 评级配置
	public Map<Integer, ConfigTask> getTaskMap() {
		if (taskMap.isEmpty()) {
			setTaskMap(configService.getConfigTask());
		}
		return taskMap;
	}

	public void setTaskMap(List<ConfigTask> tasklists) {
		Map<Integer, ConfigTask> taskMap = new HashMap<>(32, 0.75f);
		for (ConfigTask tasklist : tasklists) {
			taskMap.put(tasklist.getId(), tasklist);
		}
		this.taskMap = taskMap;
	}

	public Map<Integer, ConfigRate> getRateMap() {
		if (rateMap.isEmpty()) {
			setRateMap(configService.getConfigRate());
		}
		return rateMap;
	}

	public void setRateMap(List<ConfigRate> ratelists) {
		Map<Integer, ConfigRate> rateMap = new HashMap<>(32, 0.75f);
		for (ConfigRate ratelist : ratelists) {
			rateMap.put(ratelist.getRateLevel(), ratelist);
		}
		this.rateMap = rateMap;
	}

	// 高级道具配置
	public Map<Integer, ConfigAdvancedProp> getAdvancedpropMap() {
		if (advancedpropMap.isEmpty()) {
			setAdvancedpropMap(configService.getConfigAdvancedProp());
		}
		return advancedpropMap;
	}

	public void setAdvancedpropMap(List<ConfigAdvancedProp> advancedproplists) {
		Map<Integer, ConfigAdvancedProp> advancedpropMap = new HashMap<>();
		for (ConfigAdvancedProp configAdvancedProp : advancedproplists) {
			advancedpropMap.put(configAdvancedProp.getId(), configAdvancedProp);
		}
		this.advancedpropMap = advancedpropMap;
	}

	// 爱心配置
	public Map<Integer, ConfigLove> getLoveMap() {
		if (loveMap.isEmpty()) {
			setLoveMap(configService.getConfigLove());
		}
		return loveMap;
	}

	public void setLoveMap(List<ConfigLove> loves) {
		Map<Integer, ConfigLove> loveMap = new HashMap<>();
		for (ConfigLove love : loves) {
			loveMap.put(love.getFoodLevel(), love);
		}
		this.loveMap = loveMap;
	}

	// 角色
	public List<ConfigRole> getRoleList() {
		if (roleList.isEmpty()) {
			setRoleList(configService.getConfigRole());
		}
		return roleList;
	}

	public void setRoleList(List<ConfigRole> roleList) {
		this.roleList = roleList;
	}

	// 宣传名气
	public Map<Integer, Configadvertise> getAdvertiseMap() {
		if (advertiseMap.isEmpty()) {
			setAdvertiseMap(configService.getConfigadvertise());
		}
		return advertiseMap;
	}

	public void setAdvertiseMap(List<Configadvertise> advertiselist) {
		Map<Integer, Configadvertise> advertiseMap = new HashMap<>();
		for (Configadvertise configadvertise : advertiselist) {
			advertiseMap.put(configadvertise.getId(), configadvertise);
		}
		this.advertiseMap = advertiseMap;
	}

	public Map<Integer, Configfame> getFameMap() {
		if (fameMap.isEmpty()) {
			setFameMap(configService.getConfigfame());
		}
		return fameMap;
	}

	public void setFameMap(List<Configfame> famelist) {
		Map<Integer, Configfame> fameMap = new HashMap<>();
		for (Configfame configfame : famelist) {
			fameMap.put(configfame.getFameLevel(), configfame);
		}
		this.fameMap = fameMap;
	}

	public List<Configfame> getFameList() {
		if (fameList.isEmpty()) {
			setFameList(configService.getConfigfame());
		}
		return fameList;
	}

	public void setFameList(List<Configfame> fameList) {
		this.fameList = fameList;
	}

}
