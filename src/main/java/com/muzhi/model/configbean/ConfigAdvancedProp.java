package com.muzhi.model.configbean;

public class ConfigAdvancedProp {
	//ID 名称	类型	品质	图标	描述
	private Integer id;
	private String name;
	private Integer propLevel;
	private Integer propType;
	private Integer propQualify;
	private String logo;
	private String describe;
	
	
	public Integer getPropQualify() {
		return propQualify;
	}
	public void setPropQualify(Integer propQualify) {
		this.propQualify = propQualify;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPropType() {
		return propType;
	}
	public void setPropType(Integer propType) {
		this.propType = propType;
	}
	public Integer getPropLevel() {
		return propLevel;
	}
	public void setPropLevel(Integer propLevel) {
		this.propLevel = propLevel;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Override
	public String toString() {
		return "ConfigAdvancedProp [id=" + id + ", name=" + name + ", propType=" + propType + ", propLevel=" + propLevel
				+ ", logo=" + logo + ", describe=" + describe + "]";
	}
	
	
	
}
