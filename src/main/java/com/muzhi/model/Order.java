package com.muzhi.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Order extends OrderKey implements Serializable {
    /**
     * 订单等级
     */
    private Integer orderLevel;

    /**
     * 订单类型 1为食材任务，2为建材任务
     */
    private Integer orderType;

    /**
     * 菜品需求数量
     */
    private Integer typeNum;

    /**
     * 单个菜品需求数
     */
    private Integer requestNum;

    /**
     * 菜品品质范围
     */
    private String qualifyRound;

    /**
     * 任务金币奖励
     */
    private Integer goldReward;

    /**
     * 道具奖励数量
     */
    private String curencyNum;

    /**
     * 道具奖励范围
     */
    private String currencyRound;

    /**
     * 订单生成时间
     */
    private Date startTime;

    /**
     * 订单结束时间(不管完成与否）
     */
    private Date endTime;

    /**
     * 奖励领取标志（订单是否完成）1完成，0未完成
     */
    private Integer state;

    /**
     * 订单号
     */
    private String uuid;
    
    /**
     * 手动刷新后的状态 1有效， 0无效
     */
    private Integer status;
    
    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Order() {
		super();
	}

	/**
	 * @param orderLevel
	 * @param orderType
	 * @param typeNum
	 * @param requestNum
	 * @param qualifyRound
	 * @param goldReward
	 * @param curencyNum
	 * @param currencyRound
	 * @param startTime
	 * @param endTime
	 * @param state
	 * @param uuid
	 * @param status
	 */
	public Order(Integer orderLevel, Integer orderType, Integer typeNum, Integer requestNum, String qualifyRound,
			Integer goldReward, String curencyNum, String currencyRound, Date startTime, Date endTime, Integer state,
			String uuid, Integer status) {
		super();
		this.orderLevel = orderLevel;
		this.orderType = orderType;
		this.typeNum = typeNum;
		this.requestNum = requestNum;
		this.qualifyRound = qualifyRound;
		this.goldReward = goldReward;
		this.curencyNum = curencyNum;
		this.currencyRound = currencyRound;
		this.startTime = startTime;
		this.endTime = endTime;
		this.state = state;
		this.uuid = uuid;
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(Integer orderLevel) {
        this.orderLevel = orderLevel;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public Integer getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(Integer requestNum) {
        this.requestNum = requestNum;
    }

    public String getQualifyRound() {
        return qualifyRound;
    }

    public void setQualifyRound(String qualifyRound) {
        this.qualifyRound = qualifyRound;
    }

    public Integer getGoldReward() {
        return goldReward;
    }

    public void setGoldReward(Integer goldReward) {
        this.goldReward = goldReward;
    }

    public String getCurencyNum() {
        return curencyNum;
    }

    public void setCurencyNum(String curencyNum) {
        this.curencyNum = curencyNum;
    }

    public String getCurrencyRound() {
        return currencyRound;
    }

    public void setCurrencyRound(String currencyRound) {
        this.currencyRound = currencyRound;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderLevel() == null ? other.getOrderLevel() == null : this.getOrderLevel().equals(other.getOrderLevel()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getTypeNum() == null ? other.getTypeNum() == null : this.getTypeNum().equals(other.getTypeNum()))
            && (this.getRequestNum() == null ? other.getRequestNum() == null : this.getRequestNum().equals(other.getRequestNum()))
            && (this.getQualifyRound() == null ? other.getQualifyRound() == null : this.getQualifyRound().equals(other.getQualifyRound()))
            && (this.getGoldReward() == null ? other.getGoldReward() == null : this.getGoldReward().equals(other.getGoldReward()))
            && (this.getCurencyNum() == null ? other.getCurencyNum() == null : this.getCurencyNum().equals(other.getCurencyNum()))
            && (this.getCurrencyRound() == null ? other.getCurrencyRound() == null : this.getCurrencyRound().equals(other.getCurrencyRound()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderLevel() == null) ? 0 : getOrderLevel().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getTypeNum() == null) ? 0 : getTypeNum().hashCode());
        result = prime * result + ((getRequestNum() == null) ? 0 : getRequestNum().hashCode());
        result = prime * result + ((getQualifyRound() == null) ? 0 : getQualifyRound().hashCode());
        result = prime * result + ((getGoldReward() == null) ? 0 : getGoldReward().hashCode());
        result = prime * result + ((getCurencyNum() == null) ? 0 : getCurencyNum().hashCode());
        result = prime * result + ((getCurrencyRound() == null) ? 0 : getCurrencyRound().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderLevel=").append(orderLevel);
        sb.append(", orderType=").append(orderType);
        sb.append(", typeNum=").append(typeNum);
        sb.append(", requestNum=").append(requestNum);
        sb.append(", qualifyRound=").append(qualifyRound);
        sb.append(", goldReward=").append(goldReward);
        sb.append(", curencyNum=").append(curencyNum);
        sb.append(", currencyRound=").append(currencyRound);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", state=").append(state);
        sb.append(", uuid=").append(uuid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}