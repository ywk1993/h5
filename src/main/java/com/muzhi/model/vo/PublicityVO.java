package com.muzhi.model.vo;

import com.muzhi.model.Reused;

/**
* @author ykw
* @version 创建时间：2018年6月6日 下午3:03:03
*/
public class PublicityVO {
	/**
	 * 宣传类型
	 */
	private Integer type;
	/**
	 * 需求道具
	 */
	private Reused needProp;
	/**
	 * 名气加成
	 */
	private Integer fameUp;
	/**
	 * 开启等级
	 */
	private Integer openLevel;
	
	
	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Reused getNeedProp() {
		return needProp;
	}


	public void setNeedProp(Reused needProp) {
		this.needProp = needProp;
	}


	public Integer getFameUp() {
		return fameUp;
	}


	public void setFameUp(Integer fameUp) {
		this.fameUp = fameUp;
	}


	public Integer getOpenLevel() {
		return openLevel;
	}


	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}


	@Override
	public String toString() {
		return "PublicityVO [type=" + type + ", needProp=" + needProp + ", fameUp=" + fameUp + ", openLevel="
				+ openLevel + "]";
	}
}
