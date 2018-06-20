package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class Prop implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 道具id
     */
    private Integer propId;

   /* *//**
     * 名称
     *//*
    private String name;*/

    /**
     * 数量
     */
    private Integer number;

   /* *//**
     * 类型 暂时可能0高级食材1高级的建筑材料
     *//*
    private Integer type;*/

    private static final long serialVersionUID = 1L;
    
    public Prop() {
		super();
	}

	

	public Prop(Integer id, Integer propId, Integer number) {
		super();
		this.id = id;
		this.propId = propId;
		this.number = number;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropId() {
        return propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

   /* public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
*/

    public String toJson() {
        return new Gson().toJson(this);
    }
}