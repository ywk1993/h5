package com.muzhi.dao;

import com.muzhi.model.Province;
import java.util.List;

public interface ProvinceDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(Province record);

    Province selectByPrimaryKey(Integer uid);

    List<Province> selectAll();

    int updateByPrimaryKey(Province record);
}