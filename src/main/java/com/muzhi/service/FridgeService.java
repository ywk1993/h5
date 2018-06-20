package com.muzhi.service;

import java.util.List;
import com.muzhi.model.Fridge;
import com.muzhi.model.Reused;
import com.muzhi.model.User;

/**
 * 冰箱服务
 * 
 * @author yany
 *
 */
public interface FridgeService {

	public int deleteByPrimaryKey(Integer id);

	public int insert(Fridge record);

	public int insertSelective(Fridge record);

	public Fridge selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(Fridge record);

	public int updateByPrimaryKey(Fridge record);

	public Fridge getFridge(Integer id);

	public void updateFridge(Fridge fridge);

	/**
	 * 判断食材是否足够
	 * 
	 * @param id
	 *            用户id
	 * @param needlist
	 *            食材需求列表 按照顺序不需要的填0 eg:1,1,1,0,0 true 足够 false 不足
	 * @return
	 */
	public int isEnough(Integer id, List<Reused> needlist);

	/**
	 * 消耗材料
	 * 
	 * @param id
	 *            用户id
	 * @param needlist
	 *            食材需求列表 按照顺序不需要的填0 eg:1,1,1,0,0
	 * @return
	 */
	public Fridge expendMaterial(String token, List<Reused> needlist);
	/**
	 * 返还食材
	 * @param id
	 * @param needlist
	 * @return
	 */
	public Fridge addMaterial(String token, List<Reused> needlist);

	public void fridgeInit(User userInit);
}
