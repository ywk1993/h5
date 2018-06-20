package com.muzhi.dao;

import com.muzhi.model.Ranking;
import com.muzhi.model.Ranks;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RankingDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(Ranking record);

    Ranking selectByPrimaryKey(Integer uid);

    List<Ranking> selectAll();

    int updateByPrimaryKey(Ranking record);
    /**
     * 更新收益
     */
    void updateIncome();
    /**
     * 更新评级
     */
    void updateRate();
    /**
     * 更新豪华度
     */
    void updateHaohua();
    
    List<Ranks> getIncomeAreaRank();
    
    List<Ranks> getIncomeProvinceRank(@Param("province") String province);
    
    List<Ranks> getIncomeFriendRank(@Param("list") List<Integer> list);
    
    List<Ranks> getRateAreaRank();
    
    List<Ranks> getRateProvinceRank(@Param("province") String province);
    
    List<Ranks> getRateFriendRank(@Param("list") List<Integer> list);
    
    List<Ranks> getHaohuaAreaRank();
    
    List<Ranks> getHaohuaProvinceRank(@Param("province") String province);
    
    List<Ranks> getHaohuaFriendRank(@Param("list") List<Integer> list);
}