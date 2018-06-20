package com.muzhi.model;

import java.io.Serializable;

/**
 * @author 
 */
public class Generator implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 刷新时间
     */
    private Long riceTime;
    
    /**
     * 距离上一次收取的时间_粮食（大米）
     */
    private Long riceStartTime;

    /**
     * 水果（香蕉）
     */
    private Long bananaTime;

    private Long bananaStartTime;
    /**
     * 蔬菜
     */
    private Long vegetableTime;

    private Long vegetableStartTime;
    /**
     * 肉类
     */
    private Long meatTime;

    private Long meatStartTime;
    /**
     * 鱼类
     */
    private Long fishTime;
    
    private Long fishStartTime;

    private static final long serialVersionUID = 1L;

    
    public Generator() {
		super();
	}

	public Generator(Integer id, Long riceTime, Long riceStartTime, Long bananaTime, Long bananaStartTime,
			Long vegetableTime, Long vegetableStartTime, Long meatTime, Long meatStartTime, Long fishTime,
			Long fishStartTime) {
		super();
		this.id = id;
		this.riceTime = riceTime;
		this.riceStartTime = riceStartTime;
		this.bananaTime = bananaTime;
		this.bananaStartTime = bananaStartTime;
		this.vegetableTime = vegetableTime;
		this.vegetableStartTime = vegetableStartTime;
		this.meatTime = meatTime;
		this.meatStartTime = meatStartTime;
		this.fishTime = fishTime;
		this.fishStartTime = fishStartTime;
	}



	public Long getRiceStartTime() {
		return riceStartTime;
	}

	public void setRiceStartTime(Long riceStartTime) {
		this.riceStartTime = riceStartTime;
	}

	public Long getBananaStartTime() {
		return bananaStartTime;
	}

	public void setBananaStartTime(Long bananaStartTime) {
		this.bananaStartTime = bananaStartTime;
	}

	public Long getVegetableStartTime() {
		return vegetableStartTime;
	}

	public void setVegetableStartTime(Long vegetableStartTime) {
		this.vegetableStartTime = vegetableStartTime;
	}

	public Long getMeatStartTime() {
		return meatStartTime;
	}

	public void setMeatStartTime(Long meatStartTime) {
		this.meatStartTime = meatStartTime;
	}

	public Long getFishStartTime() {
		return fishStartTime;
	}

	public void setFishStartTime(Long fishStartTime) {
		this.fishStartTime = fishStartTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRiceTime() {
        return riceTime;
    }

    public void setRiceTime(Long riceTime) {
        this.riceTime = riceTime;
    }

    public Long getBananaTime() {
        return bananaTime;
    }

    public void setBananaTime(Long bananaTime) {
        this.bananaTime = bananaTime;
    }

    public Long getVegetableTime() {
        return vegetableTime;
    }

    public void setVegetableTime(Long vegetableTime) {
        this.vegetableTime = vegetableTime;
    }

    public Long getMeatTime() {
        return meatTime;
    }

    public void setMeatTime(Long meatTime) {
        this.meatTime = meatTime;
    }

    public Long getFishTime() {
        return fishTime;
    }

    public void setFishTime(Long fishTime) {
        this.fishTime = fishTime;
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
        Generator other = (Generator) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRiceTime() == null ? other.getRiceTime() == null : this.getRiceTime().equals(other.getRiceTime()))
            && (this.getBananaTime() == null ? other.getBananaTime() == null : this.getBananaTime().equals(other.getBananaTime()))
            && (this.getVegetableTime() == null ? other.getVegetableTime() == null : this.getVegetableTime().equals(other.getVegetableTime()))
            && (this.getMeatTime() == null ? other.getMeatTime() == null : this.getMeatTime().equals(other.getMeatTime()))
            && (this.getFishTime() == null ? other.getFishTime() == null : this.getFishTime().equals(other.getFishTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRiceTime() == null) ? 0 : getRiceTime().hashCode());
        result = prime * result + ((getBananaTime() == null) ? 0 : getBananaTime().hashCode());
        result = prime * result + ((getVegetableTime() == null) ? 0 : getVegetableTime().hashCode());
        result = prime * result + ((getMeatTime() == null) ? 0 : getMeatTime().hashCode());
        result = prime * result + ((getFishTime() == null) ? 0 : getFishTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", riceTime=").append(riceTime);
        sb.append(", bananaTime=").append(bananaTime);
        sb.append(", vegetableTime=").append(vegetableTime);
        sb.append(", meatTime=").append(meatTime);
        sb.append(", fishTime=").append(fishTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}