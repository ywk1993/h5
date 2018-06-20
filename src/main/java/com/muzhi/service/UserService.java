package com.muzhi.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Result;
import com.muzhi.model.User;
/**
 * 
 * @author Yuwk
 *
 * 2017年11月21日
 */

public interface UserService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @param nickName  昵称
	 * @param avatarUrl 头像地址
     * @param request
     * @param response
     * @return
     */
	public Result userLogin(String username, String password, String openid, String nickName, String avatarUrl);
	
	/**
	 * 通过
	 * @param openid
	 * @return
	 */
	public String loginFromWeinxin(String openid);
	
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 */
	public User getUserByToken(String token);
	
	/**
	 * 重刷新token
	 * @param string
	 * @return
	 */
	public Result refreshLogin(String string);
	
//	-----------------------------------------------------------------------------------
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public User getUser(Integer id);
	
	/**
	 * 更新用户
	 */
	public void updateUser(User user);
	
	/**
	 * 获取用户列表
	 */
	public List<User> getUserList();
	
	/**
	 * 添加新用户
	 * @return 
	 */
	public User userInit(Integer id);
	
	/**
	 * 批量查询
	 */
	public List<User> selectByIdList(@ Param("idList") List<Integer> idList);
	/**
	 * 获取用户总厨力值
	 * @param user 
	 * @return
	 */
	public Integer getTotalStrength(User user);
	/**
	 * 通过uid获取用户
	 * @param uid
	 * @return
	 */
	public User getUserByUid(Integer uid);

}
