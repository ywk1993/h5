package com.muzhi.service;

import java.util.List;
import java.util.Map;

import com.muzhi.model.Article;
import com.muzhi.model.Result;
import com.muzhi.model.configbean.ConfigArticle;
import com.muzhi.model.vo.ArticleData;

public interface ArticleService {
  /**
   * 获取用户当前装扮的装饰 为空会初始化用户数据
   * @param userId
   * @return
   */
  public List<ConfigArticle> getUserArticel(Integer userId);
  /**
   * 获取装扮信息
   * @param token
   * @return
   */
  public ArticleData getArticleInfo(String token);
  /**
   * 激活装扮
   * @param token
   * @param articleId
   * @return
   */
  public Result activateArticle(String token,Integer articleId);
  /**
   * 装扮当前装扮
   * @param token
   * @param articleId
   * @return
   */
  public Result dressArticle(String token,Integer articleId);
  /**
   * 获取用户的摆件信息 
   * @return
   */
  public Map<Integer, Article> getUserArticle(Integer userid);
  /**
   * 可以获取装饰总的豪华度
   * @param userid
   * @return
   */
  public int getTotalHaoHua(Integer userid);
  /**
   * 针对相应类型的售卖食物的爱心奖励加成值
   * @param fooid
   * @param userid
   * @return
   */
  public int rewardLove(Integer fooid,Integer userid);
  
}
