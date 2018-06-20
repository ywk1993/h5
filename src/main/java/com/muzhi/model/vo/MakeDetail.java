package com.muzhi.model.vo;

import java.util.List;

import com.muzhi.model.CurrencyType;
import com.muzhi.model.Reused;

public class MakeDetail {
	/**
	 * 厨师位信息
	 */
	private MakeFoodState makeFoodState;
	/**
	 * 资源使用情况
	 */
	private List<Reused> useInfo;
	/**
	 * 资源剩余情况
	 */
	private List<CurrencyType> remainInfo;
    
	public MakeDetail(MakeFoodState makeFoodState, List<Reused> useInfo, List<CurrencyType> remainInfo) {
		super();
		this.makeFoodState = makeFoodState;
		this.useInfo = useInfo;
		this.remainInfo = remainInfo;
	}
    
	public MakeDetail() {
		super();
	}



	public MakeFoodState getMakeFoodState() {
		return makeFoodState;
	}

	public void setMakeFoodState(MakeFoodState makeFoodState) {
		this.makeFoodState = makeFoodState;
	}



	public List<Reused> getUseInfo() {
		return useInfo;
	}

	public void setUseInfo(List<Reused> useInfo) {
		this.useInfo = useInfo;
	}

	public List<CurrencyType> getRemainInfo() {
		return remainInfo;
	}

	public void setRemainInfo(List<CurrencyType> remainInfo) {
		this.remainInfo = remainInfo;
	}

}
