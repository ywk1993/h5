package com.muzhi.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.OrderTask;

public interface OrderTaskDao {
    

    void insert(OrderTask record);
    
    List<OrderTask> selectByTime(@Param("id") Integer id,@Param("endTime") Timestamp endTime);

    void update(OrderTask record);
    
    List<OrderTask> selectByNoTime(@Param("id") Integer id);
    
    OrderTask selectOneByTime(@Param("id") Integer id,@Param("orderId") Integer orderId,@Param("endTime") Timestamp endTime);
    
}