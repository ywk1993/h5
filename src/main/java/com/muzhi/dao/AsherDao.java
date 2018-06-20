package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Asher;

public interface AsherDao {

   /* int deleteByPrimaryKey(Integer id);*/

    int insertList(@Param("recordList") List<Asher> recordList);

    List<Asher> selectList(Asher asher);
    
    List<Asher> selectByUid(@Param("userId") Integer userId);

   /* int updateByPrimaryKey(Asher record);*/
    /**
     * 查询用户雇佣的迎宾
     * @param userId
     * @return
     */
    Asher selectAshers(@Param("userId") Integer userId,@Param("index") Integer index);
    /**
     * 查询用户某一个迎宾
     * @return
     */
    Asher selectOneAsher(@Param("userId") Integer userId,@Param("asherId") Integer asherId);
    /**
     * 解雇用户某一个迎宾
     * @param userId
     * @param asherId
     * @return
     */
    int deleteOneAsher(@Param("userId") Integer userId,@Param("asherId") Integer asherId);
    
    int updateByPrimaryKey(Asher record);
}