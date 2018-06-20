package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Order;
import com.muzhi.model.OrderKey;

public interface OrderDao {
    int deleteByPrimaryKey(OrderKey key);

    int insert(Order record);
    
    void insertList(@Param("recordList") List<Order> recordList);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Order record);
    
    List<Order> selectByObject(Order record);
    
    List<Order> selectOrder(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    void updateList(@Param("recordList")List<Order> recordList);
}