package com.muzhi.dao;

import com.muzhi.model.CookBench;

public interface CookBenchDao {
	CookBench getCookBench(Integer id);
	void insert(CookBench cookBench);
	void updateCookBench(CookBench cookBench);
}
