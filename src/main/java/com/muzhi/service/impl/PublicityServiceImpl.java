package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.PropDao;
import com.muzhi.dao.PublicityDao;
import com.muzhi.dao.RankDao;
import com.muzhi.dao.UserDao;
import com.muzhi.model.Asher;
import com.muzhi.model.FameInfo;
import com.muzhi.model.Prop;
import com.muzhi.model.Publicity;
import com.muzhi.model.PublicityPage;
import com.muzhi.model.Rank;
import com.muzhi.model.Result;
import com.muzhi.model.Reused;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFacilities;
import com.muzhi.model.configbean.Configadvertise;
import com.muzhi.model.configbean.Configfame;
import com.muzhi.model.vo.PublicityVO;
import com.muzhi.service.AsherService;
import com.muzhi.service.PublicityService;
import com.muzhi.service.SignboardService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.RandomUtil;
import com.muzhi.util.ResultUtil;

/**
* @author ykw
* @version 创建时间：2018年6月6日 下午3:38:05
*/
@Service
public class PublicityServiceImpl implements PublicityService {
	@Autowired
	private UserService userService;
	@Autowired
	private PublicityDao publicityDao;
	@Autowired
	private SignboardService signboardService;
	@Autowired
	private PropDao propDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	RankDao rankDao;
	@Autowired
	private AsherService asherService;

	private Logger logger = LoggerFactory.getLogger(PublicityServiceImpl.class);

	@Override
	public Object getPublicityPage(String token) {
		User user = userService.getUserByToken(token);
		if(null==user) {
			return new Result(500, "用户登录已过期", null, null, null);
		}
		
		Map<Integer, Configadvertise> configAdvertise = InitConfig.getInstance().getAdvertiseMap();
		
		//获取宣传行为信息
		List<PublicityVO> publicityList = getPublicies(configAdvertise, user);
		
		int totalfame =getTotalFame(user.getId());//总名气
		
		int fameLevel =getFameLevel(totalfame);//名气等级
		
		int nextFame = -1;//下级需要名气
		Configfame configfame = InitConfig.getInstance().getFameMap().get(fameLevel+1);
		if(null!=configfame) {
			nextFame = configfame.getFameNum();
		}
		
		Integer initFame = InitConfig.getInstance().getFameMap().get(fameLevel).getFameNum();//当前等级初始名气
		
		PublicityPage result = new PublicityPage(fameLevel,totalfame , nextFame,initFame, publicityList);

		return result;
	}
	
	@Override
	public Object publicity(String token, Integer type) {
		User user = userService.getUserByToken(token);
		if(null==user) {
			return new Result(500, "用户登录已过期", null, null, null);
		}
		
		Map<Integer, Configadvertise> configAdvertise = InitConfig.getInstance().getAdvertiseMap();
		Configadvertise configadvertise = configAdvertise.get(type);
		Publicity publicity = publicityDao.selectByPublicityId(user.getId(), type);
		
		Reused reused = new Reused(configadvertise.getFreeProp());
		Prop prop = propDao.selectByPrimaryKey(user.getId(),reused.getId() );
		
		//宣传消耗(是否免费？-->是否有道具？-->花费金币或者钻石)
		if(publicity.getFreeTime()>0) {
			publicity.setFreeTime(publicity.getFreeTime()-1);
			//publicityDao.updateByPrimaryKey(publicity);
		}else if(null!=prop && prop.getNumber()>0) {
			prop.setNumber(prop.getNumber()-1);
			propDao.updateByPrimaryKey(prop);
		}else{
			Reused cost = new Reused(configadvertise.getCost());
			if(cost.getId().equals(1)) {//花费金币
				if(user.getGold()<cost.getNumber()) {
					return new Result(1004, "金币不足", null, null, null);
				}
				user.setGold(user.getGold()-cost.getNumber());
				userDao.updateUser(user);
			}
			if(cost.getId().equals(2)) {//花费钻石
				if(user.getDiamond()<cost.getNumber()) {
					return new Result(1111, "钻石不足", null, null, null);
				}
				user.setDiamond(user.getDiamond()-cost.getNumber());
				userDao.updateUser(user);
			}
		}
		
		//宣传名气值加成，并获取返回信息
		Map<String,Object> result = publicityAddFame(configadvertise, publicity);
		
		return new Result(0, null, result, null, user);
	}

	@Override
	public void publicityInit(User userInit) {
		try {
			Map<Integer, Configadvertise> configAdvertise = InitConfig.getInstance().getAdvertiseMap();
			for (Integer i : configAdvertise.keySet()) {
				Configadvertise configadvertise = configAdvertise.get(i);
				Publicity publicity = new Publicity();
				publicity.setUid(userInit.getId());
				publicity.setFame(0);
				publicity.setPublicityId(configadvertise.getId());
				publicity.setFreeTime(configadvertise.getFreeNum());
				publicityDao.insert(publicity);
			}
		} catch (Exception e) {
			logger.info("宣传数据初始化失败");
			e.printStackTrace();
		}
	}

	@Override
	public Result getFameInfo(String token) {
		User user = userService.getUserByToken(token);
		Integer publicityFame = getPublicityFame(user.getId());
		Integer level = signboardService.getSignboard(user.getId()).getLevel();
		ConfigFacilities configFacilities = InitConfig.getInstance().getFacilitiesMap().get(Integer.parseInt(4+""+level));
		
		FameInfo fameInfo = new FameInfo();
		fameInfo.setSheShi(configFacilities.getAttribute());
		fameInfo.setXuanChuang(publicityFame);
		//要改
		int YuanGong = 0;
		List<Asher> selectByUid = asherService.selectByUid(user.getId());
		for (Asher asher : selectByUid) {
			user = userService.getUser(asher.getEmployid());
			if (user != null) {
				YuanGong += user.getLevel() * 100;
			}
		}
		fameInfo.setYuanGong(YuanGong);
		fameInfo.setTotalFame(configFacilities.getAttribute() + publicityFame + YuanGong);
		int famelevel = getFameLevel(configFacilities.getAttribute() + publicityFame + YuanGong);
		Configfame thisLevelFame = InitConfig.getInstance().getFameMap().get(famelevel);
		Configfame nextLevelFame = InitConfig.getInstance().getFameMap().get(famelevel + 1);
		fameInfo.setFameLevel(famelevel);
		fameInfo.setNextLevelFame(nextLevelFame);
		fameInfo.setThisLevelFame(thisLevelFame);
		
		return ResultUtil.success(fameInfo);
	}

	/**
	 * 获取总的名气
	 * 
	 * @return
	 */
	@Override
	public int getTotalFame(Integer uid) {

		Integer level = signboardService.getSignboard(uid).getLevel();
		ConfigFacilities configFacilities = InitConfig.getInstance().getFacilitiesMap()
				.get(Integer.parseInt(4 + "" + level));

		int sheshi = configFacilities.getAttribute();
		int publicityFame = getPublicityFame(uid);
		int YuanGong = 0;

		List<Asher> selectByUid = asherService.selectByUid(uid);
		for (Asher asher : selectByUid) {
			User user = userService.getUser(asher.getEmployid());
			if (user != null) {
				YuanGong += user.getLevel() * 100;
			}
		}
		return sheshi + publicityFame + YuanGong;

	}

	/**
	 * 获取名气等级
	 * 
	 * @return
	 */
	@Override
	public int getFameLevel(Integer totalfame) {
		int level =-1;
		List<Configfame> fameList = InitConfig.getInstance().getFameList();
		for (Configfame configfame : fameList) {
			if (totalfame<configfame.getFameNum()) {
				level= configfame.getFameLevel()-1;
				if (level==0) {
					level=1;
				}
				break;
			}
		}
		if (level==-1) {
			level =fameList.size();
		}
		return level;

	}
	/**
	 * 获取宣传信息
	 */
	public List<PublicityVO> getPublicies(Map<Integer, Configadvertise> configAdvertise,User user) {
		List<PublicityVO> publicityList = new ArrayList<PublicityVO>();
		Rank rank = rankDao.selectByPrimaryKey(user.getId());
		
		for (Integer i : configAdvertise.keySet()) {
			PublicityVO publicities = new PublicityVO();
			Configadvertise configadvertise = configAdvertise.get(i);
			publicities.setType(configadvertise.getId());
			publicities.setFameUp(configadvertise.getAddFame());

			if(rank.getRateLevel()>=configadvertise.getNeedPingJi()) {
				publicities.setOpenLevel(-1);
			}else {
				publicities.setOpenLevel(configadvertise.getNeedPingJi());
			}
			
			Publicity publicity = publicityDao.selectByPublicityId(user.getId(), configadvertise.getId());
			
			//免费次数道具
			Reused reused = new Reused(configadvertise.getFreeProp());
			
			Prop prop = propDao.selectByPrimaryKey(user.getId(),reused.getId() );
			
			//宣传需求道具(是否免费？-->是否有道具？-->花费金币或者钻石)
			if(publicity.getFreeTime()>0) {//如果存在免费次数，则需求道具为免费
				publicities.setNeedProp(new Reused("0,0,0"));
			}else if(null!=prop && prop.getNumber()>0) {//如果有免费次数道具，则需求道具为免费次数道具
				publicities.setNeedProp(reused);
			}else{//需求道具为配置表里的  花费需求
				publicities.setNeedProp(new Reused(configadvertise.getCost()));
			}
			publicityList.add(publicities);
		}
		return publicityList;
	}
	
	public Map<String,Object> publicityAddFame(Configadvertise configadvertise,Publicity publicity) {
		Map<String,Object> result = new HashMap<String,Object>();
		int fame = 0;
		boolean isSuccess = RandomUtil.getIsSuccess(configadvertise.getSuccessRate());
		if(isSuccess) {//宣传成功
			boolean isCrit = RandomUtil.getIsSuccess(configadvertise.getCritRate());
			if(isCrit) {//成功并且暴击
				fame+=configadvertise.getAddFame()*1.5;
				result.put("describe", configadvertise.getCritTip());
				result.put("state", 3);
			}else {//成功没有暴击
				fame+=configadvertise.getAddFame();
				result.put("describe", configadvertise.getSucessTip());
				result.put("state", 2);
			}
		}else {//宣传失败
			fame+=configadvertise.getAddFame()*0.5;
			result.put("describe", configadvertise.getFailTip());
			result.put("state", 1);
		}
		result.put("addFame", fame);
		publicity.setFame(publicity.getFame()+fame);
		publicityDao.updateByPrimaryKey(publicity);
		
		return result;
	}
	
	@Override
	public Integer getPublicityFame(Integer uid) {
		Integer num = publicityDao.getPublicityFame(uid);
		if(null==num) {
			return 0;
		}
		return num;
	}
}
