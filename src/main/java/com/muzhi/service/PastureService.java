package com.muzhi.service;

import com.muzhi.model.Pasture;

public interface PastureService {
	public int deleteByPrimaryKey(Integer id);

    public int insert(Pasture record);

    public int insertSelective(Pasture record);

    public Pasture selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Pasture record);

    public int updateByPrimaryKey(Pasture record);
    
    public void initPasture(Integer userId);
}
