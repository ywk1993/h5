package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.OrderFood;
import com.muzhi.model.OrderFoodKey;

public interface OrderFoodDao {
    int deleteByPrimaryKey(OrderFoodKey key);

    int insert(OrderFood record);
    
    void insertList(@Param("recordList")List<OrderFood> recordList);

    int insertSelective(OrderFood record);

    List<OrderFood> selectByPrimaryKey(OrderFoodKey key);
    
    List<OrderFood> selectByObject(@Param("uuidList")List<String> uuidList);

    int updateByPrimaryKeySelective(OrderFood record);

    int updateByPrimaryKey(OrderFood record);

}