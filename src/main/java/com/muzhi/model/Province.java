package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class Province implements Serializable {
    private Integer uid;

    /**
     * 省份
     */
    private String province;

    private static final long serialVersionUID = 1L;
    
    public Province() {
		
	}
    
    public Province(Integer uid, String province) {
		super();
		this.uid = uid;
		this.province = province;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", province=").append(province);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}