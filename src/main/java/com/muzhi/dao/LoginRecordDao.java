package com.muzhi.dao;

import com.muzhi.model.LoginRecord;
import java.util.List;

public interface LoginRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(LoginRecord record);

    LoginRecord selectByPrimaryKey(String id);

    List<LoginRecord> selectAll();

    int updateByPrimaryKey(LoginRecord record);
}