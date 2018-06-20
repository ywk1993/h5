package com.muzhi.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 * @author 
 */
public class Article implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 摆件id
     */
    private Integer articleId;

    /**
     * 摆件类型
     */
    private Integer atricleType;

    /**
     * 状态 0没装扮 1装扮了
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
    
    public Article() {
		super();
	}

	public Article(Integer id, Integer articleId, Integer atricleType, Integer status) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.atricleType = atricleType;
		this.status = status;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getAtricleType() {
        return atricleType;
    }

    public void setAtricleType(Integer atricleType) {
        this.atricleType = atricleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleId=").append(articleId);
        sb.append(", atricleType=").append(atricleType);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}