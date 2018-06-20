package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.CookBookDao;
import com.muzhi.model.Constant;
import com.muzhi.model.CookBook;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigStrength;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.RandomUtil;

@Service
public class CookBookServiceImpl implements CookBookSerivce {

	@Autowired
	CookBookDao cookBookDao;
	@Autowired
    UserService userService;

	@Override
	public List<CookBook> getList(CookBook cookBook) {
		return cookBookDao.getList(cookBook);
	}

	@Override
	public void addList(List<CookBook> cookbookList) {
		cookBookDao.addList(cookbookList);
	}

	@Override
	public void cookBookInit(User user) {
		Map<Integer, ConfigFood> configFoodMap = InitConfig.getInstance().getFoodMap();
		//Map<Integer, ConfigFood> configFoodMap = configService.getConfigFood();
		System.out.println("configFoodMap:" + configFoodMap.size());
		Set<Entry<Integer, ConfigFood>> entrySet = configFoodMap.entrySet();
		List<CookBook> arrayList = new ArrayList<CookBook>();
		for (Entry<Integer, ConfigFood> entry : entrySet) {
			if (entry.getValue().getFoodid().equals(101001)) {//entry.getValue().getFoodid().toString().endsWith("001")&&entry.getValue().getFoodid()!=106001) {
				ConfigFood configFood = entry.getValue();
				CookBook cookBook = new CookBook();
				cookBook.setFoodId(configFood.getFoodid());
				cookBook.setFoodQualify(configFood.getFoodqualify());
				cookBook.setId(user.getId());
				cookBook.setMakeTime(configFood.getMaketime());
				cookBook.setMetarial(configFood.getMetarial());
				cookBook.setPrice(configFood.getPrice());
				cookBook.setProcess(0);
				cookBook.setStatus(0);
				cookBook.setStrength(configFood.getStrength());
				cookBook.setResearchLevel(0);
				arrayList.add(cookBook);
			}

		}
		cookBookDao.addList(arrayList);
		System.out.println("cookBook init success!");
	}

	@Override
	public CookBook getOneCookbook(Integer userId, Integer bookId) {

		return cookBookDao.getOneCookbook(userId, bookId);
	}

	@Override
	public List<CookBook> getListByUserId(Integer id) {

		return cookBookDao.getListByUserId(id);
	}

	@Override
	public void updateCookBook(CookBook cookBook) {
		cookBookDao.updateCookBook(cookBook);

	}

	@Override
	public Integer getAddQuality(Integer userId, Integer bookId) {
		CookBook oneCookbook = getOneCookbook(userId, bookId);
		int add =0;
		if (oneCookbook.getFoodQualify()<Constant.QUALITYLEVEL) {
			List<ConfigStrength> list = InitConfig.getInstance().getStrengthListMap().get(bookId);
			int indexNumber = getIndex(list, userService.getTotalStrength(userService.getUser(userId)));
			if (indexNumber != -1) {
				Integer crit = list.get(indexNumber).getCrit();
				Integer integer = RandomUtil.getInteger(100);
				if (integer<=crit) {
					add=list.get(indexNumber).getAddition();
				}	
			}
		}
		return add;
	}

	// 返回区间
	private int getIndex(List<ConfigStrength> list, int skill) {
		Collections.sort(list);
		int a = -1;
		for (int i = 0; i < list.size(); i++) {
			if (skill < list.get(0).getStrength()) {
				break;
			}
			if (i != list.size() - 1&&list.get(i).getStrength() <= skill && skill < list.get(i + 1).getStrength()) {
				a = i;
				break;
			}
			if (i == list.size()-1) {
				a = i;
				break;
			}
		}
		return a;

	}

}
