package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.OrderCenterDao;
import com.muzhi.model.OrderCenter;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.OrderCenterService;

@Service
public class OrderCenterServiceImpl implements OrderCenterService{
	@Autowired
	OrderCenterDao orderCenterDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderCenter record) {
		return orderCenterDao.insert(record);
	}

	@Override
	public int insertSelective(OrderCenter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderCenter selectByPrimaryKey(Integer id) {
		return orderCenterDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderCenter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderCenter record) {
		return orderCenterDao.updateByPrimaryKey(record);
	}
	// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
	@Override
	public void initOrderCenter(Integer userId) {
		insert(new OrderCenter(userId, LEVEL_STATE.INIT_LEVEL.getNum()));
		
	}

	
	
}
