package com.muzhi.model;

import java.io.Serializable;

import com.muzhi.model.configbean.ConfigManor;

/**
 * @author 
 * 
 */
public class Manor /*extends ManorKey*/ implements Serializable {
	/**
     * 用户id
     */
    private Integer id;

    /**
     * 建筑id
     */
    private Integer buildId;
	
    /**
     * 表格id（庄园建筑）
     */
    private Integer tid;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 金币消耗
     */
    private String needGold;

    /**
     * 升级时间
     */
    private Long time;

    /**
     * 剩余时间
     */
    private Long leftTime;
    
    /**
     * 记录升级的开始时间
     */
    private Long startTime;
    
    /**
     * 下一级升级信息
     */
    private  ConfigManor nextLevelUp;
    /**
     * 下一级属性
     */
    private  Object nextLevelAttr;

    private static final long serialVersionUID = 1L;

    
    public Manor() {
		super();
		// TODO Auto-generated constructor stub
	}
     
	public Manor(Integer id, Integer tid, Integer buildId, Integer level, String needGold, Long time,
			Long leftTime, Long startTime) {
		super();
		this.id = id;
		this.buildId = buildId;
		this.tid = tid;
		this.level = level;
		this.needGold = needGold;
		this.time = time;
		this.leftTime = leftTime;
		this.startTime = startTime;
	}
	

	public ConfigManor getNextLevelUp() {
		return nextLevelUp;
	}

	public void setNextLevelUp(ConfigManor nextLevelUp) {
		this.nextLevelUp = nextLevelUp;
	}

	public Object getNextLevelAttr() {
		return nextLevelAttr;
	}

	public void setNextLevelAttr(Object nextLevelAttr) {
		this.nextLevelAttr = nextLevelAttr;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBuildId() {
		return buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getNeedGold() {
        return needGold;
    }

    public void setNeedGold(String needGold) {
        this.needGold = needGold;
    }

    public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(Long leftTime) {
		this.leftTime = leftTime;
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
        Manor other = (Manor) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBuildId() == null ? other.getBuildId() == null : this.getBuildId().equals(other.getBuildId()))
            && (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getNeedGold() == null ? other.getNeedGold() == null : this.getNeedGold().equals(other.getNeedGold()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getLeftTime() == null ? other.getLeftTime() == null : this.getLeftTime().equals(other.getLeftTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBuildId() == null) ? 0 : getBuildId().hashCode());
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getNeedGold() == null) ? 0 : getNeedGold().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getLeftTime() == null) ? 0 : getLeftTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", level=").append(level);
        sb.append(", needGold=").append(needGold);
        sb.append(", time=").append(time);
        sb.append(", leftTime=").append(leftTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}