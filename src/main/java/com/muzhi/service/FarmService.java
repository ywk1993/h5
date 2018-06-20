package com.muzhi.service;

import com.muzhi.model.Farm;

public interface FarmService {
	// 数据库基本操作
	
	public int deleteByPrimaryKey(Integer id);

    public int insert(Farm record);

    public int insertSelective(Farm record);

    public Farm selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Farm record);

    public int updateByPrimaryKey(Farm record);
    
    public void initFarm(Integer userId);
}
