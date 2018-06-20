package com.muzhi.model.configbean;

import com.google.gson.Gson;


/**
 * @author 
 */
public class ConfigResearch  {
    /**
     * 表格序号id（菜品研究表）
     */
    private Integer id;

    /**
     * 菜品id
     */
    private Integer foodid;

    /**
     * 菜品名称
     */
    private String foodname;

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
    private Integer usepower;

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
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

    public Integer getUsepower() {
        return usepower;
    }

    public void setUsepower(Integer usepower) {
        this.usepower = usepower;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", foodid=").append(foodid);
        sb.append(", foodname=").append(foodname);
        sb.append(", level=").append(level);
        sb.append(", reward=").append(reward);
        sb.append(", strengh=").append(strengh);
        sb.append(", time=").append(time);
        sb.append(", usepower=").append(usepower);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}