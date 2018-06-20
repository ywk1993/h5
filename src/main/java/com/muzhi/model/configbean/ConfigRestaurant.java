package com.muzhi.model.configbean;

import com.google.gson.Gson;


/**
 * @author 
 */
public class ConfigRestaurant {
    /**
     * 等级（餐厅配置）
     */
    private Integer level;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 爱心上限值
     */
    private Integer lovelimit;

    /**
     * 最大厨师数
     */
    private Integer maxchef;

    /**
     * 最大雇佣迎宾数
     */
    private Integer maxemploy;

    

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getLovelimit() {
        return lovelimit;
    }

    public void setLovelimit(Integer lovelimit) {
        this.lovelimit = lovelimit;
    }

    public Integer getMaxchef() {
        return maxchef;
    }

    public void setMaxchef(Integer maxchef) {
        this.maxchef = maxchef;
    }

    public Integer getMaxemploy() {
        return maxemploy;
    }

    public void setMaxemploy(Integer maxemploy) {
        this.maxemploy = maxemploy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", level=").append(level);
        sb.append(", area=").append(area);
        sb.append(", lovelimit=").append(lovelimit);
        sb.append(", maxchef=").append(maxchef);
        sb.append(", maxemploy=").append(maxemploy);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}