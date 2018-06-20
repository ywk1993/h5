package com.muzhi.dao;

import com.muzhi.model.Article;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleDao {
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Article record);
    /**
     * 查询单条数据
     * @param id
     * @param articleId
     * @return
     */
    Article selectByPrimaryKey(@Param("id") Integer id, @Param("articleId") Integer articleId);
    /**
     * 获取用户所有的摆件（已经激活的才会入库）
     * @param id
     * @return
     */
    List<Article> selectByUser(@Param("id") Integer id);
    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Article record);
    /**
     * 将同种类型的装饰的装扮状态为1的都改为0  0没装扮 1装扮了
     * @param id
     * @param atricleType
     */
    void updateState(@Param("id") Integer id,@Param("atricleType") Integer atricleType);
    /**
     * 获取用户已经装扮的装饰 用于大厅数据的拉取
     */
    List<Article> getUserArticle(@Param("id") Integer id);
}