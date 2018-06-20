package com.muzhi.model.configbean;

public class ConfigArticle {
	private Integer id;
	private Integer articleType;
	private String name;
	private String model;
	private Integer useType;
	private Integer number;
	private Integer haohua;
	private Integer loveType;
	private Integer loveAdd;
	private Integer condition;
	
	
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticleType() {
		return articleType;
	}
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getHaohua() {
		return haohua;
	}
	public void setHaohua(Integer haohua) {
		this.haohua = haohua;
	}
	public Integer getLoveType() {
		return loveType;
	}
	public void setLoveType(Integer loveType) {
		this.loveType = loveType;
	}
	public Integer getLoveAdd() {
		return loveAdd;
	}
	public void setLoveAdd(Integer loveAdd) {
		this.loveAdd = loveAdd;
	}
	@Override
	public String toString() {
		return "ConfigArticle [id=" + id + ", articleType=" + articleType + ", name=" + name + ", model=" + model
				+ ", useType=" + useType + ", number=" + number + ", haohua=" + haohua + ", loveType=" + loveType
				+ ", loveAdd=" + loveAdd + ", condition=" + condition + "]";
	}
    
}
