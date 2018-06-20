package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.AsherDao;
import com.muzhi.dao.ChefDao;
import com.muzhi.dao.FriendDao;
import com.muzhi.dao.RestaurantDao;
import com.muzhi.dao.UserDao;
import com.muzhi.model.Asher;
import com.muzhi.model.Chef;
import com.muzhi.model.Constant;
import com.muzhi.model.Friend;
import com.muzhi.model.MakeFoodInfo;
import com.muzhi.model.Restaurant;
import com.muzhi.model.Result;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.model.vo.EmployManager;
import com.muzhi.model.vo.EmploySource;
import com.muzhi.model.vo.Staff;
import com.muzhi.model.vo.StaffAll;
import com.muzhi.service.AsherService;
import com.muzhi.service.ChefService;
import com.muzhi.service.FriendService;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.MakeFoodInfoService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;

@Service
public class InteractiveServiceImpl implements InteractiveService {

	@Autowired
	UserService userService;
	@Autowired
	FriendService friendService;
	@Autowired
	AsherService asherService;
	@Autowired
	ChefService chefService;
	@Autowired
	UserDao userDao;
	@Autowired
	ChefDao chefDao;
	@Autowired
	AsherDao asherDao;
	@Autowired
	MakeFoodInfoService makeFoodInfoService;
	@Autowired
	RestaurantDao restaurantDao;
	@Autowired
	FriendDao friendDao;
	@Autowired
	TaskService taskService;

	@Override
	public List<EmployManager> employSource(User user) {
		List<EmployManager> employManagerList = new ArrayList<EmployManager>();
		// 好友
		List<Friend> friendList = friendService.selectList(new Friend(user.getId(), null));

		List<Integer> friendIds = new ArrayList<Integer>();// 获取好友id
		for (Friend friend : friendList) {
			friendIds.add(friend.getFriendid());
		}

		// 获取好友雇佣列表
		List<EmploySource> friendEmploys = getFriends(user, friendIds);

		EmployManager employManager = new EmployManager();
		employManager.setEmploySource(friendEmploys);
		employManager.setType(1);
		employManagerList.add(employManager);
		// 所有用户
		List<User> allUserList = userService.getUserList();

		// 陌生人
		List<Integer> userIds = new ArrayList<Integer>(100);
		for (User u : allUserList) {
			userIds.add(u.getId());
		}
		userIds.removeAll(friendIds);
		userIds.remove(user.getId());

		// 获取陌生人列表
		List<EmploySource> strangers = getStrangers(user, userIds);
		EmployManager strangerManager = new EmployManager();
		strangerManager.setEmploySource(strangers);
		strangerManager.setType(2);
		employManagerList.add(strangerManager);

		// 机器人
		EmployManager robot = new EmployManager();
		robot.setEmploySource(strangers);
		robot.setType(3);
		employManagerList.add(robot);

		return employManagerList;

	}

	@Override
	public StaffAll getStaff(User user) {
		// 1、通过配置表查询用户解锁的雇用位
		int chefUnlock = 0;// 厨师开放个数
		int asherUnlock = 0;// 迎宾开放个数

		Restaurant res = restaurantDao.selectByPrimaryKey(user.getId());
		ConfigRestaurant restaurant = InitConfig.getInstance().getRestaurantMap().get(res.getLevel());
		chefUnlock = restaurant.getMaxchef();
		asherUnlock = restaurant.getMaxemploy();

		List<Staff> chefList = new ArrayList<Staff>();
		List<Staff> asherList = new ArrayList<Staff>();

		/** 自己的厨师信息，暂时不加 */
		/*
		 * Chef ownChef = chefService.selectByIndex(user.getId(),0);//自己的厨师 Staff
		 * ownStaff = new Staff(); ownStaff.setId(ownChef.getId());
		 * ownStaff.setImg("img"); ownStaff.setIndex(ownChef.getIndex());
		 * ownStaff.setLeftTime(ownChef.getLefttime());
		 * ownStaff.setLevel(user.getLevel()); chefList.add(ownStaff);
		 */
		// 厨师列表
		for (int i = 1; i <= Constant.TOTAL_UNLOACK; i++) {
			Chef chef = chefService.selectByIndex(user.getId(), i);

			Staff staff = new Staff();
			if (chefUnlock >= i)// 表示已经解锁过的厨师位
			{
				if (null != chef) {// 第一种情况，玩家有雇佣厨师
					User hireUser = userDao.getUser(chef.getEmployid());
					staff.setId(chef.getEmployid());
					staff.setImg("Img");// 应该为用户头像
					staff.setLeftTime(chef.getLefttime(chef));// 厨师的离开时间
					chefDao.updateByPrimaryKey(chef);// 修改厨师离开的时间
					staff.setLevel(hireUser.getLevel());
					staff.setStaffType(1);
					staff.setIndex(chef.getIndex());

					// 雇佣需要的金币
					Friend friend = friendDao.getOneFriend(user.getId(), chef.getEmployid());
					int discount = 8;
					if (null == friend) {
						discount = 10;
					}
					staff.setGold(hireUser.getLevel() * 1000 * discount / 10);

					// 厨师过期删除makefoodinfo厨师信息
					/*
					 * MakeFoodInfo makeFoodInfo =
					 * makeFoodInfoService.getOneMakeFoodInfo(user.getId(), i);
					 * if(0==chef.getLefttime() && null!=makeFoodInfo) {
					 * makeFoodInfoService.deleteMakeFoodInfo(user.getId(), i); }
					 */
				} else if (null == chef) {// 第二种情况，玩家解锁了位置但是没雇佣
					staff.setStaffType(2);
					staff.setIndex(i);
				}
			} else {// 表示该厨师位还没解锁
				Map<Integer,ConfigRestaurant> restaurants = InitConfig.getInstance().getRestaurantMap();
				for (Map.Entry<Integer,ConfigRestaurant> entry : restaurants.entrySet()) {
					ConfigRestaurant value = entry.getValue();
					if(value.getMaxchef().equals(i)) {
						staff.setOpenLevel(value.getLevel());
						break;
					}
				}
				staff.setStaffType(3);
				staff.setIndex(i);
			}
			chefList.add(staff);
		}

		// 迎宾列表
		for (int i = 1; i <= Constant.TOTAL_ASHERS; i++) {

			Asher asher = asherService.selectAshers(user.getId(), i);

			Staff staff = new Staff();
			if (asherUnlock >= i)// 表示已经解锁过的迎宾位
			{
				if (null != asher) {// 第一种情况，玩家有雇佣迎宾
					User hireUser = userDao.getUser(asher.getEmployid());
					staff.setId(asher.getEmployid());
					staff.setImg("Img");// 应该为用户头像
					staff.setLeftTime(asher.getLefttime(asher));// 迎宾的离开时间
					asherDao.updateByPrimaryKey(asher);// 修改迎宾剩余时间
					staff.setLevel(hireUser.getLevel());
					staff.setStaffType(1);
					staff.setIndex(asher.getIndex());

					// 雇佣需要的金币
					Friend friend = friendDao.getOneFriend(user.getId(), asher.getEmployid());
					int discount = 8;
					if (null == friend) {
						discount = 10;
					}
					staff.setGold(hireUser.getLevel() * 1000 * discount / 10);
				} else if (null == asher) {// 第二种情况，玩家解锁了位置但是没有雇佣
					staff.setStaffType(2);
					staff.setIndex(i);
				}

			} else {// 表示该厨师位还没解锁
				Map<Integer,ConfigRestaurant> restaurants = InitConfig.getInstance().getRestaurantMap();
				for (Map.Entry<Integer,ConfigRestaurant> entry : restaurants.entrySet()) {
					ConfigRestaurant value = entry.getValue();
					if(value.getMaxemploy().equals(i)) {
						staff.setOpenLevel(value.getLevel());
						break;
					}
				}
				staff.setStaffType(3);
				staff.setIndex(i);
			}
			asherList.add(staff);
		}
		StaffAll staffAll = new StaffAll(chefList, asherList, res.getLevel());
		return staffAll;

	}

	@Override
	public Result employ(User user, Integer id, Integer type, Integer index) {
		Result result = new Result();
		if (null == user || null == id || null == type || null == index) {
			result.setCode(6002);
			result.setErrMsg("参数传输出错");
		}
		// 1 厨师
		if (type == 1) {

			// 雇佣一个厨师，扣除相应的金钱
			User userTemp = userDao.getUser(id);
			int discount = 10;// 折扣
			// 如果是好友，0.8折
			List<Friend> friendList = friendService.selectList(new Friend(user.getId(), null));
			for (Friend friend : friendList) {
				if (friend.getFriendid().equals(userTemp.getId())) {
					discount = 8;
					break;
				}
			}
			// 雇佣所需要的金币
			int gold = userTemp.getLevel() * 1000 * discount / 10;
			if (user.getGold() < gold) {
				result.setCode(1004);
				result.setErrMsg("金币不足");
				return result;
			}

			// 扣除金币
			user.setGold(user.getGold() - gold);
			userDao.updateUser(user);

			Chef chef = new Chef();
			chef.setId(user.getId());
			chef.setAddition("");
			chef.setEmployid(id);
			chef.setIskeep(1);
			chef.setIsnew(0);
			chef.setIstempkeep(0);
			chef.setLefttime(24 * 3600);
			chef.setSource(1);
			chef.setIndex(index);
			chef.setCreateTime(new Date());
			List<Chef> recordList = new ArrayList<Chef>();
			recordList.add(chef);
			chefService.insertList(recordList);

			MakeFoodInfo makeFoodInfo = new MakeFoodInfo();
			makeFoodInfo.setFoodId(null);
			makeFoodInfo.setFoodQualify(null);
			makeFoodInfo.setIndex(chef.getIndex());
			makeFoodInfo.setStatus(0);
			makeFoodInfo.setUserId(user.getId());
			makeFoodInfoService.insertMakeFoodInfo(makeFoodInfo);// 雇佣一个员工添加一条做菜信息

		}

		// 2迎宾
		if (type == 2) {

			// 雇佣一个迎宾，扣除相应的金钱
			User userTemp = userDao.getUser(id);
			int discount = 10;// 折扣
			// 如果是好友，0.8折
			List<Friend> friendList = friendService.selectList(new Friend(user.getId(), null));
			for (Friend friend : friendList) {
				if (friend.getFriendid().equals(userTemp.getId())) {
					discount = 8;
					break;
				}
			}
			// 雇佣所需要的金币
			int gold = userTemp.getLevel() * 1000 * discount / 10;
			if (user.getGold() < gold) {
				result.setCode(1004);
				result.setErrMsg("金币不足");
				return result;
			}

			// 扣除金币
			user.setGold(user.getGold() - gold);
			userDao.updateUser(user);

			Asher asher = new Asher();
			asher.setId(user.getId());
			asher.setAddition("");
			asher.setEmployid(id);
			asher.setIskeep(1);
			asher.setIsnew(0);
			asher.setLefttime(24 * 3600);
			asher.setSource(1);
			asher.setIndex(index);
			asher.setCreateTime(new Date());
			List<Asher> recordList = new ArrayList<Asher>();
			recordList.add(asher);
			asherService.insertList(recordList);
		}
		result.setCode(0);
		result.setErrMsg("雇佣成功");
		result.setData(this.getStaff(user));

		// 雇佣任务, 业务待分离
		taskService.updateTaskNumAutoAddtion(user.getId(), TaskMethod.EMPLOY_X_Y_TIME.getTaskCondition(), type);

		return result;
	}

	@Override
	public Result employAgain(User user, Integer id, Integer type, Integer index) {
		Result result = new Result();
		if (null == user || null == id || null == type || null == index) {
			result.setCode(6002);
			result.setErrMsg("参数传输出错");
		}
		// 1 厨师
		if (type == 1) {

			// 雇佣一个厨师，扣除相应的金钱
			User userTemp = userDao.getUser(id);
			int discount = 10;// 折扣
			// 如果是好友，0.8折
			List<Friend> friendList = friendService.selectList(new Friend(user.getId(), null));
			for (Friend friend : friendList) {
				if (friend.getFriendid().equals(userTemp.getId())) {
					discount = 8;
					break;
				}
			}
			// 雇佣所需要的金币
			int gold = userTemp.getLevel() * 1000 * discount / 10;
			if (user.getGold() < gold) {
				result.setCode(1004);
				result.setErrMsg("金币不足");
				return result;
			}
			// 厨师时间还原
			Chef chef = chefService.selectByIndex(user.getId(), index);
			chef.setLefttime(24 * 3600);
			chef.setCreateTime(new Date());
			chefService.updateByPrimaryKey(chef);
			// 扣除金币
			user.setGold(user.getGold() - gold);
			userDao.updateUser(user);
		}

		// 2迎宾
		if (type == 2) {

			// 雇佣一个迎宾，扣除相应的金钱
			User userTemp = userDao.getUser(id);
			int discount = 10;// 折扣
			// 如果是好友，0.8折
			List<Friend> friendList = friendService.selectList(new Friend(user.getId(), null));
			for (Friend friend : friendList) {
				if (friend.getFriendid().equals(userTemp.getId())) {
					discount = 8;
					break;
				}
			}
			// 雇佣所需要的金币
			int gold = userTemp.getLevel() * 1000 * discount / 10;
			if (user.getGold() < gold) {
				result.setCode(1004);
				result.setErrMsg("金币不足");
				return result;
			}
			// 还原时间
			Asher asher = asherService.selectAshers(user.getId(), index);
			asher.setLefttime(24 * 3600);
			asher.setCreateTime(new Date());
			asherService.updateByPrimaryKey(asher);
			// 扣除金币
			user.setGold(user.getGold() - gold);
			userDao.updateUser(user);

		}
		result.setCode(0);
		result.setErrMsg("雇佣成功");
		result.setData(this.getStaff(user));
		return result;
	}

	@Override
	public void staffInit(User user) {
		// 初始化自己的厨师
		/*
		 * List<Chef> list = new ArrayList<Chef>(); Chef chef = new Chef();
		 * chef.setCreateTime(new Date()); chef.setEmployid(user.getId());
		 * chef.setId(user.getId()); chef.setIndex(0);//默认厨师index为0 chef.setIskeep(1);
		 * chef.setIsnew(1); chef.setIstempkeep(0); chef.setLefttime(-1);//自己的厨师 不会被解雇离开
		 * chef.setSource(2);//机器人 chef.setAddition("自己的厨师"); list.add(chef);
		 * chefDao.insertList(list);
		 */
	}

	@Override
	public Result dismiss(User user, Integer id, Integer type) {
		Result result = new Result();
		if (type == 1) {// 厨师
			// 判断厨师头上是否有食物，如果有食物，解雇失败
			Chef chef = chefDao.selectOneChef(user.getId(), id);

			if (null == chef || 1 == chef.getIstempkeep()) {
				result.setCode(6004);
				result.setErrMsg("请先领取食物再解雇");
				return result;
			} else {
				chefDao.deleteOneChef(user.getId(), id);
				makeFoodInfoService.deleteMakeFoodInfo(user.getId(), chef.getIndex());// 解雇员工删除该位置的做菜信息

				result.setCode(0);
				result.setErrMsg("解雇成功");
				result.setData(this.getStaff(user));
				return result;
			}
		}

		if (type == 2) {// 迎宾
			Asher asher = asherDao.selectOneAsher(user.getId(), id);
			if (null == asher) {
				result.setCode(6004);
				result.setErrMsg("迎宾不存在");
				return result;
			} else {
				asherDao.deleteOneAsher(user.getId(), id);

				result.setCode(0);
				result.setErrMsg("解雇成功");
				result.setData(this.getStaff(user));
				return result;
			}
		}
		return result;
	}

	/**
	 * 把已经雇佣的厨师从雇佣列表删除
	 * 
	 * @param idList
	 * @param chefs
	 * @return
	 */
	public List<Integer> removeList(List<Integer> idList, List<Chef> chefs, List<Asher> ashers) {
		List<Integer> removeList = new ArrayList<Integer>();
		for (Chef cf : chefs) {
			removeList.add(cf.getEmployid());
		}
		for (Asher as : ashers) {
			removeList.add(as.getEmployid());
		}
		idList.removeAll(removeList);
		return idList;
	}

	/**
	 * 好友雇佣列表
	 * 
	 * @param user
	 * @param friendList
	 * @param employManager
	 */
	public List<EmploySource> getFriends(User user, List<Integer> friendIds) {
		// 当前用户已经雇佣的厨师
		Chef chef = new Chef();
		chef.setId(user.getId());
		List<Chef> chefs = chefDao.selectList(chef);
		// 当前用户已经雇佣的迎宾
		Asher asher = new Asher();
		asher.setId(user.getId());
		List<Asher> ashers = asherDao.selectList(asher);

		// 把已经雇佣的厨师从雇佣列表删除
		List<Integer> idLists = removeList(friendIds, chefs, ashers);// 去除被雇佣后的好友id集

		List<EmploySource> employSourceList = new ArrayList<EmploySource>();
		List<User> userList = null;
		if (idLists.size() > 0) {
			userList = userService.selectByIdList(idLists);// 根据好友id查询出好友信息
			for (User userTemp : userList) {
				EmploySource employSource = new EmploySource();
				employSource.setUser(userTemp);
				employSource.setDiscount(8);
				employSource.setGold(userTemp.getLevel() * 1000 * employSource.getDiscount() / 10);
				employSourceList.add(employSource);
			}
		}

		return employSourceList;
	}

	/**
	 * 陌生人雇佣列表
	 * 
	 * @param user
	 * @param userIds
	 * @return
	 */
	public List<EmploySource> getStrangers(User user, List<Integer> userIds) {
		// 当前用户已经雇佣的厨师
		Chef chef = new Chef();
		chef.setId(user.getId());
		List<Chef> chefs = chefDao.selectList(chef);
		// 当前用户已经雇佣的迎宾
		Asher asher = new Asher();
		asher.setId(user.getId());
		List<Asher> ashers = asherDao.selectList(asher);

		List<Integer> strangerIds = removeList(userIds, chefs, ashers);

		List<EmploySource> employSourceList = new ArrayList<EmploySource>();
		List<User> strangerList = null;
		if (strangerIds.size() > 0) {
			strangerList = userService.selectByIdList(strangerIds);// 陌生人信息
			for (User userTemp : strangerList) {
				EmploySource employSource = new EmploySource();
				employSource.setUser(userTemp);
				employSource.setDiscount(10);
				employSource.setGold(userTemp.getLevel() * 1000 * employSource.getDiscount() / 10);
				employSourceList.add(employSource);
			}
		}

		return employSourceList;
	}

	@Override
	public Integer getEmployStrength(User user) {
		int addStrength = 0;
		Chef chefDto = new Chef();
		chefDto.setId(user.getId());
		List<Chef> chefs = chefDao.selectList(chefDto);
		if (null == chefs || chefs.size() < 1) {// 如果没雇佣厨师，增加厨力值为0
			return addStrength;
		}

		List<Integer> userIds = new ArrayList<Integer>();
		for (Chef chef : chefs) {
			userIds.add(chef.getEmployid());
		}
		// 被雇佣的厨师
		List<User> users = userService.selectByIdList(userIds);
		for (User u : users) {
			addStrength += (u.getLevel() * 100);
		}
		return addStrength;
	}

	@Override
	public Integer getEmployFame(User user) {
		int addFame = 0;
		Asher asherDto = new Asher();
		asherDto.setId(user.getId());
		List<Asher> ashers = asherDao.selectList(asherDto);
		if (null == ashers || ashers.size() < 1) {
			return addFame;
		}

		List<Integer> userIds = new ArrayList<Integer>();
		for (Asher asher : ashers) {
			userIds.add(asher.getEmployid());
		}
		// 被雇佣的迎宾
		List<User> users = userService.selectByIdList(userIds);

		for (User u : users) {
			addFame += (u.getLevel() * 100);
		}
		return addFame;
	}
}
