package com.muzhi.dao;

import com.muzhi.model.Fishpond;

public interface FishpondDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Fishpond record);

    int insertSelective(Fishpond record);

    Fishpond selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fishpond record);

    int updateByPrimaryKey(Fishpond record);
}