package com.muzhi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 厨师
 * @author yany 
 */
public class Chef implements Serializable {
    /**
     * 玩家id
     */
    private Integer id;

    /**
     * 雇佣员工
     */
    private Integer employid;

    /**
     * 来源 1好友 2机器人 3陌生人
     */
    private Integer source;

    private String addition;

    /**
     * 是否继续雇佣 1保持 0不保持
     */
    private Integer iskeep;

    /**
     * 是否临时保存（是否有菜品未领取） 1 是； 0 否
     */
    private Integer istempkeep;

    /**
     * 雇佣倒计时
     */
    private Integer lefttime;

    /**
     * 是否为新员工
     */
    private Integer isnew;
    
    /**
     * 位置信息
     */
    private Integer index;
    
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Chef() {
		super();
	}

	public Chef(Integer id, Integer employid, Integer source, String addition, Integer iskeep, Integer istempkeep,
			Integer lefttime, Integer isnew, Integer index) {
		super();
		this.id = id;
		this.employid = employid;
		this.source = source;
		this.addition = addition;
		this.iskeep = iskeep;
		this.istempkeep = istempkeep;
		this.lefttime = lefttime;
		this.isnew = isnew;
		this.index = index;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setIndex(List<Chef> list) {
		Integer preIndex = -1;
		for (Chef chef : list) {
			Integer index = chef.getIndex();
			if (index != 2) {
				preIndex = 2;
				break;
			}
			if (index != 3 && preIndex == -1) {
				preIndex = 3;
				break;
			}
			if (index != 4 && preIndex == -1 ) {
				preIndex = 4;
				break;
			}
			if (index != 5 && preIndex == -1 ) {
				preIndex = 5;		
				break;
			}
		}
		this.index = preIndex;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployid() {
        return employid;
    }

    public void setEmployid(Integer employid) {
        this.employid = employid;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public Integer getIskeep() {
        return iskeep;
    }

    public void setIskeep(Integer iskeep) {
        this.iskeep = iskeep;
    }

    public Integer getIstempkeep() {
        return istempkeep;
    }

    public void setIstempkeep(Integer istempkeep) {
        this.istempkeep = istempkeep;
    }

    public Integer getLefttime() {
        return lefttime;
    }
    public Integer getLefttime(Chef chef) {
    	long leftTime = 0;
    	if(null!=chef) {
    		if(null == chef.getCreateTime()) {
    			chef.setCreateTime(new Date());
    		}
			long createTime = chef.getCreateTime().getTime();
			long nowTime = new Date().getTime();
			long reduceTime = nowTime - createTime;
			leftTime = Constant.STAFF_LEFTTIME-reduceTime/1000;
			if(leftTime<=0) {
				leftTime=0;
			}
			chef.setLefttime((int)leftTime);
    	}
		return  chef.getLefttime();
    }

    public void setLefttime(Integer lefttime) {
        this.lefttime = lefttime;
    }

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
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
        Chef other = (Chef) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmployid() == null ? other.getEmployid() == null : this.getEmployid().equals(other.getEmployid()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getAddition() == null ? other.getAddition() == null : this.getAddition().equals(other.getAddition()))
            && (this.getIskeep() == null ? other.getIskeep() == null : this.getIskeep().equals(other.getIskeep()))
            && (this.getIstempkeep() == null ? other.getIstempkeep() == null : this.getIstempkeep().equals(other.getIstempkeep()))
            && (this.getLefttime() == null ? other.getLefttime() == null : this.getLefttime().equals(other.getLefttime()))
            && (this.getIsnew() == null ? other.getIsnew() == null : this.getIsnew().equals(other.getIsnew()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmployid() == null) ? 0 : getEmployid().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getAddition() == null) ? 0 : getAddition().hashCode());
        result = prime * result + ((getIskeep() == null) ? 0 : getIskeep().hashCode());
        result = prime * result + ((getIstempkeep() == null) ? 0 : getIstempkeep().hashCode());
        result = prime * result + ((getLefttime() == null) ? 0 : getLefttime().hashCode());
        result = prime * result + ((getIsnew() == null) ? 0 : getIsnew().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", employid=").append(employid);
        sb.append(", source=").append(source);
        sb.append(", addition=").append(addition);
        sb.append(", iskeep=").append(iskeep);
        sb.append(", istempkeep=").append(istempkeep);
        sb.append(", lefttime=").append(lefttime);
        sb.append(", isnew=").append(isnew);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
}