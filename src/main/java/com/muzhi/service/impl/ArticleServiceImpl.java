package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.ArticleDao;
import com.muzhi.dao.IncomeDao;
import com.muzhi.model.Article;
import com.muzhi.model.Income;
import com.muzhi.model.Rank;
import com.muzhi.model.Result;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigArticle;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.vo.ArticleData;
import com.muzhi.model.vo.ArticleInfo;
import com.muzhi.model.vo.ArticleResult;
import com.muzhi.redis.service.UserRedisService;
import com.muzhi.service.ArticleService;
import com.muzhi.service.RankService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.ResultUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRedisService userRedisService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RankService rankService;
	@Autowired
	private IncomeDao incomeDao;

	@Override
	public List<ConfigArticle> getUserArticel(Integer userId) {
		List<ConfigArticle> list =new ArrayList<>();
		List<Article> userArticle = articleDao.getUserArticle(userId);
		if (userArticle.isEmpty()) {
			Map<Integer, ConfigArticle> articleMap = InitConfig.getInstance().getArticleMap();
			for (Entry<Integer, ConfigArticle> configArticle : articleMap.entrySet()) {
				if (configArticle.getValue().getModel().endsWith("001")) {//状态 0没装扮 1装扮了
					Article article =new Article(userId, configArticle.getValue().getId(), configArticle.getValue().getArticleType(), 1);
					userArticle.add(article);
					articleDao.insert(article);
				}
			}
			
		}
		Map<Integer, ConfigArticle> articleMap = InitConfig.getInstance().getArticleMap();
		for (Article article : userArticle) {
			list.add(articleMap.get(article.getArticleId()));
		}
			
		// 豪华度任务， 待业务分离
		taskService.updateTaskNum(userId, TaskMethod.DELUXE_X.getTaskCondition(), getTotalHaoHua(userId));
		
		return list;
	}

	@Override
	public ArticleData getArticleInfo(String token) {
		
		User user = userService.getUserByToken(token);
		Rank rank = rankService.query(user.getId());
		Map<Integer, Article> userArticle = getUserArticle(user.getId());
		Map<Integer, ConfigArticle> articleMap = InitConfig.getInstance().getArticleMap();
		List<ArticleResult> articleResults = new ArrayList<>();
		Map<Integer, List<ArticleInfo>> map = new HashMap<>();
        int totalHaoHua =0;
		Set<Entry<Integer, ConfigArticle>> entrySet = articleMap.entrySet();
		for (Entry<Integer, ConfigArticle> entry : entrySet) {
			int state = 0;//0未激活不满足条件 1可激活 2.已激活没装扮 3.装扮
			if (userArticle.containsKey(entry.getKey())) {// 用户已有的说明激活过 状态2,3 
				totalHaoHua+=entry.getValue().getHaohua();
				if (userArticle.get(entry.getKey()).getStatus().equals(0)) {//0没装扮 1装扮了
						state=2;
					}else {
						state=3;
					}
			} else {// 没激活的 状态0 或者1
				 int pingji =rank.getRateLevel();//当前用户的评级 
                 if (isCanActivate(user,entry.getValue(),pingji)) {//货币够 激活等级够
                	 state=1;
				}   
			}
			if (map.containsKey(entry.getValue().getArticleType())) {// 如果包含类型了直接拿出来添加
				List<ArticleInfo> articleInfos = map.get(entry.getValue().getArticleType());
				ArticleInfo articleInfo = new ArticleInfo(entry.getKey(), state, entry.getValue());
				articleInfos.add(articleInfo);

			} else {// 不包含当前类型 添加进去
				List<ArticleInfo> articleInfos = new ArrayList<>();
				ArticleInfo articleInfo = new ArticleInfo(entry.getKey(), state, entry.getValue());
				articleInfos.add(articleInfo);
				map.put(entry.getValue().getArticleType(), articleInfos);
			}
		}
		for (Entry<Integer, List<ArticleInfo>> entry : map.entrySet()) {
			Collections.sort(entry.getValue());//排序置顶
			articleResults.add(new ArticleResult(entry.getKey(), entry.getValue()));
		}
		
		// 豪华度任务， 待业务分离
		taskService.updateTaskNum(user.getId(), TaskMethod.DELUXE_X.getTaskCondition(), totalHaoHua);
		
		return new ArticleData(totalHaoHua, articleResults,rank.getRateLevel());//用户当前评级等级
	}

	@Override
	public Result activateArticle(String token, Integer articleId) {
		User user = userService.getUserByToken(token);
		Map<Integer, ConfigArticle> articleMap = InitConfig.getInstance().getArticleMap();
		ConfigArticle configArticle =articleMap.get(articleId);
		Article article = articleDao.selectByPrimaryKey(user.getId(), articleId);
		Rank rank = rankService.query(user.getId());
		int pingji =rank.getRateLevel();//当前用户的评级 
		if (article!=null||!isCanActivate(user,articleMap.get(articleId),pingji)) {
			return ResultUtil.error(1101, "不满足激活条件");
		}
		//减去货币
		int type=configArticle.getUseType();//货币使用类型1为金钱，2为钻石
		int num =configArticle.getNumber();//数量
		if (type==1) {
			user.setGold(user.getGold()-num);
		}
		if (type==2) {
			user.setDiamond(user.getDiamond()-num);
		}
		userService.updateUser(user);
		userRedisService.setUser(token, user);
		
		//自定义的规则新激活的不装扮状态 0没装扮 1装扮了
		article =new Article(user.getId(), configArticle.getId(), configArticle.getArticleType(), 0);
		articleDao.insert(article);
		ArticleData articleInfo = getArticleInfo(token);
		
		//将总的豪华度存进数据库
		Income income = incomeDao.selectByPrimaryKey(user.getId());
		if(null!=income) {
		income.setHaohua(getTotalHaoHua(user.getId()));
		incomeDao.updateByPrimaryKey(income);
		}
		
		return ResultUtil.success(articleInfo);
	}

	@Override
	public Result dressArticle(String token, Integer articleId) {
		User user = userService.getUserByToken(token);
		Article article = articleDao.selectByPrimaryKey(user.getId(), articleId);
		if (article==null) {
			return ResultUtil.error(1102, "当前的装饰尚未激活");
		}
		//切换其他的同类型的装饰为没装扮状态
	    articleDao.updateState(user.getId(), article.getAtricleType());
	    
		article.setStatus(1);// 0没装扮 1装扮了
		articleDao.updateByPrimaryKey(article);
		
		ArticleData articleInfo = getArticleInfo(token);
		return ResultUtil.success(articleInfo);
	}

	@Override
	public Map<Integer, Article> getUserArticle(Integer userid) {
		List<Article> articles = articleDao.selectByUser(userid);
		Map<Integer, Article> map = new HashMap<>(256, 0.75f);
		for (Article article : articles) {
			map.put(article.getArticleId(), article);
		}
		return map;
	}
	/**
	 * 判断是激活条件是否满足true满足
	 * @return
	 */
	private Boolean isCanActivate(User user,ConfigArticle configArticle,Integer pingji) {
		
		int type=configArticle.getUseType();//货币使用类型1为金钱，2为钻石
		int num =configArticle.getNumber();//数量
		int level =configArticle.getCondition();//评级条件
		
		if (type==1&&user.getGold()<num) {
			return false;
		}
		if (type==2&&user.getDiamond()<num) {
			return false;
		}
		if (level>pingji) {
			return false;
		}
		return true;
		
	}
    /**
     * 可以获取装饰总的豪华度
     */
	@Override
	public int getTotalHaoHua(Integer userid) {
		Map<Integer, Article> userArticle = getUserArticle(userid);
		Map<Integer, ConfigArticle> articleMap = InitConfig.getInstance().getArticleMap();
		int totalHaoHua =0;
		Set<Entry<Integer, ConfigArticle>> entrySet = articleMap.entrySet();
		for (Entry<Integer, ConfigArticle> entry : entrySet) {
			if (userArticle.containsKey(entry.getKey())) {
				totalHaoHua+=entry.getValue().getHaohua();
			}
		}
		return totalHaoHua;
	}

	@Override
	public int rewardLove(Integer fooid, Integer userid) {
		int number =0;
		List<Article> userArticle = articleDao.getUserArticle(userid);
		ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(fooid);
		Map<Integer, ConfigArticle> articleMap = InitConfig.getInstance().getArticleMap();
		for (Article article : userArticle) {
			if (articleMap.get(article.getArticleId()).getLoveType().equals(configFood.getFoodtype())) {
				number+=articleMap.get(article.getArticleId()).getLoveAdd();
			}
		}
		return number;
	}
}
