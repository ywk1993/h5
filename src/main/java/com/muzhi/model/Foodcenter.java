package com.muzhi.model;

import java.io.Serializable;

import com.muzhi.model.state.BuildState;

/**
 * @author 
 */
public class Foodcenter implements Serializable, BuildState {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 建筑等级
     */
    private Integer level;

    /**
     * 建筑id
     */
    private Integer buildId;
    
    /**
     * 研究时间
     */
    private Integer lefttime;
    /**
     * 研究力
     */
    private Integer power;
    
    private Integer maxPower;
    
    private Integer makeLevel;

    private static final long serialVersionUID = 1L;
    
    public Foodcenter() {
		super();
	}

	public Foodcenter(Integer id, Integer level, Integer buildId, Integer lefttime,Integer power) {
		super();
		this.id = id;
		this.level = level;
		this.buildId = buildId;
		this.lefttime = lefttime;
		this.power=power;
	}
	
	public Integer getMakeLevel() {
		return makeLevel;
	}

	public void setMakeLevel(Integer makeLevel) {
		this.makeLevel = makeLevel;
	}

	public Integer getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(Integer maxPower) {
		this.maxPower = maxPower;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getlefttime() {
		return lefttime;
	}

	public void setlefttime(Integer lefttime) {
		this.lefttime = lefttime;
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

    
    public Integer getBuildId() {
		return buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
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
        Foodcenter other = (Foodcenter) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getBuildId() == null ? other.getBuildId() == null : this.getBuildId().equals(other.getBuildId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getBuildId() == null) ? 0 : getBuildId().hashCode());
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
        sb.append(", buildid=").append(buildId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}