package com.muzhi.model.vo;

import com.muzhi.model.SaleFoodInfo;
import com.muzhi.model.User;

/**
 * 恭维结果
 * @author yany
 *
 */
public class ComplimentResult {
	/**
	 * 恭维是否成功	0否1是
	 */
	private int isSuccess;
	/**
	 * 当前售卖物品是否已经恭维过	返回1
	 */
	private int isCompliment;
	/**
	 * 当前恭维消耗（增加）爱心值	增值
	 */
	private int incrcomplimentNumber;
	/**
	 * 当前用户的爱心数量	总值
	 */
	private int loveNumber;
	
	
	public ComplimentResult() {
		super();
	}

	public ComplimentResult(User user, SaleFoodInfo saleFoodInfo, int complimentNum) {
		this.setIsSuccess(complimentNum > 0 ? 1 : 0);
		this.setIncrcomplimentNumber(complimentNum);
		this.setIsCompliment(1);
		this.setLoveNumber(user.getLove());
		
	}

	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public int getIsCompliment() {
		return isCompliment;
	}
	public void setIsCompliment(int isCompliment) {
		this.isCompliment = isCompliment;
	}
	public int getIncrcomplimentNumber() {
		return incrcomplimentNumber;
	}
	public void setIncrcomplimentNumber(int incrcomplimentNumber) {
		this.incrcomplimentNumber = incrcomplimentNumber;
	}
	public int getLoveNumber() {
		return loveNumber;
	}
	public void setLoveNumber(int loveNumber) {
		this.loveNumber = loveNumber;
	}

}
