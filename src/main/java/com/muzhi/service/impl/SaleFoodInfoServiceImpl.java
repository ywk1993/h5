package com.muzhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.SaleFoodInfoDao;
import com.muzhi.model.SaleFoodInfo;
import com.muzhi.service.SaleFoodInfoService;
@Service
public class SaleFoodInfoServiceImpl implements SaleFoodInfoService{
    @Autowired 
    private SaleFoodInfoDao saleFoodInfoDao;
	@Override
	public List<SaleFoodInfo> getSaleFoodInfo(Integer id) {
		return saleFoodInfoDao.getSaleFoodInfo(id);
	}

	@Override
	public SaleFoodInfo getOneSaleFoodInfo(Integer id, Integer index) {
		return saleFoodInfoDao.getOneSaleFoodInfo(id, index);
		
	}

	@Override
	public void updateSaleFoodInfo(SaleFoodInfo saleFoodInfo) {
		saleFoodInfoDao.updateSaleFoodInfo(saleFoodInfo);
		
	}

	@Override
	public void deleteSaleFoodInfo(Integer id, Integer index) {
		saleFoodInfoDao.deleteSaleFoodInfo(id, index);
		
	}

	@Override
	public void insertSaleFoodInfo(SaleFoodInfo saleFoodInfo) {
		saleFoodInfoDao.insertSaleFoodInfo(saleFoodInfo);
		
	}

	@Override
	public Integer getCount() {
	
		return saleFoodInfoDao.getCount();
	}

}
