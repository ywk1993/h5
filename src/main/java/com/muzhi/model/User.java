package com.muzhi.model;

import java.io.Serializable;

/**
 * @author 
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 金币
     */
    private Integer gold;
    /**
     * 爱心值
     */
    private Integer love;
    /**
     * 经验
     */
    private Integer exp;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 亲密度
     */
    private Integer intimacy;
    /**
     * 钻石
     */
    private Integer diamond;
    /**
     * 厨力
     */
	private Integer strength;
	/**
	 * 是否可制作 1可以 0不可制作
	 */
	private Integer isMaked;

	public Integer getId() {
		return id;
	}
	
	public User() {
		super();
	}

	public User(Integer id, Integer gold, Integer love, Integer exp, Integer level, Integer intimacy, Integer diamond,
			Integer strength, Integer isMaked) {
		super();
		this.id = id;
		this.gold = gold;
		this.love = love;
		this.exp = exp;
		this.level = level;
		this.intimacy = intimacy;
		this.diamond = diamond;
		this.strength = strength;
		this.isMaked = isMaked;
	}
	
	public User(Integer gold, Integer love, Integer exp, Integer level, Integer intimacy, Integer diamond,
			Integer strength, Integer isMaked) {
		super();
		this.gold = gold;
		this.love = love;
		this.exp = exp;
		this.level = level;
		this.intimacy = intimacy;
		this.diamond = diamond;
		this.strength = strength;
		this.isMaked = isMaked;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getLove() {
		return love;
	}

	public void setLove(Integer love) {
		this.love = love;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIntimacy() {
		return intimacy;
	}

	public void setIntimacy(Integer intimacy) {
		this.intimacy = intimacy;
	}

	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getIsMaked() {
		return isMaked;
	}

	public void setIsMaked(Integer isMaked) {
		this.isMaked = isMaked;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}