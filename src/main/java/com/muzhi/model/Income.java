package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class Income implements Serializable {
    /**
     *  用户id
     */
    private Integer uid;

    /**
     * 上一日收益
     */
    private Integer lastIncome;
    /**
     * 总豪华度
     */
    private Integer haohua;

    private static final long serialVersionUID = 1L;

    public Income() {
		super();
	}

	public Income(Integer uid, Integer lastIncome,Integer haohua) {
		super();
		this.uid = uid;
		this.lastIncome = lastIncome;
		this.haohua = haohua;
	}

	public Integer getHaohua() {
		return haohua;
	}

	public void setHaohua(Integer haohua) {
		this.haohua = haohua;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLastIncome() {
        return lastIncome;
    }

    public void setLastIncome(Integer lastIncome) {
        this.lastIncome = lastIncome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", lastIncome=").append(lastIncome);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}