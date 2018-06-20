package com.muzhi.model;

import java.io.Serializable;
import java.util.List;

import com.muzhi.model.configbean.ConfigTask;

/**
 * @author 
 */
public class Task extends TaskKey implements Serializable {
    private String describe;

    private Integer condition;

    private String parament;

    private Integer userNum;

    private static final long serialVersionUID = 1L;

    public Task(Integer uid, ConfigTask configTask) {
    	setId(uid);
    	setTid(configTask.getId());
    	setStatus(1); // 1: represend effect status; 0: represend no effect
    	setDescribe(configTask.getDescribe());
    	setCondition(configTask.getCondition());
    	setParament(configTask.getParament());
    	setUserNum(0);// 0: represend initialized task times
	}
    
    /**
	 * 
	 */
	public Task() {
		super();
	}

	public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public String getParament() {
        return parament;
    }

    public void setParament(String parament) {
        this.parament = parament;
    }

    public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
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
        Task other = (Task) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDescribe() == null ? other.getDescribe() == null : this.getDescribe().equals(other.getDescribe()))
            && (this.getCondition() == null ? other.getCondition() == null : this.getCondition().equals(other.getCondition()))
            && (this.getParament() == null ? other.getParament() == null : this.getParament().equals(other.getParament()))
            && (this.getUserNum() == null ? other.getUserNum() == null : this.getUserNum().equals(other.getUserNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDescribe() == null) ? 0 : getDescribe().hashCode());
        result = prime * result + ((getCondition() == null) ? 0 : getCondition().hashCode());
        result = prime * result + ((getParament() == null) ? 0 : getParament().hashCode());
        result = prime * result + ((getUserNum() == null) ? 0 : getUserNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", describe=").append(describe);
        sb.append(", condition=").append(condition);
        sb.append(", parament=").append(parament);
        sb.append(", userNum=").append(userNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}