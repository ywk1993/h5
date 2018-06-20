package com.muzhi.dao;

import com.muzhi.model.Publicity;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PublicityDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(Publicity record);

    List<Publicity> selectByPrimaryKey(Integer uid);
    
    List<Publicity> selectAll();

    int updateByPrimaryKey(Publicity record);
    
    Publicity selectByPublicityId(@Param("userId") Integer userId,@Param("publicityId") Integer publicityId);
    /**
     * 获取用户的宣传名气
     * @param userId
     * @return
     */
    Integer getPublicityFame(@Param("userId") Integer userId);
}