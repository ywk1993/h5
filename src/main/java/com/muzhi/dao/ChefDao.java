package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Chef;

public interface ChefDao {
	int insertList(@Param("recordList") List<Chef> recordList);

    List<Chef> selectList(Chef chef);
    /**
     * 查询用户雇佣厨师列表
     * @param userId
     * @return
     */
    Chef selectByIndex(@Param("userId") Integer userId,@Param("index") Integer index);
    /**
     * 查询用户的某一个厨师
     * @param userId
     * @param chafId
     * @return
     */
    Chef selectOneChef(@Param("userId") Integer userId,@Param("chefId") Integer chefId);
    /**
     * 解雇用户的某一个厨师
     * @param userId
     * @param chafId
     * @return
     */
    int deleteOneChef(@Param("userId") Integer userId,@Param("chefId") Integer chafId);
    
    int updateByPrimaryKey(Chef record);
}