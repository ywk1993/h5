package com.muzhi.model.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.muzhi.model.configbean.ConfigArticle;

public class ArticleInfo implements Comparable<ArticleInfo>{
    private Integer id;
    private Integer state;
    private ConfigArticle configArticle;
	
	public ArticleInfo() {
		super();
	}
	public ArticleInfo(Integer id, Integer state, ConfigArticle configArticle) {
		super();
		this.id = id;
		this.state = state;
		this.configArticle = configArticle;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public ConfigArticle getConfigArticle() {
		return configArticle;
	}
	public void setConfigArticle(ConfigArticle configArticle) {
		this.configArticle = configArticle;
	}
	@Override
	public int compareTo(ArticleInfo o) {
		
		return o.state-this.state;
	}
	public static void main(String[] args) {
		List<ArticleInfo> articleInfos = new ArrayList<>();
		articleInfos.add(new ArticleInfo(1, 1, null));
		articleInfos.add(new ArticleInfo(1, 3, null));
		articleInfos.add(new ArticleInfo(1, 2, null));
		articleInfos.add(new ArticleInfo(1, 4, null));
		for (ArticleInfo articleInfo : articleInfos) {
			System.out.println(articleInfo.getState());
		}
		Collections.sort(articleInfos);
		for (ArticleInfo articleInfo : articleInfos) {
			System.out.println(articleInfo.getState());
		}
	}
    
}
