package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class LoginRecord implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登出时间
     */
    private Date logoutTime;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 手机型号
     */
    private String phoneModel;
    /**
     * 渠道
     */
    private Integer forum;

    /**
     * 授权方式  1、静默授权  2、完整授权
     */
    private Integer empower;
    /**
     * 登录时长/秒
     */
    private Long onlineTime;

    private static final long serialVersionUID = 1L;

	public Long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getForum() {
		return forum;
	}

	public void setForum(Integer forum) {
		this.forum = forum;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public Integer getEmpower() {
        return empower;
    }

    public void setEmpower(Integer empower) {
        this.empower = empower;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", logoutTime=").append(logoutTime);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", phoneModel=").append(phoneModel);
        sb.append(", empower=").append(empower);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}