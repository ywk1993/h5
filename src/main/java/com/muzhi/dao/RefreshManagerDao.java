package com.muzhi.dao;

import com.muzhi.model.RefreshManager;

public interface RefreshManagerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RefreshManager record);

    int insertSelective(RefreshManager record);

    RefreshManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefreshManager record);

    int updateByPrimaryKey(RefreshManager record);
}