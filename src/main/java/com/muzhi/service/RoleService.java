package com.muzhi.service;

import java.util.List;

import com.muzhi.model.User;
import com.muzhi.model.vo.RoleVO;

/**
* @author ykw
* @version 创建时间：2018年3月27日 下午5:21:38
*/
public interface RoleService {
	public List<RoleVO> getRoles(User user);
}
