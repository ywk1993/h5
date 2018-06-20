package com.muzhi.model.vo;

import com.muzhi.model.RankType;


/**
* @author ykw
* @version 创建时间：2018年6月13日 下午5:29:29
*/
public class RankVO {
	/**
	 * 赚钱榜
	 */
	private RankType incomeRank;
	/**
	 * 评级榜
	 */
	private RankType rateRank;
	/**
	 * 豪华榜
	 */
	private RankType haohuaRank;
	
	public RankVO() {
		super();
	}

	public RankVO(RankType incomeRank2, RankType rateRank2, RankType haohuaRank2) {
		super();
		this.incomeRank = incomeRank2;
		this.rateRank = rateRank2;
		this.haohuaRank = haohuaRank2;
	}

	public RankType getIncomeRank() {
		return incomeRank;
	}

	public void setIncomeRank(RankType incomeRank) {
		this.incomeRank = incomeRank;
	}

	public RankType getRateRank() {
		return rateRank;
	}

	public void setRateRank(RankType rateRank) {
		this.rateRank = rateRank;
	}

	public RankType getHaohuaRank() {
		return haohuaRank;
	}

	public void setHaohuaRank(RankType haohuaRank) {
		this.haohuaRank = haohuaRank;
	}

	
	
}
