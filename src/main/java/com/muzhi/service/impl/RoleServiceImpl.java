package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.muzhi.dao.RestaurantDao;
import com.muzhi.model.CookBook;
import com.muzhi.model.Food;
import com.muzhi.model.Restaurant;
import com.muzhi.model.SaleFoodInfo;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.model.configbean.ConfigRole;
import com.muzhi.model.vo.RoleVO;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.PublicityService;
import com.muzhi.service.RoleService;
import com.muzhi.service.SaleFoodInfoService;
import com.muzhi.service.config.InitConfig;

/**
 * @author ykw
 * @version 创建时间：2018年3月27日 下午5:22:12
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private CookBookSerivce cookBookSerivce;
	@Autowired
	private SaleFoodInfoService saleFoodInfoService;
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private PublicityService publicityService;
	
	@Override
	public List<RoleVO> getRoles(User user) {

		Restaurant res = restaurantDao.selectByPrimaryKey(user.getId());// 用户餐厅信息
		// 根据配置表，用户餐厅面积判断用户大厅的客人数
		int roleNum = this.getRoleNum(res);

		List<CookBook> cookBooks = cookBookSerivce.getListByUserId(user.getId());// 可制作食谱列表

		List<ConfigRole> configRoles = InitConfig.getInstance().getRoleList();// 角色配置表（后期可以通过excle读取）

		List<RoleVO> list = new ArrayList<RoleVO>();
		int totalFame = publicityService.getTotalFame(user.getId());
		int fameLevel = publicityService.getFameLevel(totalFame);
		for (int i = 0; i < roleNum; i++) {
			SaleFoodInfo oneSaleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), i + 1);
			if (null == oneSaleFoodInfo) {
				CookBook cb =CookBook.getCookBook(cookBooks, fameLevel);
				ConfigRole configRole = ConfigRole.getConfigRole(configRoles);// 随机角色
				Food food = new Food();
				food.setFoodID(cb.getFoodId());
				food.setFoodQualify(cb.getFoodQualify());
				food.setId(user.getId());
				food.setNum(1);

				RoleVO role = new RoleVO();
				role.setId(configRole.getRoleid());
				role.setName(configRole.getDescribe());
				role.setRoleType(1);
				role.setSeatNumber(i + 1);
				role.setFood(food);
				role.setShopType(1);
				list.add(role);

				SaleFoodInfo saleFoodInfo = new SaleFoodInfo();
				saleFoodInfo.setCookbookId(cb.getFoodId());
				saleFoodInfo.setCookbookLevel(cb.getFoodQualify());
				saleFoodInfo.setIndex(role.getSeatNumber());
				saleFoodInfo.setRoleId(role.getId());
				// 菜品的售价规则 菜品售价=基础售价×品质系数×（豪华度加成+名气加成）×彩蛋事件加成+彩蛋事件加成。
				// 现在暂定基础品质为1，品质每+1，售价+10% 如品质2为售价提高10%，品质3收售价提高20%
				int add = (cb.getFoodQualify() - 1) * 10;
				Integer price = cb.getPrice() * (100 + add) / 100;
				saleFoodInfo.setCurrentprice(price);
				saleFoodInfo.setIscompliment(0);
				saleFoodInfo.setIsdiscount(0);
				saleFoodInfo.setIsriseprice(0);
				saleFoodInfo.setRefusenumber(0);
				saleFoodInfo.setSellcount(0);
				saleFoodInfo.setUserId(user.getId());
				saleFoodInfoService.insertSaleFoodInfo(saleFoodInfo);
			} else {
				Food food = new Food();
				food.setFoodID(oneSaleFoodInfo.getCookbookId());
				food.setFoodQualify(oneSaleFoodInfo.getCookbookLevel());
				food.setId(user.getId());
				food.setNum(1);

				RoleVO role = new RoleVO();
				role.setId(oneSaleFoodInfo.getRoleId());
				role.setName("gg");
				role.setRoleType(1);
				role.setSeatNumber(i + 1);
				role.setFood(food);
				role.setShopType(1);
				list.add(role);
			}
		}
		return list;
	}

	/**
	 * 根据配置表，用户餐厅面积判断用户大厅的客人数
	 * 
	 * @param configRestaurant
	 * @param res
	 * @return
	 */
	public int getRoleNum(Restaurant res) {
		int roleNum = 0;
		ConfigRestaurant configRes = InitConfig.getInstance().getRestaurantMap().get(res.getLevel());
		if (configRes.getArea() == 1 || configRes.getArea() == 2) {
			roleNum = 2;
		}
		if (configRes.getArea() == 3) {
			roleNum = 3;
		}
		if (configRes.getArea() == 4 || configRes.getArea() == 5) {
			roleNum = 4;
		}
		if (configRes.getArea() == 6) {
			roleNum = 5;
		}
		return roleNum;
	}
}
