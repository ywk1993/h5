package com.muzhi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Asher;

public interface AsherService {
	public int insertList(@Param("recordList") List<Asher> recordList);

	public List<Asher> selectList(Asher asher);
	public List<Asher> selectByUid(Integer userId);
    /**
     * 查询用户雇佣迎宾
     * @param userId
     * @return
     */
	public Asher selectAshers(Integer userId,Integer index);
    
	public int updateByPrimaryKey(Asher record);
}
