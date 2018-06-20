package com.muzhi.dao;

import com.muzhi.model.Pasture;

public interface PastureDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Pasture record);

    int insertSelective(Pasture record);

    Pasture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pasture record);

    int updateByPrimaryKey(Pasture record);
}