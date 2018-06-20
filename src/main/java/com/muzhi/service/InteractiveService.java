package com.muzhi.service;

import java.util.List;

import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.vo.EmployManager;
import com.muzhi.model.vo.StaffAll;

public interface InteractiveService {
	/**
	 * 雇佣来源接口
	 */
	public List<EmployManager> employSource(User user);
	
	/**
	 * 获取员工：厨师和迎宾
	 */
	public StaffAll getStaff(User user);
	
	/**
	 * 雇佣
	 */
	public Result employ(User user, Integer id, Integer type,Integer index);

	public void staffInit(User userInit);
	/**
	 * 解雇
	 * @param user
	 * @param id
	 * @param type
	 * @return
	 */
	public Result dismiss(User user, Integer id, Integer type);
	/**
     * 获取用户雇佣后增加的总厨力
     * @param user 雇佣者本身
     * @return
     */
	public Integer getEmployStrength(User user);
	/**
     * 获取用户雇佣后增加的总名气
     * @param user 雇佣者本身
     * @return
     */
	public Integer getEmployFame(User user);
	/**
	 * 合同到期后再次雇佣
	 * @param user
	 * @return
	 */
	public Result employAgain(User user,Integer id,Integer type,Integer index);
}
