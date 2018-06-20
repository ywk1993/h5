package com.muzhi.dao;

import com.muzhi.model.Research;
import com.muzhi.model.ResearchKey;

public interface ResearchDao {
    int deleteByPrimaryKey(ResearchKey key);

    int insert(Research record);

    int insertSelective(Research record);

    Research selectByPrimaryKey(ResearchKey key);

    int updateByPrimaryKeySelective(Research record);

    int updateByPrimaryKey(Research record);
}