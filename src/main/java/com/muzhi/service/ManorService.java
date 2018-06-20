package com.muzhi.service;

import java.util.List;

import com.muzhi.model.Manor;
import com.muzhi.model.ManorKey;

public interface ManorService {

	public int deleteByPrimaryKey(ManorKey key);

	public int insert(Manor record);

	public int insertSelective(Manor record);

	public Manor selectByPrimaryKey(ManorKey key);

	public int updateByPrimaryKeySelective(Manor record);

	public int updateByPrimaryKey(Manor record);
	
	public List<Manor> selectBySelectivePrimaryKey(ManorKey record);
	
	public void initManor(Integer userId);
}
