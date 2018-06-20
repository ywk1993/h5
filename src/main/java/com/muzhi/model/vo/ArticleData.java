package com.muzhi.model.vo;

import java.util.List;

public class ArticleData {
	private Integer totalHaohua;
	private List<ArticleResult> articleResults;
	private Integer userPingJi;
    
	public ArticleData() {
		super();
	}
    
	
    
	public ArticleData(Integer totalHaohua, List<ArticleResult> articleResults, Integer userPingJi) {
		super();
		this.totalHaohua = totalHaohua;
		this.articleResults = articleResults;
		this.userPingJi = userPingJi;
	}



	public Integer getUserPingJi() {
		return userPingJi;
	}

	public void setUserPingJi(Integer userPingJi) {
		this.userPingJi = userPingJi;
	}

	public Integer getTotalHaohua() {
		return totalHaohua;
	}

	public void setTotalHaohua(Integer totalHaohua) {
		this.totalHaohua = totalHaohua;
	}

	public List<ArticleResult> getArticleResults() {
		return articleResults;
	}

	public void setArticleResults(List<ArticleResult> articleResults) {
		this.articleResults = articleResults;
	}

}
