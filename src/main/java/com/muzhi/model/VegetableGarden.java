package com.muzhi.model;

import java.io.Serializable;

import com.muzhi.model.state.BuildState;

/**
 * @author 
 */
public class VegetableGarden implements Serializable, BuildState {
	/**
     * 用户id
     */
    private Integer id;

    /**
     * 等级
     */
    private Integer level;

    /**
     * table id
     */
    private Integer tid;

    /**
     * 建筑id
     */
    private Integer buildId;

    /**
     * 产出
     */
    private Integer output;

    /**
     * 最大容量
     */
    private Integer maxCapacity;

    /**
     * 蔬菜存量
     */
    private Integer vegetable;

    private static final long serialVersionUID = 1L;

    public VegetableGarden() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VegetableGarden(Integer id, Integer level, Integer tid, Integer buildId, Integer output, Integer maxCapacity,
			Integer vegetable) {
		super();
		this.id = id;
		this.level = level;
		this.tid = tid;
		this.buildId = buildId;
		this.output = output;
		this.maxCapacity = maxCapacity;
		this.vegetable = vegetable;
	}
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getVegetable() {
        return vegetable;
    }

    public void setVegetable(Integer vegetable) {
        this.vegetable = vegetable;
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
        VegetableGarden other = (VegetableGarden) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getBuildId() == null ? other.getBuildId() == null : this.getBuildId().equals(other.getBuildId()))
            && (this.getOutput() == null ? other.getOutput() == null : this.getOutput().equals(other.getOutput()))
            && (this.getMaxCapacity() == null ? other.getMaxCapacity() == null : this.getMaxCapacity().equals(other.getMaxCapacity()))
            && (this.getVegetable() == null ? other.getVegetable() == null : this.getVegetable().equals(other.getVegetable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getBuildId() == null) ? 0 : getBuildId().hashCode());
        result = prime * result + ((getOutput() == null) ? 0 : getOutput().hashCode());
        result = prime * result + ((getMaxCapacity() == null) ? 0 : getMaxCapacity().hashCode());
        result = prime * result + ((getVegetable() == null) ? 0 : getVegetable().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", level=").append(level);
        sb.append(", tid=").append(tid);
        sb.append(", buildId=").append(buildId);
        sb.append(", output=").append(output);
        sb.append(", maxCapacity=").append(maxCapacity);
        sb.append(", vegetable=").append(vegetable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}