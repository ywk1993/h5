package com.muzhi.model.configbean;

import com.google.gson.Gson;


/**
 * @author 
 */
public class ConfigResources  {
    /**
     * 表格id(资源建筑表）
     */
    private Integer id;

    /**
     * 建筑id
     */
    private Integer buildid;

    /**
     * 建筑等级
     */
    private Integer level;

    /**
     * 每小时产量
     */
    private Integer output;

    /**
     * 最大容量
     */
    private Integer maxcapacity;

    
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

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }

    public Integer getMaxcapacity() {
        return maxcapacity;
    }

    public void setMaxcapacity(Integer maxcapacity) {
        this.maxcapacity = maxcapacity;
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
        sb.append(", output=").append(output);
        sb.append(", maxcapacity=").append(maxcapacity);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}