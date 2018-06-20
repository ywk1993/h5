package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Task;
import com.muzhi.model.TaskKey;

public interface TaskDao {
    int deleteByPrimaryKey(TaskKey key);

    int insert(Task record);

    int insertSelective(Task record);
    
    int insertList(@Param("recordList")List<Task> recordList);

    Task selectByPrimaryKey(TaskKey key);
    
    List<Task> selectList(Task record);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    
    int updateList(@Param("recordList")List<Task> recordList);
    
    int updateSelective(Task record);
}