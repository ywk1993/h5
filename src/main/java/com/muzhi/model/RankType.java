package com.muzhi.model;

import java.util.List;

/**
* @author ykw
* @version 创建时间：2018年6月13日 下午5:28:10
*/
public class RankType {
	/**
	 * 区域排行
	 */
	private List<Ranks> areaRanks;
	/**
	 * 省份排行
	 */
	private List<Ranks> provinceRanks;
	/**
	 * 好友排行
	 */
	private List<Ranks> friendRanks;
	/**
	 * 个人区域排行
	 */
	private Ranks areaSelf;
	/**
	 * 个人省份排行
	 */
	private Ranks provinceSelf;
	/**
	 * 个人好友排行
	 */
	private Ranks friendSelf;
	
	public RankType() {
		super();
	}
	
	public RankType(List<Ranks> areaRanks, List<Ranks> provinceRanks, List<Ranks> friendRanks,Ranks areaSelf,Ranks provinceSelf,Ranks friendSelf) {
		super();
		this.areaRanks = areaRanks;
		this.provinceRanks = provinceRanks;
		this.friendRanks = friendRanks;
		this.areaSelf = areaSelf;
		this.provinceSelf = provinceSelf;
		this.friendSelf = friendSelf;
	}
	
	public RankType(List<Ranks> areaRanks, List<Ranks> provinceRanks, List<Ranks> friendRanks) {
		super();
		this.areaRanks = areaRanks;
		this.provinceRanks = provinceRanks;
		this.friendRanks = friendRanks;
	}

	public Ranks getAreaSelf() {
		return areaSelf;
	}

	public void setAreaSelf(Ranks areaSelf) {
		this.areaSelf = areaSelf;
	}

	public Ranks getProvinceSelf() {
		return provinceSelf;
	}

	public void setProvinceSelf(Ranks provinceSelf) {
		this.provinceSelf = provinceSelf;
	}

	public Ranks getFriendSelf() {
		return friendSelf;
	}

	public void setFriendSelf(Ranks friendSelf) {
		this.friendSelf = friendSelf;
	}

	public List<Ranks> getAreaRanks() {
		return areaRanks;
	}
	public void setAreaRanks(List<Ranks> areaRanks) {
		this.areaRanks = areaRanks;
	}
	public List<Ranks> getProvinceRanks() {
		return provinceRanks;
	}
	public void setProvinceRanks(List<Ranks> provinceRanks) {
		this.provinceRanks = provinceRanks;
	}
	public List<Ranks> getFriendRanks() {
		return friendRanks;
	}
	public void setFriendRanks(List<Ranks> friendRanks) {
		this.friendRanks = friendRanks;
	}
	
}
