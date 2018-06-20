package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class Ranking implements Serializable {
    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 上一日收益
     */
    private Integer income;

    /**
     * 评级
     */
    private Integer rateLevel;

    /**
     * 豪华度
     */
    private Integer haohua;

    public Ranking() {
		super();
	}

	public Ranking(Integer uid, Integer income, Integer rateLevel, Integer haohua) {
		super();
		this.uid = uid;
		this.income = income;
		this.rateLevel = rateLevel;
		this.haohua = haohua;
	}

	private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getRateLevel() {
        return rateLevel;
    }

    public void setRateLevel(Integer rateLevel) {
        this.rateLevel = rateLevel;
    }

    public Integer getHaohua() {
        return haohua;
    }

    public void setHaohua(Integer haohua) {
        this.haohua = haohua;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", income=").append(income);
        sb.append(", rateLevel=").append(rateLevel);
        sb.append(", haohua=").append(haohua);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}