package com.muzhi.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.IncomeDao;
import com.muzhi.dao.OrderTaskDao;
import com.muzhi.dao.PropDao;
import com.muzhi.dao.RefreshManagerDao;
import com.muzhi.model.CurrencyType;
import com.muzhi.model.Food;
import com.muzhi.model.Income;
import com.muzhi.model.OrderCenter;
import com.muzhi.model.OrderFood;
import com.muzhi.model.OrderInfo;
import com.muzhi.model.OrderTask;
import com.muzhi.model.Prop;
import com.muzhi.model.RefreshManager;
import com.muzhi.model.Result;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigOrder;
import com.muzhi.redis.service.UserRedisService;
import com.muzhi.service.FoodService;
import com.muzhi.service.OrderCenterService;
import com.muzhi.service.OrderService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.RandomUtil;
import com.muzhi.util.ResultUtil;
import com.muzhi.util.StringUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRedisService userRedisService;
	@Autowired
	private PropDao propDao;
	@Autowired
	private RefreshManagerDao refreshManagerDao;
	@Autowired
	private FoodService foodService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private OrderCenterService orderCenterService;
	@Autowired
	private OrderTaskDao orderTaskDao;
	@Autowired
	private IncomeDao incomeDao;
	
	private Long lastTime = null;

	@Override
	public Result refreshReward(String token) {
		if (lastTime != null && System.currentTimeMillis() - lastTime <= 1000) {
			return ResultUtil.error(1112, "歇会再来刷吧");
		} else {
			lastTime = System.currentTimeMillis();
		}
		User user = userRedisService.getUser(token);
		RefreshManager refreshManager = refreshManagerDao.selectByPrimaryKey(user.getId());
		if (refreshManager.getNum() > 0) {
			refreshManager.setNum(refreshManager.getNum() - 1);
		} else {
			refreshManager.setTotleNum(refreshManager.getTotleNum() + 1);
			int reduceNum = 0;
			if (refreshManager.getTotleNum() * 10 > 200) {
				reduceNum = 200;
			} else {
				reduceNum = refreshManager.getTotleNum() * 10;
			}
			if (user.getDiamond() - reduceNum >= 0) {
				user.setDiamond(user.getDiamond() - reduceNum);
				userService.updateUser(user);
				userRedisService.setUser(token, user);
			} else {
				return ResultUtil.error(1111, "钻石不足");
			}
		}

		refreshManagerDao.updateByPrimaryKey(refreshManager);
		List<OrderTask> orderTasks = refreshOrder(user.getId());
		List<OrderInfo> orderInfos = new ArrayList<>();
		for (OrderTask orderTask : orderTasks) {
			OrderInfo orderInfo = new OrderInfo();
			ConfigOrder configOrder = InitConfig.getInstance().getOrderMap().get(orderTask.getTid());
			orderInfo.setOrderFoods(getOrderFood(orderTask.getOrderFood()));
			orderInfo.setRewards(getRewards(configOrder));
			orderInfo.setTaskLevel(configOrder.getTaskLevel());
			orderInfo.setTaskQualify(configOrder.getTaskQualify());
			orderInfo.setOrderId(orderTask.getOrderId());
			orderInfo.setType(configOrder.getType());
			orderInfo.setState(orderTask.getState());
			orderInfo.setEndTime(orderTask.getEndTime());
			orderInfo.setStartTime(orderTask.getStartTime());
			orderInfos.add(orderInfo);
		}
		return ResultUtil.success(orderInfos);
	}

	@Override
	public Result receiveReward(String token, Integer orderId) {

		User user = userService.getUserByToken(token);
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		OrderTask order = orderTaskDao.selectOneByTime(user.getId(), orderId,timeStamp);
		if (null == order) {
			return ResultUtil.error(1012, "当前订单已经过期");
		}
		boolean canReward = isCanReward(order);
		if (!canReward) {
			return ResultUtil.error(1011, "不满足订单要求");
		}
		boolean flag = order.getState() == 0 ? false : true;// 对应订单的领取状态1完成，0未完成
		if (flag) {
			return ResultUtil.error(1010, "已经领取过奖励");
		}

		ConfigOrder configOrder = InitConfig.getInstance().getOrderMap().get(order.getTid());
		// 减少库存
		reduceFood(order);

		// 奖励金钱 钻石奖励 订单表取
		int rewardGold = configOrder.getGoldReward();
		user.setGold(user.getGold() + rewardGold);
		int rewardzuanshi = configOrder.getZuanshiReward();
		user.setDiamond(user.getDiamond() + rewardzuanshi);
		userService.updateUser(user);
		userRedisService.setUser(token, user);

		try {
			Income income = incomeDao.selectByPrimaryKey(user.getId());
			income.setLastIncome(income.getLastIncome()+rewardGold);
			incomeDao.updateByPrimaryKey(income);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("增加收益出错");
		}
		
		// 高级食材奖励
		List<Integer> splitHandle = StringUtil.splitHandle(configOrder.getMainReward());
		Prop prop = propDao.selectByPrimaryKey(user.getId(), splitHandle.get(0));
		if (prop == null) {
			propDao.insert(new Prop(user.getId(), splitHandle.get(0), splitHandle.get(1)));
		} else {
			prop.setNumber(prop.getNumber() + splitHandle.get(1));
			propDao.updateByPrimaryKey(prop);
		}

		userService.updateUser(user);
		userRedisService.setUser(token, user);
		// 返回订单列表 改变订单列表的完成状态
		order.setState(1);
		orderTaskDao.update(order);

		List<OrderTask> orderList = orderTaskDao.selectByTime(user.getId(),timeStamp);
		boolean falg = true;// 对应所有订单的领取状态 1完成，0未完成
		for (OrderTask order2 : orderList) {
			if (order2.getState().equals(0)) {
				falg = false;
				break;
			}
		}
		if (falg) {
			orderList = refreshOrder(user.getId());
		}
		List<OrderInfo> orderInfos = new ArrayList<>();
		for (OrderTask orderTask : orderList) {
			OrderInfo orderInfo = new OrderInfo();
			configOrder = InitConfig.getInstance().getOrderMap().get(orderTask.getTid());
			orderInfo.setOrderFoods(getOrderFood(orderTask.getOrderFood()));
			orderInfo.setRewards(getRewards(configOrder));
			orderInfo.setTaskLevel(configOrder.getTaskLevel());
			orderInfo.setTaskQualify(configOrder.getTaskQualify());
			orderInfo.setOrderId(orderTask.getOrderId());
			orderInfo.setType(configOrder.getType());
			orderInfo.setState(orderTask.getState());
			orderInfo.setEndTime(orderTask.getEndTime());
			orderInfo.setStartTime(orderTask.getStartTime());
			orderInfos.add(orderInfo);
		}
		// 订单任务： 此处待业务分离
		taskService.updateTaskNum(user.getId(), TaskMethod.ORDER_X_.getTaskCondition());
		
		return ResultUtil.success(orderInfos);
	}

	@Override
	public List<OrderInfo> getOrders(Integer uid) {
		// 获取没过期的订单
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		List<OrderTask> orderTasks = orderTaskDao.selectByTime(uid,timeStamp);
		if (orderTasks == null || orderTasks.size() == 0) {// 刷新订单
			orderTasks = refreshOrder(uid);
		}
		List<OrderInfo> orderInfos = new ArrayList<>();
		for (OrderTask orderTask : orderTasks) {
			OrderInfo orderInfo = new OrderInfo();
			ConfigOrder configOrder = InitConfig.getInstance().getOrderMap().get(orderTask.getTid());
			orderInfo.setOrderFoods(getOrderFood(orderTask.getOrderFood()));
			orderInfo.setRewards(getRewards(configOrder));
			orderInfo.setTaskLevel(configOrder.getTaskLevel());
			orderInfo.setTaskQualify(configOrder.getTaskQualify());
			orderInfo.setOrderId(orderTask.getOrderId());
			orderInfo.setType(configOrder.getType());
			orderInfo.setState(orderTask.getState());
			orderInfo.setEndTime(orderTask.getEndTime());
			orderInfo.setStartTime(orderTask.getStartTime());
			orderInfos.add(orderInfo);
		}
		return orderInfos;
	}

	/**
	 * 食物需求列表
	 * 
	 * @param string
	 * @return
	 */
	private List<OrderFood> getOrderFood(String string) {
		List<OrderFood> list = new ArrayList<>();
		List<String> splitStrHandle = StringUtil.splitStrHandle(string);
		for (String string2 : splitStrHandle) {
			list.add(new OrderFood(string2));
		}
		return list;

	}

	/**
	 * 奖励列表
	 * 
	 * @param configOrder
	 * @return
	 */
	private List<CurrencyType> getRewards(ConfigOrder configOrder) {
		List<CurrencyType> list = new ArrayList<>();
		String mainReward = configOrder.getMainReward();
		Map<Integer, Integer> splitHandle = StringUtil.getCurrencyType(mainReward);
		Integer goldReward = configOrder.getGoldReward();
		Integer zuanshiReward = configOrder.getZuanshiReward();
		list.add(new CurrencyType(1, goldReward));
		list.add(new CurrencyType(2, zuanshiReward));
		for (Entry<Integer, Integer> currencyType : splitHandle.entrySet()) {
			list.add(new CurrencyType(currencyType.getKey(), currencyType.getValue()));
		}
		return list;

	}

	/**
	 * 刷新或者添加订单
	 * 
	 * @param uid
	 * @return
	 */
	private List<OrderTask> refreshOrder(Integer uid) {
		List<OrderTask> orderTasks = orderTaskDao.selectByNoTime(uid);
		
		if (orderTasks == null || orderTasks.size() == 0) {
			orderTasks = new ArrayList<>();
			for (int i = 1; i <= 6; i++) {
				OrderTask orderTask = geOrderTask(i, uid);
				orderTasks.add(orderTask);
				orderTaskDao.insert(orderTask);
			}
		} else {
			List<OrderTask> neworderTasks =new ArrayList<>();
			for (OrderTask orderTask : orderTasks) {
				orderTask = geOrderTask(orderTask.getOrderId(), uid);
				orderTaskDao.update(orderTask);
				neworderTasks.add(orderTask);
			}
			return neworderTasks;
		}
		return orderTasks;

	}

	/**
	 * 获取OrderTask
	 * 
	 * @param orderTask
	 * @return
	 */
	private OrderTask geOrderTask(Integer orderid, Integer uid) {
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		Integer orderTaskLevel = getOrderTaskLevel(uid);//随机订单等级
		ConfigOrder configOrder = getConfigOrder(InitConfig.getInstance().getOrderListMap().get(orderTaskLevel));
		OrderTask orderTask = new OrderTask();
		orderTask.setId(uid);
		orderTask.setOrderFood(getOrderFood(configOrder));
		orderTask.setOrderId(orderid);
		orderTask.setState(0);
		orderTask.setTid(configOrder.getId());
		orderTask.setStartTime(timeStamp);
		long current = System.currentTimeMillis();
		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
		// 每日的9点，12点，15点，18点，21点自动刷新
		if (System.currentTimeMillis() < zero + 9 * 3600 * 1000) {
			orderTask.setEndTime(new Timestamp(zero + 9 * 3600 * 1000));
		} else if (zero + 9 * 3600 * 1000 <= System.currentTimeMillis()
				&& System.currentTimeMillis() < zero + 12 * 3600 * 1000) {
			orderTask.setEndTime(new Timestamp(zero + 12 * 3600 * 1000));
		} else if (zero + 12 * 3600 * 1000 <= System.currentTimeMillis()
				&& System.currentTimeMillis() < zero + 15 * 3600 * 1000) {
			orderTask.setEndTime(new Timestamp(zero + 15 * 3600 * 1000));
		} else if (zero + 15 * 3600 * 1000 <= System.currentTimeMillis()
				&& System.currentTimeMillis() < zero + 18 * 3600 * 1000) {
			orderTask.setEndTime(new Timestamp(zero + 18 * 3600 * 1000));
		} else if (zero + 18 * 3600 * 1000 <= System.currentTimeMillis()
				&& System.currentTimeMillis() < zero + 21 * 3600 * 1000) {
			orderTask.setEndTime(new Timestamp(zero + 21 * 3600 * 1000));
		} else {
			orderTask.setEndTime(new Timestamp(zero + 9 * 3600 * 1000 + 24 * 3600 * 1000));
		}

		return orderTask;
	}
	/**
	 * 获取随机的任务等级 随机不会低于当前最高等级的10级
	 * @return
	 */
	private Integer getOrderTaskLevel(Integer uid) {
		OrderCenter orderCenter = orderCenterService.selectByPrimaryKey(uid);
		int a=10;//随机等级限制参数
		Integer level = orderCenter.getLevel();
		if (level<=a) {
			return RandomUtil.getInteger(level)+1;
		}else {
			level=level-a;
			return RandomUtil.getInteger(a)+1+level;
		}
	}

	/**
	 * 获取订单食物的需求
	 * 
	 * @param tid
	 * @return
	 */
	private String getOrderFood(ConfigOrder configOrder) {
		String join = "";
		List<String> splitStrHandle = StringUtil.splitStrHandle(configOrder.getFoodRound());
		List<String> newlist = new ArrayList<>();
		for (int i = 0; i < configOrder.getRequestNumber(); i++) {
			int integer = RandomUtil.getInteger(splitStrHandle.size());
			newlist.add(splitStrHandle.get(integer) + "," + getQualify(configOrder.getQualifyRound()));
			splitStrHandle.remove(integer);
		}
		join = String.join(";", newlist);
		return join;

	}
	/**
	 * 获取随机的品质
	 * 
	 * @param qualifyRound
	 * @return
	 */
	private Integer getQualify(String qualifyRound) {
		if (qualifyRound.contains(",") || qualifyRound.contains("，")) {
			String[] split;
			try {
				split = qualifyRound.split(",");
			} catch (Exception e) {
				split = qualifyRound.split("，");
			}
			int nextInt = RandomUtil.getInteger(split.length);
			return Integer.parseInt(split[nextInt]);
		} else {
			return Integer.parseInt(qualifyRound);
		}

	}

	/**
	 * 获取相应等级的订单配置
	 * 
	 * @param configOrderlist
	 * @return
	 */
	private ConfigOrder getConfigOrder(List<ConfigOrder> configOrderlist) {
		Integer integer = RandomUtil.getInteger(configOrderlist.size());
		return configOrderlist.get(integer);
	}

	/**
	 * 判断是否能够领取奖励 true表示可以领取 false表示不可以
	 * 
	 * @return
	 */
	private boolean isCanReward(OrderTask orderTask) {
		boolean flag = true;
		ConfigOrder configOrder = InitConfig.getInstance().getOrderMap().get(orderTask.getTid());
		List<OrderFood> orderFoods = getOrderFood(orderTask.getOrderFood());
		for (OrderFood orderFood : orderFoods) {
			List<Food> foods = foodService.getOrderFood(orderTask.getId(), orderFood.getFoodid(),
					orderFood.getFoodqualify());
			int total = 0;
			for (Food food : foods) {
				total += food.getNum();
			}
			if (foods == null || foods.isEmpty() || total < configOrder.getRequestNumber()) {
				flag = false;
				break;
			}
		}
		return flag;

	}

	/**
	 * 完成减少相应的库存
	 * 
	 * @param order
	 */
	private void reduceFood(OrderTask orderTask) {
		List<OrderFood> orderFoods = getOrderFood(orderTask.getOrderFood());
		for (OrderFood orderFood : orderFoods) {
			List<Food> foods = foodService.getOrderFood(orderTask.getId(), orderFood.getFoodid(),
					orderFood.getFoodqualify());
			int needNum = orderFood.getNeedNumber();
			for (int i = 0; i < foods.size() && needNum != 0; i++) {
				if (foods.get(i).getNum() >= needNum) {
					foods.get(i).setNum(foods.get(i).getNum() - needNum);
					foodService.updateFood(foods.get(i));
					break;
				} else {
					needNum -= foods.get(i).getNum();
					foods.get(i).setNum(0);
					foodService.updateFood(foods.get(i));
				}
			}
		}
	}

}
