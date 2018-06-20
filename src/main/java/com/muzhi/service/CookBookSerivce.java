package com.muzhi.service;

import java.util.List;
import com.muzhi.model.CookBook;
import com.muzhi.model.User;

public interface CookBookSerivce {
	public List<CookBook> getList(CookBook cookBook);
	public List<CookBook> getListByUserId(Integer id);
	public void addList(List<CookBook> cookbookList);
	public void cookBookInit(User user);
	/**
	 * 查询玩家唯一id的食谱信息
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public CookBook getOneCookbook(Integer userId, Integer bookId);
	/**
	 * 跟新cookbook
	 * @param cookBook
	 */
	public void updateCookBook(CookBook cookBook);
	/**
	 * 获取可能的暴击加成的品质 到了满级品质不会暴击加成
	 */
	public Integer getAddQuality(Integer userId,Integer bookId);
	
}
