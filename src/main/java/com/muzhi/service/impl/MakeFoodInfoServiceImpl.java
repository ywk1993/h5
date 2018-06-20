package com.muzhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.MakeFoodInfoDao;
import com.muzhi.model.MakeFoodInfo;
import com.muzhi.model.User;
import com.muzhi.service.MakeFoodInfoService;
@Service
public class MakeFoodInfoServiceImpl implements MakeFoodInfoService {
    @Autowired
    private MakeFoodInfoDao makeFoodInfoDao;
	@Override
	public List<MakeFoodInfo> getMakeFoodInfo(Integer id) {
		
		return makeFoodInfoDao.getMakeFoodInfo(id);
	}

	@Override
	public MakeFoodInfo getOneMakeFoodInfo(Integer id, Integer index) {
		
		return makeFoodInfoDao.getOneMakeFoodInfo(id, index);
	}

	@Override
	public void updateMakeFoodInfo(MakeFoodInfo makeFoodInfo) {
		makeFoodInfoDao.updateMakeFoodInfo(makeFoodInfo);

	}

	@Override
	public void deleteMakeFoodInfo(Integer id, Integer index) {
		makeFoodInfoDao.deleteMakeFoodInfo(id, index);

	}

	@Override
	public void insertMakeFoodInfo(MakeFoodInfo makeFoodInfo) {
		makeFoodInfoDao.insertMakeFoodInfo(makeFoodInfo);

	}

	@Override
	public Integer getCount() {
		
		return makeFoodInfoDao.getCount();
	}

	@Override
	public boolean isEnough(Integer id) {
		
		List<MakeFoodInfo> free = makeFoodInfoDao.getFree(id);
		if (null ==free||free.size()==0) {
			return false;
		}
		return true;
	}

	@Override
	public void makeFoodInfoInit(User userInit) {
		MakeFoodInfo makeFoodInfo = new MakeFoodInfo();
		makeFoodInfo.setUserId(userInit.getId());
		makeFoodInfo.setIndex(0);//默认自己厨师位置为0
		makeFoodInfo.setStatus(0);
		makeFoodInfoDao.insertMakeFoodInfo(makeFoodInfo);
	}

	@Override
	public void updateState(Integer id, Integer state, Integer index) {
		makeFoodInfoDao.updateState(id, state, index);
		
	}

	@Override
	public void soloveBug() {
		makeFoodInfoDao.soloveBug();
		
	}

}
