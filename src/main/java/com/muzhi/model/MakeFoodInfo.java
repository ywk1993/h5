package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class MakeFoodInfo implements Serializable {
    /**
     * 用户id(厨师位信息)
     */
    private Integer id;

    /**
     * 厨师状态
     */
    private Integer status;

    /**
     * 厨师位置
     */
    private Integer index;

    /**
     * 食物id
     */
    private Integer foodId;
    /**
     * 品质等级
     */
    private Integer foodQualify;

    private static final long serialVersionUID = 1L;
    
    
    public Integer getFoodQualify() {
		return foodQualify;
	}

	public void setFoodQualify(@TaskMarker(method = TaskMethod.MAKE_X_QUALIFY_Y, xValue="foodQualify")Integer foodQualify) {
		this.foodQualify = foodQualify;
	}

	public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(id);
        sb.append(", status=").append(status);
        sb.append(", index=").append(index);
        sb.append(", foodId=").append(foodId);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}