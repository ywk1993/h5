package com.muzhi.model.vo;

import java.util.List;
/**
 * 员工管理分组界面实体
 * @author yany
 *
 */
public class EmployManager {
	/**
	 * 员工列表
	 */
	List<EmploySource> employSource;
	/**
	 * 员工来源 1好友、2陌生人、3附近
	 */
	Integer type;
	public List<EmploySource> getEmploySource() {
		return employSource;
	}
	public void setEmploySource(List<EmploySource> employSource) {
		this.employSource = employSource;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
