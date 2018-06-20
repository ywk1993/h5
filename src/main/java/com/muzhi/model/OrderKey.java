package com.muzhi.model;

import java.io.Serializable;

/**
 * @author 
 */
public class OrderKey implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 订单id，id范围（1-6）
     */
    private Integer orderId;

    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
	public OrderKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param orderId
	 */
	public OrderKey(Integer id, Integer orderId) {
		super();
		this.id = id;
		this.orderId = orderId;
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
        OrderKey other = (OrderKey) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}