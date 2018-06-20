package com.muzhi.model.configbean;

import com.google.gson.Gson;

/**
 * @author 
 */
public class ConfigFacilities  {


	/**
     * id
     */
    private Integer id;

    /**
     * 设施id
     */
    private Integer facilitiesid;

    /**
     * 设施等级
     */
    private Integer level;

    /**
     * 属性
     */
    private Integer attribute;

    /**
     * 升级需要的金币
     */
    private Integer needgold;

    /**
     * 升级需要的餐厅等级
     */
    private Integer needlevel;

 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacilitiesid() {
        return facilitiesid;
    }

    public void setFacilitiesid(Integer facilitiesid) {
        this.facilitiesid = facilitiesid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getNeedgold() {
        return needgold;
    }

    public void setNeedgold(Integer needgold) {
        this.needgold = needgold;
    }

    public Integer getNeedlevel() {
        return needlevel;
    }

    public void setNeedlevel(Integer needlevel) {
        this.needlevel = needlevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", facilitiesid=").append(facilitiesid);
        sb.append(", level=").append(level);
        sb.append(", attribute=").append(attribute);
        sb.append(", needgold=").append(needgold);
        sb.append(", needlevel=").append(needlevel);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}