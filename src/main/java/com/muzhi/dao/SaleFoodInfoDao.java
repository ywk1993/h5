package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.SaleFoodInfo;

public interface SaleFoodInfoDao {
	/**
	 * 获取所有的用户记录
	 * @param id
	 * @return
	 */
	List<SaleFoodInfo> getSaleFoodInfo(Integer id);
	/**
	 * 获取单条的记录
	 * @param id
	 * @param index
	 * @return
	 */
	SaleFoodInfo getOneSaleFoodInfo(@Param("id")Integer id,@Param("index")Integer index);
	/**
	 * 跟新记录
	 * @param makeFoodInfo
	 */
	void updateSaleFoodInfo(SaleFoodInfo saleFoodInfo);
	/**
	 * 删除记录
	 * @param id
	 * @param index
	 */
	void deleteSaleFoodInfo(@Param("id")Integer id,@Param("index")Integer index);
	/**
	 * 新增记录
	 * @param makeFoodInfo
	 */
	void insertSaleFoodInfo(SaleFoodInfo saleFoodInfo);
	/**
	 * 获取记录数
	 * @return
	 */
	Integer getCount();
}
 