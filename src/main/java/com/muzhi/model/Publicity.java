package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class Publicity implements Serializable {
    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 宣传编号
     */
    private Integer publicityId;

    /**
     * 剩余免费次数
     */
    private Integer freeTime;

    /**
     * 宣传增加的名气值
     */
    private Integer fame;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPublicityId() {
        return publicityId;
    }

    public void setPublicityId(Integer publicityId) {
        this.publicityId = publicityId;
    }

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    public Integer getFame() {
        return fame;
    }

    public void setFame(Integer fame) {
        this.fame = fame;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", publicityId=").append(publicityId);
        sb.append(", freeTime=").append(freeTime);
        sb.append(", fame=").append(fame);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}