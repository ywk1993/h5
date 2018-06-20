package com.muzhi.dao;

import com.muzhi.model.Generator;

public interface GeneratorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Generator record);

    int insertSelective(Generator record);

    Generator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Generator record);

    int updateByPrimaryKey(Generator record);
}