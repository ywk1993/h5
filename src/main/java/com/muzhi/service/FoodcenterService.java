package com.muzhi.service;

import com.muzhi.model.Foodcenter;
import com.muzhi.model.Result;

public interface FoodcenterService {
	public int deleteByPrimaryKey(Integer id);

	public int insert(Foodcenter record);

	public int insertSelective(Foodcenter record);

	public Foodcenter selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(Foodcenter record);

	public int updateByPrimaryKey(Foodcenter record);
	/**
	 * 菜谱研究详情页
	 * @param token
	 * @return
	 */
	public Result getBookResearch(String  token);
	/**
	 * 研究
	 * @param token
	 * @param foodID
	 * @return
	 */
	public Result doBookResearch(String  token,int foodID);
	/**
	 * 取消菜品研究
	 * @param token
	 * @param foodID
	 * @return
	 */
	public Result cancerBookResearch(String  token,int foodID);
	/**
	 * 图鉴
	 * @param token
	 * @return
	 */
	public Result getDraw(String token);
	/**
	 * 研究升级加速
	 * @param token
	 * @return
	 */
	public Result addSpeed(String token);
	/**
	 * 美食中心初始化
	 * @param userId
	 */
	public void initFoodcenter(Integer userId);
}
