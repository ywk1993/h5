package com.muzhi.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.CookBenchDao;
import com.muzhi.dao.LoginDao;
import com.muzhi.dao.UserDao;
import com.muzhi.model.Constant;
import com.muzhi.model.CookBench;
import com.muzhi.model.Login;
import com.muzhi.model.LoginResult;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.redis.service.impl.UserRedisImpl;
import com.muzhi.service.FriendService;
import com.muzhi.service.InitService;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.LoginService;
import com.muzhi.service.SpringContextUtils;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.ResultUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendService friendService;
	@Autowired
	private InitService initService;
	@Autowired
	private UserRedisImpl userRedisImpl;
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;
	@Autowired
	private InteractiveService interactiveService;
	@Autowired
	private CookBenchDao cookBenchDao;
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private SpringContextUtils springContextUtils;

	public static final Integer UID_CODE = 800000000;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.muzhi.service.UserService#userLogin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Result userLogin(String username, String password, String openid, String nickName, String avatarUrl) {
		Login selectOne = loginService.selectOne(username, password);
		User user = null;
		if (selectOne == null) {
			System.out.println("创建新用户: " + username);
			Integer id = loginService.insert(username, password, openid, nickName, avatarUrl);
			loginService.updateUid(id , (UID_CODE + id) + "");
			initService.loginInit(id);
			user = userDao.getUser(id);// 需要登录接口
			
		} else {
			selectOne.setOpenid(openid);
			selectOne.setNickName(selectOne.getNickName() == null ? nickName : selectOne.getNickName());
			selectOne.setAvatarUrl(selectOne.getAvatarUrl() == null ? avatarUrl : selectOne.getAvatarUrl());
			System.out.println(username + "====" + selectOne.getOpenid() + "\t" + selectOne.getNickName() + "\t" + selectOne.getAvatarUrl());
			loginService.updateObject(selectOne);
			user = userDao.getUser(selectOne.getId());// 需要登录接口
		}
		
		LoginResult loginResult = new LoginResult();
		loginResult.setIsNewUser(0);
		// 如果用户不存在 添加用户初始化数据
		// 生成token
		String token = UUID.randomUUID().toString();
		loginResult.setToken(token);
		// 把用户信息写入redis
		userRedisImpl.setUser(token, user);
		userRedisImpl.expireToken(token, Constant.TOKENEXPIRE);

		// 刷新token
		String refreshToken = UUID.randomUUID().toString();
		userRedisImpl.setRefreshUser("Refresh:" + refreshToken, user);
		userRedisImpl.expireRefreshToken("Refresh:" + refreshToken, Constant.LONGTOKEN);
		loginResult.setRefreshToken(refreshToken);

		userRedisImpl.IncrUser();
		return ResultUtil.success(loginResult);
	}

	@Override
	public User getUserByToken(String token) {
		// 根据token从redis中查询用户信息
		User user = userRedisImpl.getUser(token);
		// 判断是否为空
		if (null == user) {
			return null;
		}
		// 更新过期时间
		userRedisImpl.expireToken(token, Constant.TOKENEXPIRE);
		User u = userService.getUser(user.getId()); 
		// 返回用户信息
		return u;
	}

	@Override
	public Result refreshLogin(String refreshToken) {
		User user = userRedisImpl.getRefreshUser("Refresh:"+refreshToken);
		if (null==user) {
			return ResultUtil.error(2, "长token失效");
		}

		String token = UUID.randomUUID().toString();

		userRedisImpl.setUser(token, user);
		userRedisImpl.expireToken(token, Constant.TOKENEXPIRE);
		
		LoginResult loginResult = new LoginResult();
		loginResult.setIsNewUser(0);
		loginResult.setRefreshToken(refreshToken);
		loginResult.setToken(token);

		return ResultUtil.success(loginResult);
	}

	@Override
	public User getUser(Integer id) {
		User user = userDao.getUser(id);
		return user;
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public List<User> getUserList() {
		User user = new User();
		return userDao.selectList(user);
	}

	@Override
	public User userInit(Integer id) {
//		int friendNum = 10;
		ConfigRestaurant res = InitConfig.getInstance().getRestaurantMap().get(1);
		User user = new User(id, 100000, res.getLovelimit(), 0, 1, 0, 10000, 100, 0);
		userDao.insert(user);
//		@SuppressWarnings("unused")
//		List<Friend> friends = friendService.selectList(new Friend(null, user.getId()));
//		List<User> userList = userDao.selectList(new User());
//
//		for (User userTemp : userList) {
//			List<Friend> selectList = friendService.selectList(new Friend(userTemp.getId(), null));
//			if (selectList.size() > 0 && selectList.size() < friendNum) {
//				friendService.insert(new Friend(userTemp.getId(), user.getId()));
//				friendService.insert(new Friend(user.getId(), userTemp.getId()));
//				break;
//			} else {
//				if (userList.size() == 2) {
//					User user1 = userList.get(0);
//					User user2 = userList.get(1);
//					friendService.insert(new Friend(user1.getId(), user2.getId()));
//					friendService.insert(new Friend(user2.getId(), user1.getId()));
//					break;
//				}
//			}
//
//		}
		return user;
	}

	@Override
	public List<User> selectByIdList(List<Integer> idList) {
		return userDao.selectByIdList(idList);
	}
	
	@Override
	public Integer getTotalStrength(User user) {
		int employStrength = interactiveService.getEmployStrength(user);//雇佣厨师加的厨力
		CookBench cookBench = cookBenchDao.getCookBench(user.getId());
		int cookBenchStrength = cookBench.getAddition();//灶台厨力
		int userStrength = user.getStrength();//用户本身厨力
		
		int currentStrength = employStrength + cookBenchStrength + userStrength;
		return currentStrength;
	}

	@Override
	public String loginFromWeinxin(String openid) {
		String selectWeixin = loginService.selectWeixin(openid);
		User user = null;
		String username = null;
		if (selectWeixin == null) {
			System.out.println("创建新用户: " + openid);
			Integer id = loginService.insert(openid, "123456", openid, null, null);
			username = UID_CODE + id + "";
			loginService.updateUsername(id, username);
			loginService.updateUid(id , (UID_CODE + id) + "");
			initService.loginInit(id);
			selectWeixin = username; 
			return selectWeixin;
		} else {
			return selectWeixin;
		}
	}

	@Override
	public User getUserByUid(Integer uid) {
		Integer userId = loginDao.getUserIdByUid(uid);
		if(null!=userId) {
			return userDao.getUser(userId);
		}
		return null;
	}

}
