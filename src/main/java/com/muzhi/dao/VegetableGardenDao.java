package com.muzhi.dao;

import com.muzhi.model.VegetableGarden;

public interface VegetableGardenDao {
    int deleteByPrimaryKey(Integer id);

    int insert(VegetableGarden record);

    int insertSelective(VegetableGarden record);

    VegetableGarden selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VegetableGarden record);

    int updateByPrimaryKey(VegetableGarden record);
}