package com.muzhi.model;

import java.io.Serializable;
import java.util.List;

import com.muzhi.model.configbean.ConfigRate;

/**
 * @author 
 */
public class Rank implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 等级
     */
    private Integer rateLevel;

    /**
     * 名字
     */
    private String name;

    /**
     * 图标
     */
    private String logo;

    /**
     * 星级
     */
    private Integer startLevel;

    /**
     * 任务清单
     */
    private String task;

    /**
     * 需要的金币
     */
    private Integer needgold;
    
    /**
     * 
     */
    private List<Task> taskList;

    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
	public Rank() {
		super();
	}

	
	/**
	 * @param id
	 */
	public Rank(Integer id, ConfigRate configRate) {
		super();
		this.id = id;
		this.setLogo(configRate.getLogo());
		this.setName(configRate.getName());
		this.setNeedgold(configRate.getNeedGold());
		this.setRateLevel(configRate.getRateLevel());
		this.setStartLevel(configRate.getStartLevel());
		this.setTask(configRate.getTask());
	}



	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRateLevel() {
        return rateLevel;
    }

    public void setRateLevel(Integer rateLevel) {
        this.rateLevel = rateLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(Integer startLevel) {
        this.startLevel = startLevel;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getNeedgold() {
        return needgold;
    }

    public void setNeedgold(Integer needgold) {
        this.needgold = needgold;
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
        Rank other = (Rank) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRateLevel() == null ? other.getRateLevel() == null : this.getRateLevel().equals(other.getRateLevel()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getStartLevel() == null ? other.getStartLevel() == null : this.getStartLevel().equals(other.getStartLevel()))
            && (this.getTask() == null ? other.getTask() == null : this.getTask().equals(other.getTask()))
            && (this.getNeedgold() == null ? other.getNeedgold() == null : this.getNeedgold().equals(other.getNeedgold()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRateLevel() == null) ? 0 : getRateLevel().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getStartLevel() == null) ? 0 : getStartLevel().hashCode());
        result = prime * result + ((getTask() == null) ? 0 : getTask().hashCode());
        result = prime * result + ((getNeedgold() == null) ? 0 : getNeedgold().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rateLevel=").append(rateLevel);
        sb.append(", name=").append(name);
        sb.append(", logo=").append(logo);
        sb.append(", startLevel=").append(startLevel);
        sb.append(", task=").append(task);
        sb.append(", needgold=").append(needgold);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}