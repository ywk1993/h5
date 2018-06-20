package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.CookBook;

public interface CookBookDao {
	List<CookBook> getList(CookBook cookBook);
	List<CookBook> getListByUserId(@Param("id") Integer id);
	void addList(@Param("cookbookList") List<CookBook> cookbookList);
	/**
	 * 查询玩家唯一id的食谱信息
	 * @param userId
	 * @param bookId
	 * @return
	 */
	CookBook getOneCookbook(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
	/**
	 * 跟新cookbook
	 * @param cookBook
	 */
	void updateCookBook(CookBook cookBook);

	
	int updateByPrimaryKey(CookBook record);
	/**
	 * 获取已经研究完能制作的菜谱
	 * @param id
	 * @return
	 */
	List<CookBook> getCanCookBook(@Param("id") Integer id);

}
