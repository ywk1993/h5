package com.muzhi.dao;

import com.muzhi.model.Fridge;

public interface FridgeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Fridge record);

    int insertSelective(Fridge record);

    Fridge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fridge record);

    int updateByPrimaryKey(Fridge record);
}