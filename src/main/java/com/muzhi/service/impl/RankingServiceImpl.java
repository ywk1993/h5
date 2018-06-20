package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.FriendDao;
import com.muzhi.dao.LoginDao;
import com.muzhi.dao.ProvinceDao;
import com.muzhi.dao.RankingDao;
import com.muzhi.model.Friend;
import com.muzhi.model.Login;
import com.muzhi.model.Province;
import com.muzhi.model.RankType;
import com.muzhi.model.Ranking;
import com.muzhi.model.Ranks;
import com.muzhi.model.User;
import com.muzhi.model.vo.RankVO;
import com.muzhi.service.RankingService;
import com.muzhi.service.UserService;
import com.muzhi.util.PageUtil;

/**
* @author ykw
* @version 创建时间：2018年6月14日 下午2:19:21
*/
@Service
public class RankingServiceImpl implements RankingService{
	@Autowired
	private RankingDao rankingDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public RankVO getRanking(String token,Integer page,Integer pageNum) {
		User user = userService.getUserByToken(token);
		Ranking ranking = rankingDao.selectByPrimaryKey(user.getId());
		
		//收益榜
		List<Ranks> incomeArea = getIncomeAreaRank(page,pageNum);
		List<Ranks> incomeProvince = getIncomeProvinceRank(user.getId(),page,pageNum);
		List<Ranks> incomeFriend = getIncomeFriendRank(user.getId(),page,pageNum);
		//个人排行
		RankType incomeRanks = getSelfRank(user.getId(), new RankType(getIncomeAreaRank(null,null), getIncomeProvinceRank(user.getId(),null,null), getIncomeFriendRank(user.getId(),null,null)), ranking.getIncome());
		RankType incomeRank = new RankType(incomeArea, incomeProvince, incomeFriend,incomeRanks.getAreaSelf(),incomeRanks.getProvinceSelf(),incomeRanks.getFriendSelf());
		
		//评级榜
		List<Ranks> rateArea = getRateAreaRank(page,pageNum);
		List<Ranks> rateProvince = getRateProvinceRank(user.getId(),page,pageNum);
		List<Ranks> rateFriend = getRateFriendRank(user.getId(),page,pageNum);
		//个人排行
		RankType rateRanks = getSelfRank(user.getId(), new RankType(getRateAreaRank(null,null), getRateProvinceRank(user.getId(),null,null), getRateFriendRank(user.getId(),null,null)), ranking.getRateLevel());
		RankType rateRank = new RankType(rateArea, rateProvince, rateFriend,rateRanks.getAreaSelf(),rateRanks.getProvinceSelf(),rateRanks.getFriendSelf());
		
		//豪华榜
		List<Ranks> haohuaArea = getHaohuaAreaRank(page,pageNum);
		List<Ranks> haohuaProvince = getHaohuaProvinceRank(user.getId(),page,pageNum);
		List<Ranks> haohuaFriend = getHaohuaFriendRank(user.getId(),page,pageNum);
		//个人排行
		RankType haohuaRanks = getSelfRank(user.getId(), new RankType(getHaohuaAreaRank(null,null), getHaohuaProvinceRank(user.getId(),null,null), getHaohuaFriendRank(user.getId(),null,null)), ranking.getHaohua());
		RankType haohuaRank = new RankType(haohuaArea, haohuaProvince, haohuaFriend,haohuaRanks.getAreaSelf(),haohuaRanks.getProvinceSelf(),haohuaRanks.getFriendSelf());
		
		RankVO result = new RankVO(incomeRank, rateRank, haohuaRank);
		
		return result;
	}
	
	List<Ranks> getIncomeAreaRank(Integer page,Integer pageNum){
		List<Ranks> list = getRowNum(rankingDao.getIncomeAreaRank());
		if(null==page || pageNum==null) {
			return list;
		}
		return PageUtil.dataPaging(list, page, pageNum);
	}
    
    List<Ranks> getIncomeProvinceRank(Integer uid,Integer page,Integer pageNum){
    	Province province = provinceDao.selectByPrimaryKey(uid);
    	
    	List<Ranks> list = rankingDao.getIncomeProvinceRank(province.getProvince());
    	if(null==page || pageNum==null) {
			return list;
		}
    	
    	return PageUtil.dataPaging(getRowNum(list), page, pageNum);
    }
    
    List<Ranks> getIncomeFriendRank(Integer uid,Integer page,Integer pageNum){
    	List<Friend> friends = friendDao.selectList(new Friend(uid));
    	List<Integer> paramList = new ArrayList<Integer>();
		for (Friend friend : friends) {
			paramList.add(friend.getFriendid());
		}
		paramList.add(uid);
		
		List<Ranks> resultList = rankingDao.getIncomeFriendRank(paramList);
		if(null==page || pageNum==null) {
			return resultList;
		}
		
    	return PageUtil.dataPaging(getRowNum(resultList), page, pageNum);
    }
    
    List<Ranks> getRateAreaRank(Integer page,Integer pageNum){
    	List<Ranks> list = getRowNum(rankingDao.getRateAreaRank());
    	if(null==page || pageNum==null) {
			return list;
		}
    	
    	return PageUtil.dataPaging(list, page, pageNum);
    }
    
    List<Ranks> getRateProvinceRank(Integer uid,Integer page,Integer pageNum){
    	Province province = provinceDao.selectByPrimaryKey(uid);
    	
    	List<Ranks> list = rankingDao.getRateProvinceRank(province.getProvince());
    	if(null==page || pageNum==null) {
			return list;
		}
    	
    	return PageUtil.dataPaging(getRowNum(list), page, pageNum);
    }
    
    List<Ranks> getRateFriendRank(Integer uid,Integer page,Integer pageNum){
    	List<Friend> friends = friendDao.selectList(new Friend(uid));
    	List<Integer> paramList = new ArrayList<Integer>();
		for (Friend friend : friends) {
			paramList.add(friend.getFriendid());
		}
		paramList.add(uid);
		
		List<Ranks> resultList = rankingDao.getRateFriendRank(paramList);
		if(null==page || pageNum==null) {
			return resultList;
		}
		return PageUtil.dataPaging(getRowNum(resultList), page, pageNum);
    }
    
    List<Ranks> getHaohuaAreaRank(Integer page,Integer pageNum){
    	
    	List<Ranks> list = getRowNum(rankingDao.getHaohuaAreaRank());
    	if(null==page || pageNum==null) {
			return list;
		}
    	
    	return PageUtil.dataPaging(list, page, pageNum);
    }
    
    List<Ranks> getHaohuaProvinceRank(Integer uid,Integer page,Integer pageNum){
    	Province province = provinceDao.selectByPrimaryKey(uid);
    	
    	List<Ranks> list = rankingDao.getHaohuaProvinceRank(province.getProvince());
    	if(null==page || pageNum==null) {
			return list;
		}
    	
    	return PageUtil.dataPaging(getRowNum(list), page, pageNum);
    }
    
    List<Ranks> getHaohuaFriendRank(Integer uid,Integer page,Integer pageNum){
    	List<Friend> friends = friendDao.selectList(new Friend(uid));
    	List<Integer> paramList = new ArrayList<Integer>();
		for (Friend friend : friends) {
			paramList.add(friend.getFriendid());
		}
		paramList.add(uid);
		
		List<Ranks> resultList = rankingDao.getHaohuaFriendRank(paramList);
		if(null==page || pageNum==null) {
			return resultList;
		}
		
		return PageUtil.dataPaging(getRowNum(resultList), page, pageNum);
    }
    
    List<Ranks> getRowNum(List<Ranks> list){
    	int num = 1;
    	for (Ranks ranks : list) {
			ranks.setRowNum(num++);
		}
    	return list;
    }
    /**
     * 获取收益个人排行
     * @param rankType
     * @return
     */
    public RankType getSelfRank(Integer uid,RankType rankType,Integer rankValue) {
    	List<Ranks> areaRanks = rankType.getAreaRanks();
    	for (Ranks ranks : areaRanks) {
			if(ranks.getId()==uid) {
				rankType.setAreaSelf(ranks);
				break;
			}
		}
    	
    	List<Ranks> provinceRanks = rankType.getProvinceRanks();
    	for (Ranks ranks : provinceRanks) {
    		if(ranks.getId()==uid) {
				rankType.setProvinceSelf(ranks);
				break;
			}
		}
    	
    	List<Ranks> friendRanks = rankType.getFriendRanks();
    	for (Ranks ranks : friendRanks) {
			if(ranks.getId()==uid) {
				rankType.setFriendSelf(ranks);
				break;
			}
		}
    	
    	Login login = loginDao.selectByPrimaryKey(uid);
		
    	if(null == rankType.getAreaSelf()) {
    		Ranks ranks = new Ranks(uid, login.getUsername(),rankValue, -1, login.getAvatarUrl());
    		rankType.setAreaSelf(ranks);
    	}
    	
    	if(null == rankType.getProvinceSelf()) {
    		Ranks ranks = new Ranks(uid, login.getUsername(), rankValue, -1, login.getAvatarUrl());
    		rankType.setProvinceSelf(ranks);
    	}
    	if(null == rankType.getFriendSelf()) {
    		Ranks ranks = new Ranks(uid, login.getUsername(), rankValue, -1, login.getAvatarUrl());
    		rankType.setFriendSelf(ranks);
    	}
    	
    	return rankType;
    }
}
