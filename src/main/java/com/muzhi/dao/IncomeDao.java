package com.muzhi.dao;

import java.util.List;

import com.muzhi.model.Income;

public interface IncomeDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(Income record);

    Income selectByPrimaryKey(Integer uid);

    List<Income> selectAll();

    int updateByPrimaryKey(Income record);
    
    void updateIncome();
}