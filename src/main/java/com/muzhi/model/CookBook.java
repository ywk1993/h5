package com.muzhi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.Configfame;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.RandomUtil;

/**
 * 菜谱
 * @author yany
 *
 */
public class CookBook {
	/**
	 * 用户id
	 */
	private Integer id;
	/**
	 * 菜品id
	 */
	private Integer foodId;
	/**
	 * 菜品品质
	 */
	private Integer foodQualify;
	/**
	 * 制作时间
	 */
	private Integer makeTime;
	/**
	 * 食材
	 */
	private String metarial;
	/**
	 * 需求厨力
	 */
	private Integer strength;
	/**
	 * 售价
	 */
	private Integer price;
	/**
	 * 当前已制作
	 */
	private Integer process;
	/**
	 * 菜品状态
	 */
	private Integer status;
	/**
	 * 研究等级
	 */
    private Integer researchLevel;
	
	public CookBook() {
		super();
	}


	
	public CookBook(Integer id, Integer foodId, Integer foodQualify, Integer makeTime, String metarial,
			Integer strength, Integer price, Integer process, Integer status, Integer researchLevel) {
		super();
		this.id = id;
		this.foodId = foodId;
		this.foodQualify = foodQualify;
		this.makeTime = makeTime;
		this.metarial = metarial;
		this.strength = strength;
		this.price = price;
		this.process = process;
		this.status = status;
		this.researchLevel = researchLevel;
	}

    

	public Integer getResearchLevel() {
		return researchLevel;
	}



	public void setResearchLevel(Integer researchLevel) {
		this.researchLevel = researchLevel;
	}



	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}


	public Integer getFoodQualify() {
		return foodQualify;
	}

	public void setFoodQualify(Integer foodQualify) {
		this.foodQualify = foodQualify;
	}

	public Integer getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Integer makeTime) {
		this.makeTime = makeTime;
	}

	public String getMetarial() {
		return metarial;
	}



	public void setMetarial(String metarial) {
		this.metarial = metarial;
	}



	public Integer getStrength() {
		return strength;
	}



	public void setStrength(Integer strength) {
		this.strength = strength;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 随机获取食谱
	 * @param list
	 * @return
	 */
	public static CookBook getCookBook(List<CookBook> list) {
		Random random = new Random();
		int i = random.nextInt(list.size());
		return list.get(i);
	}
	/**
	 * 随机获取食谱根据宣传的效果随机制作的食物
	 * @param list
	 * @return
	 */
	public static CookBook getCookBook(List<CookBook> list,Integer fameLevel) {
		
		Map<Integer, Configfame> fameMap = InitConfig.getInstance().getFameMap();
		Configfame configfame = fameMap.get(fameLevel);
		Integer random = RandomUtil.getInteger(configfame.getRandom1()+configfame.getRandom2()+configfame.getRandom3()+configfame.getRandom4());//随机值
		
		//获取优先的等级
		int cookbooklevel =0;
		if (configfame.getRandom1()>random) {
			cookbooklevel=configfame.getCookbook1();
		}else if ((configfame.getRandom1()+configfame.getRandom2())>random) {
			cookbooklevel=configfame.getCookbook2();
		}else if ((configfame.getRandom1()+configfame.getRandom2()+configfame.getRandom3())>random) {
			cookbooklevel=configfame.getCookbook3();
		}else {
			cookbooklevel=configfame.getCookbook4();
		}
		
		List<CookBook> newlist =new ArrayList<>();
		if (cookbooklevel!=0) {
			Map<Integer, ConfigFood> foodMap = InitConfig.getInstance().getFoodMap();
			for (CookBook cookBook : list) {
				Integer foodlevel = foodMap.get(cookBook.getFoodId()).getFoodlevel();
				if (foodlevel.equals(cookbooklevel)) {
					newlist.add(cookBook);
				}
			}
		}else {
			Map<Integer, ConfigFood> foodMap = InitConfig.getInstance().getFoodMap();
			for (CookBook cookBook : list) {
				Integer foodlevel = foodMap.get(cookBook.getFoodId()).getFoodlevel();
				if (!foodlevel.equals(configfame.getRandom1())||!foodlevel.equals(configfame.getRandom2())||!foodlevel.equals(configfame.getRandom3())) {
					newlist.add(cookBook);
				}
			}
		}
		
		if (newlist.isEmpty()) {
			return getCookBook(list);
		}else {
			return getCookBook(newlist);
		}
	}
}
