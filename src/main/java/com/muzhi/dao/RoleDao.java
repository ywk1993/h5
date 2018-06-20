package com.muzhi.dao;

import com.muzhi.model.Role;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleDao {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId);

    int insert(Role record);

    Role selectByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
    
    List<Role> selectByUserid(@Param("userId") Integer userId);
}