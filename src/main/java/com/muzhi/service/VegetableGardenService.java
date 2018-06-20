package com.muzhi.service;

import com.muzhi.model.VegetableGarden;

public interface VegetableGardenService {
	public int deleteByPrimaryKey(Integer id);

    public int insert(VegetableGarden record);

    public int insertSelective(VegetableGarden record);

    public VegetableGarden selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(VegetableGarden record);

    public int updateByPrimaryKey(VegetableGarden record);
    
    public void initVegetableGarden(Integer userId);
}
