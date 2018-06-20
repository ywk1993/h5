package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Rank;
import com.muzhi.model.Ranks;

public interface RankDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Rank record);

    int insertSelective(Rank record);

    Rank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rank record);

    int updateByPrimaryKey(Rank record);
    
    /**
     * 获取地区排行
     * @return
     */
    List<Ranks> getAreaRank();
    /**
     * 获取省份排行
     * @param province
     * @return
     */
    List<Ranks> getProvinceRank(@Param("province") String province);
    /**
     * 获取好友排行
     * @return
     */
    List<Ranks> getFriendRank(@Param("list") List<Integer> list);
}