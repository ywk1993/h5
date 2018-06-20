package com.muzhi.model;
/**
* @author ykw
* @version 创建时间：2018年6月13日 下午5:36:45
*/
public class Ranks {
	/**
	 * 用户id
	 */
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 排行数值
	 */
	private int rankValue;
	/**
	 * 排行名次
	 */
	private int rowNum;
	/**
	 * 头像
	 */
	private String imgUrl;
	
	public Ranks() {
		super();
	}
	
	public Ranks(int id,String name, int rankValue, int rowNum, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.rankValue = rankValue;
		this.rowNum = rowNum;
		this.imgUrl = imgUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRankValue() {
		return rankValue;
	}
	public void setRankValue(int rankValue) {
		this.rankValue = rankValue;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
