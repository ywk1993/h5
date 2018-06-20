package com.muzhi.service;

import com.muzhi.model.Orchard;

public interface OrchardService {
	public int deleteByPrimaryKey(Integer id);

    public int insert(Orchard record);

    public int insertSelective(Orchard record);

    public Orchard selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Orchard record);

    public int updateByPrimaryKey(Orchard record);
    
    public void initOrchard(Integer userId);
}
