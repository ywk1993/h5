package com.muzhi.dao;

import com.muzhi.model.Orchard;

public interface OrchardDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Orchard record);

    int insertSelective(Orchard record);

    Orchard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orchard record);

    int updateByPrimaryKey(Orchard record);
}