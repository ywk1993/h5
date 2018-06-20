package com.muzhi.model.configbean;

import com.google.gson.Gson;

/**
 * @author 
 */
public class ConfigManor  {
    /**
     * 表格id（庄园建筑）
     */
    private Integer id;

    /**
     * 建筑id
     */
    private Integer buildid;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 金币消耗
     */
    private String needgold;

    /**
     * 升级时间
     */
    private Integer time;

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildid() {
        return buildid;
    }

    public void setBuildid(Integer buildid) {
        this.buildid = buildid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getNeedgold() {
        return needgold;
    }

    public void setNeedgold(String needgold) {
        this.needgold = needgold;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", buildid=").append(buildid);
        sb.append(", level=").append(level);
        sb.append(", needgold=").append(needgold);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}