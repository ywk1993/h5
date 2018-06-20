package com.muzhi.dao;

import com.muzhi.model.Prop;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PropDao {

    int insert(Prop record);

    Prop selectByPrimaryKey(@Param("id") Integer id, @Param("propId") Integer propId);

    int updateByPrimaryKey(Prop record);
    
    List<Prop> getPropByUid(@Param("id") Integer id);
}