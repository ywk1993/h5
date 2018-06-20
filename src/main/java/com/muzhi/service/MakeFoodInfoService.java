package com.muzhi.service;

import java.util.List;
import com.muzhi.model.MakeFoodInfo;
import com.muzhi.model.User;

public interface MakeFoodInfoService {
	/**
	 * 获取所有的用户记录
	 * @param id
	 * @return
	 */
	public List<MakeFoodInfo> getMakeFoodInfo(Integer id);
	/**
	 * 获取单条的记录
	 * @param id
	 * @param index
	 * @return
	 */
	public MakeFoodInfo getOneMakeFoodInfo(Integer id,Integer index);
	/**
	 * 跟新记录
	 * @param makeFoodInfo
	 */
	public void updateMakeFoodInfo(MakeFoodInfo makeFoodInfo);
	/**
	 * 删除记录
	 * @param id
	 * @param index
	 */
	public void deleteMakeFoodInfo(Integer id,Integer index);
	/**
	 * 新增记录
	 * @param makeFoodInfo
	 */
	public void insertMakeFoodInfo(MakeFoodInfo makeFoodInfo);
	/**
	 * 获取记录数
	 * @return
	 */
	public Integer getCount();
	/**
	 * 判断厨师位是否足够 true足够  false不足
	 * @param id
	 * @return
	 */
	public boolean isEnough(Integer id);
	/**
	 * 倒计时结束改变做菜的状态为完成
	 * @param state
	 */
	public void updateState(Integer id,Integer state,Integer index);
	/**
	 * 解决一个bug定时器停止做菜状态永远为1  在服务器重启的时候将做菜状态为1的直接设置为2
	 * @param state
	 */
	public void soloveBug();
	public void makeFoodInfoInit(User userInit);
}
