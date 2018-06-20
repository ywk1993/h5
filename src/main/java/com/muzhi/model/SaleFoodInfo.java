package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class SaleFoodInfo implements Serializable {
    /**
     * 用户id（此表主要用来存储交易时的状态的）
     */
    private Integer id;

    /**
     * 食谱id
     */
    private Integer cookbookId;

    /**
     * 吧台位置
     */
    private Integer index;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 当前吧台的食物的等级
     */
    private Integer cookbookLevel;

    /**
     * 是否已经打过折 0否1是
     */
    private Integer isdiscount;

    /**
     * 是否已经涨价 0否1是
     */
    private Integer isriseprice;

    /**
     * 是否已经恭维过 0否1是
     */
    private Integer iscompliment;

    /**
     * 当前拒绝时候需要爱心增值
     */
    private Integer refusenumber;

    /**
     * 当前价格
     */
    private Integer currentprice;

    /**
     * 促销次数
     */
    private Integer sellcount;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer id) {
        this.id = id;
    }

    public Integer getCookbookId() {
        return cookbookId;
    }

    public void setCookbookId(Integer cookbookId) {
        this.cookbookId = cookbookId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCookbookLevel() {
        return cookbookLevel;
    }

    public void setCookbookLevel(Integer cookbookLevel) {
        this.cookbookLevel = cookbookLevel;
    }

    public Integer getIsdiscount() {
        return isdiscount;
    }

    public void setIsdiscount(Integer isdiscount) {
        this.isdiscount = isdiscount;
    }

    public Integer getIsriseprice() {
        return isriseprice;
    }

    public void setIsriseprice(Integer isriseprice) {
        this.isriseprice = isriseprice;
    }

    public Integer getIscompliment() {
        return iscompliment;
    }

    public void setIscompliment(Integer iscompliment) {
        this.iscompliment = iscompliment;
    }

    public Integer getRefusenumber() {
        return refusenumber;
    }

    public void setRefusenumber(Integer refusenumber) {
        this.refusenumber = refusenumber;
    }

    public Integer getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(Integer currentprice) {
        this.currentprice = currentprice;
    }

    public Integer getSellcount() {
        return sellcount;
    }

    public void setSellcount(Integer sellcount) {
        this.sellcount = sellcount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(id);
        sb.append(", cookbookId=").append(cookbookId);
        sb.append(", index=").append(index);
        sb.append(", roleId=").append(roleId);
        sb.append(", cookbookLevel=").append(cookbookLevel);
        sb.append(", isdiscount=").append(isdiscount);
        sb.append(", isriseprice=").append(isriseprice);
        sb.append(", iscompliment=").append(iscompliment);
        sb.append(", refusenumber=").append(refusenumber);
        sb.append(", currentprice=").append(currentprice);
        sb.append(", sellcount=").append(sellcount);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}