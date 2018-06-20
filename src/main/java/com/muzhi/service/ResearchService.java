package com.muzhi.service;

import com.muzhi.model.Research;
import com.muzhi.model.ResearchKey;

public interface ResearchService {
	public int deleteByPrimaryKey(ResearchKey key);

    public int insert(Research record);

    public int insertSelective(Research record);

    public Research selectByPrimaryKey(ResearchKey key);

    public int updateByPrimaryKeySelective(Research record);

    public int updateByPrimaryKey(Research record);
}
