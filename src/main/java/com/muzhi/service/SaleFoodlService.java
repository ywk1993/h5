package com.muzhi.service;

import java.util.List;

import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.vo.InventoryInfo;
import com.muzhi.model.vo.PropInfo;

/**
 * 
 * @author Yuwk
 *
 * 2017年11月23日
 */
public interface SaleFoodlService {
	
	/**
	 * 拒绝客人操作
	 * @param token
	 * @return
	 */
	public Result refuseCustomer(String token,Integer foodId,Integer roleId,Integer barId);
	 
	/**
	 * 售卖信息
	 * @param token
	 * @param index
	 * @return
	 */
	public Result saleInfo(String token,Integer index);
	/**
	 * 推销食物
	 * @param token
	 * @param index
	 * @param foodId
	 * @return
	 */
	public Result sellSale(String token,Integer index,Integer foodId,Integer cookbookLevel);
	/**
	 * 打折
	 * @param token
	 * @param index
	 * @return
	 */
	public Result discountSale(String token,Integer index);
	/**
	 * 涨价
	 * @param token
	 * @param index
	 * @return
	 */
	public Result risePriceSale(String token,Integer index);
	/**
	 * 恭维
	 * @param token
	 * @param index
	 * @return
	 */
	public Result complimentSale(String token, Integer index);
	/**
	 * 卖出食物
	 * @param token
	 * @param foodId
	 * @param roleId
	 * @param index
	 * @return
	 */
	public Result saleFood(String token,Integer foodId,Integer roleId,Integer index);
	/**
	 * 库存信息
	 * @param user
	 * @return
	 */
	public InventoryInfo getAllInventory(User user);
	/**
	 * 高级食材信息
	 * @param user
	 * @return
	 */
	public List<PropInfo> getPropInfo(User user);

}
