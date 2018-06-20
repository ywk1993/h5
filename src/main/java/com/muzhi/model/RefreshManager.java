package com.muzhi.model;

import java.io.Serializable;

/**
 * @author 
 */
public class RefreshManager implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 可用刷新次数
     */
    private Integer num;

    /**
     * 总次数
     */
    private Integer totleNum;

    /**
     * 货币id
     */
    private Integer currencyId;

    /**
     * 货币数
     */
    private Integer currencyNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTotleNum() {
        return totleNum;
    }

    public void setTotleNum(Integer totleNum) {
        this.totleNum = totleNum;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getCurrencyNum() {
        return currencyNum;
    }

    public void setCurrencyNum(Integer currencyNum) {
        this.currencyNum = currencyNum;
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
        RefreshManager other = (RefreshManager) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getTotleNum() == null ? other.getTotleNum() == null : this.getTotleNum().equals(other.getTotleNum()))
            && (this.getCurrencyId() == null ? other.getCurrencyId() == null : this.getCurrencyId().equals(other.getCurrencyId()))
            && (this.getCurrencyNum() == null ? other.getCurrencyNum() == null : this.getCurrencyNum().equals(other.getCurrencyNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getTotleNum() == null) ? 0 : getTotleNum().hashCode());
        result = prime * result + ((getCurrencyId() == null) ? 0 : getCurrencyId().hashCode());
        result = prime * result + ((getCurrencyNum() == null) ? 0 : getCurrencyNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", num=").append(num);
        sb.append(", totleNum=").append(totleNum);
        sb.append(", currencyId=").append(currencyId);
        sb.append(", currencyNum=").append(currencyNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}