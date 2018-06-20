package com.muzhi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Chef;;

public interface ChefService {
	public int insertList(@Param("recordList") List<Chef> recordList);

    public List<Chef> selectList(Chef chef);
    /**
     * 查询用户的厨师列表
     * @param userId
     * @return
     */
    public Chef selectByIndex(Integer userId,Integer index);
    
    public List<Chef> selectChefs(Integer userId);
    
    public int updateByPrimaryKey(Chef record);
}
