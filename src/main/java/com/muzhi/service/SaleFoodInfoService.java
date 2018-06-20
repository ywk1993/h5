package com.muzhi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.SaleFoodInfo;

public interface SaleFoodInfoService {
	/**
	 * 获取所有的用户记录
	 * @param id
	 * @return
	 */
	public List<SaleFoodInfo> getSaleFoodInfo(Integer id);
	/**
	 * 获取单条的记录
	 * @param id
	 * @param index
	 * @return
	 */
	public SaleFoodInfo getOneSaleFoodInfo(@ Param("id")Integer id,@ Param("index")Integer index);
	/**
	 * 跟新记录
	 * @param makeFoodInfo
	 */
	public void updateSaleFoodInfo(SaleFoodInfo saleFoodInfo);
	/**
	 * 删除记录
	 * @param id
	 * @param index
	 */
	public void deleteSaleFoodInfo(@ Param("id")Integer id,@ Param("index")Integer index);
	/**
	 * 新增记录
	 * @param makeFoodInfo
	 */
	public void insertSaleFoodInfo(SaleFoodInfo saleFoodInfo);
	/**
	 * 获取记录数
	 * @return
	 */
	public Integer getCount();
}
