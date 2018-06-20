package com.muzhi.dao;

import com.muzhi.model.OrderCenter;

public interface OrderCenterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderCenter record);

    int insertSelective(OrderCenter record);

    OrderCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderCenter record);

    int updateByPrimaryKey(OrderCenter record);
}