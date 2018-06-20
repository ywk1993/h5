package com.muzhi.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Research extends ResearchKey implements Serializable {
    /**
     * 菜品id
     */
    private Integer foodId;

    /**
     * 菜品名称
     */
    private String foodName;

    /**
     * 研究等级
     */
    private Integer level;

    /**
     * 食材消耗
     */
    private String reward;

    /**
     * 厨力需求
     */
    private Integer strengh;

    /**
     * 研究时间
     */
    private Integer time;

    /**
     * 研究力消耗
     */
    private Integer usePower;

    private static final long serialVersionUID = 1L;

    
    public Research() {
		super();
	}

	public Research(Integer foodId, String foodName, Integer level, String reward, Integer strengh, Integer time,
			Integer usePower) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.level = level;
		this.reward = reward;
		this.strengh = strengh;
		this.time = time;
		this.usePower = usePower;
	}

	public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public Integer getStrengh() {
        return strengh;
    }

    public void setStrengh(Integer strengh) {
        this.strengh = strengh;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getUsePower() {
        return usePower;
    }

    public void setUsePower(Integer usePower) {
        this.usePower = usePower;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Research other = (Research) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getFoodId() == null ? other.getFoodId() == null : this.getFoodId().equals(other.getFoodId()))
            && (this.getFoodName() == null ? other.getFoodName() == null : this.getFoodName().equals(other.getFoodName()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getReward() == null ? other.getReward() == null : this.getReward().equals(other.getReward()))
            && (this.getStrengh() == null ? other.getStrengh() == null : this.getStrengh().equals(other.getStrengh()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getUsePower() == null ? other.getUsePower() == null : this.getUsePower().equals(other.getUsePower()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getFoodId() == null) ? 0 : getFoodId().hashCode());
        result = prime * result + ((getFoodName() == null) ? 0 : getFoodName().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getReward() == null) ? 0 : getReward().hashCode());
        result = prime * result + ((getStrengh() == null) ? 0 : getStrengh().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getUsePower() == null) ? 0 : getUsePower().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", foodId=").append(foodId);
        sb.append(", foodName=").append(foodName);
        sb.append(", level=").append(level);
        sb.append(", reward=").append(reward);
        sb.append(", strengh=").append(strengh);
        sb.append(", time=").append(time);
        sb.append(", usePower=").append(usePower);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}