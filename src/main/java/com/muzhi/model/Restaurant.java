package com.muzhi.model;

import java.io.Serializable;

import com.muzhi.model.state.BuildState;

/**
 * @author 
 */
public class Restaurant implements Serializable, BuildState {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 客源数目
     */
    private Integer customNum;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 爱心值上限
     */
    private Integer loveLimit;

    /**
     * 最大迎宾数目
     */
    private Integer maxAsher;

    /**
     * 最大厨师数
     */
    private Integer maxChef;

    private static final long serialVersionUID = 1L;

    
    public Restaurant() {
		super();
	}

	public Restaurant(Integer id, Integer customNum, Integer level, Integer area, Integer loveLimit, Integer maxAsher,
			Integer maxChef) {
		super();
		this.id = id;
		this.customNum = customNum;
		this.level = level;
		this.area = area;
		this.loveLimit = loveLimit;
		this.maxAsher = maxAsher;
		this.maxChef = maxChef;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomNum() {
        return customNum;
    }

    public void setCustomNum(Integer customNum) {
        this.customNum = customNum;
    }

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

    public Integer getLoveLimit() {
        return loveLimit;
    }

    public void setLoveLimit(Integer loveLimit) {
        this.loveLimit = loveLimit;
    }

    public Integer getMaxAsher() {
        return maxAsher;
    }

    public void setMaxAsher(Integer maxAsher) {
        this.maxAsher = maxAsher;
    }

    public Integer getMaxChef() {
        return maxChef;
    }

    public void setMaxChef(Integer maxChef) {
        this.maxChef = maxChef;
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
        Restaurant other = (Restaurant) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomNum() == null ? other.getCustomNum() == null : this.getCustomNum().equals(other.getCustomNum()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getLoveLimit() == null ? other.getLoveLimit() == null : this.getLoveLimit().equals(other.getLoveLimit()))
            && (this.getMaxAsher() == null ? other.getMaxAsher() == null : this.getMaxAsher().equals(other.getMaxAsher()))
            && (this.getMaxChef() == null ? other.getMaxChef() == null : this.getMaxChef().equals(other.getMaxChef()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomNum() == null) ? 0 : getCustomNum().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getLoveLimit() == null) ? 0 : getLoveLimit().hashCode());
        result = prime * result + ((getMaxAsher() == null) ? 0 : getMaxAsher().hashCode());
        result = prime * result + ((getMaxChef() == null) ? 0 : getMaxChef().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customNum=").append(customNum);
        sb.append(", level=").append(level);
        sb.append(", area=").append(area);
        sb.append(", loveLimit=").append(loveLimit);
        sb.append(", maxAsher=").append(maxAsher);
        sb.append(", maxChef=").append(maxChef);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}