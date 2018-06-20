package com.muzhi.model;

import java.util.List;

import com.muzhi.model.vo.PublicityVO;

/**
* @author ykw
* @version 创建时间：2018年6月6日 下午3:00:48
*/
public class PublicityPage {
	/**
	 * 名气等级
	 */
	private int fameLevel;
	/**
	 * 当前名气
	 */
	private int fame;
	/**
	 * 下级名气
	 */
	private int nextFame;
	/**
	 * 宣传类型
	 */
	private List<PublicityVO> publicities;
	
	private Integer initFame;
	
	public PublicityPage() {
		super();
	}
	public PublicityPage(int fameLevel,int fame,int nextFame,Integer initFame,List<PublicityVO> publicities) {
		super();
		this.fameLevel = fameLevel;
		this.fame = fame;
		this.nextFame = nextFame;
		this.publicities = publicities;
		this.initFame = initFame;
	}
	
	public int getFameLevel() {
		return fameLevel;
	}
	public void setFameLevel(int fameLevel) {
		this.fameLevel = fameLevel;
	}
	public int getFame() {
		return fame;
	}
	public void setFame(int fame) {
		this.fame = fame;
	}
	public int getNextFame() {
		return nextFame;
	}
	public void setNextFame(int nextFame) {
		this.nextFame = nextFame;
	}
	public List<PublicityVO> getPublicities() {
		return publicities;
	}
	public void setPublicities(List<PublicityVO> publicities) {
		this.publicities = publicities;
	}
	public Integer getInitFame() {
		return initFame;
	}
	public void setInitFame(Integer initFame) {
		this.initFame = initFame;
	}
	@Override
	public String toString() {
		return "PublicityPage [fameLevel=" + fameLevel + ", fame=" + fame + ", nextFame=" + nextFame + ", publicities="
				+ publicities + "]";
	}
}
