package com.muzhi.service;

import java.util.List;

import com.muzhi.model.Result;
import com.muzhi.model.vo.FacilityInfo;

public interface FacilityInfoService {
	/**
	 * 设施升级信息列表
	 * @param userid
	 * @return
	 */
	public List<FacilityInfo> facilityUpgradeInfo(Integer userid);
	/**
	 * 设施升级
	 * @param userid
	 * @return
	 */
	public Result facilityUpgrade(String token,Integer fid);
}
