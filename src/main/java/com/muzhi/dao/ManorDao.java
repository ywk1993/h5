package com.muzhi.dao;

import java.util.List;

import com.muzhi.model.Manor;
import com.muzhi.model.ManorKey;

public interface ManorDao {
    int deleteByPrimaryKey(ManorKey key);

    int insert(Manor record);

    int insertSelective(Manor record);

    Manor selectByPrimaryKey(ManorKey key);

    int updateByPrimaryKeySelective(Manor record);

    int updateByPrimaryKey(Manor record);
    
    List<Manor> selectBySelectivePrimaryKey(ManorKey record);
}