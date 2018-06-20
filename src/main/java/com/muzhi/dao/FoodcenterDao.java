package com.muzhi.dao;

import com.muzhi.model.Foodcenter;

public interface FoodcenterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Foodcenter record);

    int insertSelective(Foodcenter record);

    Foodcenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Foodcenter record);

    int updateByPrimaryKey(Foodcenter record);
}