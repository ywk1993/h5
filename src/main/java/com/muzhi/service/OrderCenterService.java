package com.muzhi.service;

import com.muzhi.model.OrderCenter;

public interface OrderCenterService {
	public int deleteByPrimaryKey(Integer id);

    public int insert(OrderCenter record);

    public int insertSelective(OrderCenter record);

    public OrderCenter selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(OrderCenter record);

    public int updateByPrimaryKey(OrderCenter record);
    
    public void initOrderCenter(Integer userId);
}
