package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class OrderReward implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 奖励的道具id
     */
    private Integer propId;

    /**
     * 数量
     */
    private Integer number;
    /**
     * 道具类型
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
    
    
    public OrderReward() {
		super();
	}

	public OrderReward(Integer id, Integer orderId, Integer propId, Integer number, Integer type) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.propId = propId;
		this.number = number;
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPropId() {
        return propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", propId=").append(propId);
        sb.append(", number=").append(number);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}