package com.muzhi.service;

import com.muzhi.model.Fishpond;

public interface FishpondService {
	public int deleteByPrimaryKey(Integer id);

    public int insert(Fishpond record);

    public int insertSelective(Fishpond record);

    public Fishpond selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Fishpond record);

    public int updateByPrimaryKey(Fishpond record);
    
    public void initFishpond(Integer userId);
}
