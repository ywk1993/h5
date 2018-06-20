package com.muzhi.dao;

import com.muzhi.model.Farm;

public interface FarmDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Farm record);

    int insertSelective(Farm record);

    Farm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Farm record);

    int updateByPrimaryKey(Farm record);
}