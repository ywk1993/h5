package com.muzhi.model.vo;

import java.util.List;

public class ArticleResult {
	private Integer type;
	private List<ArticleInfo> articleInfo;
    
	public ArticleResult() {
		super();
	}

	public ArticleResult(Integer type, List<ArticleInfo> articleInfo) {
		super();
		this.type = type;
		this.articleInfo = articleInfo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<ArticleInfo> getArticleInfo() {
		return articleInfo;
	}

	public void setArticleInfo(List<ArticleInfo> articleInfo) {
		this.articleInfo = articleInfo;
	}

}
