package com.muzhi.dao;

import com.muzhi.model.OrderReward;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderRewardDao {

    int insert(OrderReward record);

    OrderReward selectByPrimaryKey(@Param("id") Integer id, @Param("orderId") Integer orderId,@Param("propId") Integer propId);
    
    List<OrderReward> selectAll(@Param("id") Integer id, @Param("orderId") Integer orderId);
    
    int updateByPrimaryKey(OrderReward record);
    
    void delete(@Param("id") Integer id, @Param("orderId") Integer orderId);
}