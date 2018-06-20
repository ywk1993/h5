package com.muzhi.dao;

import com.muzhi.model.Signboard;

public interface SignboardDao {
	Signboard getSignboard(Integer id);
	void updateSignboard(Signboard signboard);
	void insertSignboard(Signboard signboard);
}
