package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.MakeFoodInfo;

public interface MakeFoodInfoDao {
	/**
	 * 获取所有的用户记录
	 * @param id
	 * @return
	 */
	List<MakeFoodInfo> getMakeFoodInfo(Integer id);
	/**
	 * 获取单条的记录
	 * @param id
	 * @param index
	 * @return
	 */
	MakeFoodInfo getOneMakeFoodInfo(@Param("id")Integer id,@Param("index")Integer index);
	/**
	 * 跟新记录
	 * @param makeFoodInfo
	 */
	void updateMakeFoodInfo(MakeFoodInfo makeFoodInfo);
	/**
	 * 删除记录
	 * @param id
	 * @param index
	 */
	void deleteMakeFoodInfo(@Param("id")Integer id,@Param("index")Integer index);
	/**
	 * 新增记录
	 * @param makeFoodInfo
	 */
	void insertMakeFoodInfo(MakeFoodInfo makeFoodInfo);
	/**
	 * 获取记录数
	 * @return
	 */
	Integer getCount();
	/**
	 * 获取空闲的厨师位
	 */
	List<MakeFoodInfo> getFree(Integer id);
	/**
	 * 倒计时结束改变做菜的状态为完成
	 * @param state
	 */
	void updateState(@Param("id")Integer id,@Param("state")Integer state,@Param("index")Integer index);
	/**
	 * 解决一个bug定时器停止做菜状态永远为1  在服务器重启的时候将做菜状态为1的直接设置为2
	 * @param state
	 */
	void soloveBug();
}
