package com.muzhi.model.vo;

public class StockResultl {
	private MakeFoodState makeFoodState;
	private int currentNum;
	private int stockLimit;
	/**熟练度状态,0表示未达成熟练地状态；1-5对应各自状态*/
	private int skillStatus;
	/**品质暴击0 没有暴击 1暴击*/
	private int critQuality;
	/** 实际的菜品品质*/
	private int quality;
	/** 熟练度奖励*/
	private String reward;

	public StockResultl() {
		super();
	}

	public StockResultl(MakeFoodState makeFoodState, int currentNum, int stockLimit, int skillStatus, int critQuality,
			int quality,String reward) {
		super();
		this.makeFoodState = makeFoodState;
		this.currentNum = currentNum;
		this.stockLimit = stockLimit;
		this.skillStatus = skillStatus;
		this.critQuality = critQuality;
		this.quality = quality;
		this.reward = reward;
	}

    
	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getCritQuality() {
		return critQuality;
	}

	public void setCritQuality(int critQuality) {
		this.critQuality = critQuality;
	}




	public int getSkillStatus() {
		return skillStatus;
	}

	public void setSkillStatus(int skillStatus) {
		this.skillStatus = skillStatus;
	}

	public MakeFoodState getMakeFoodState() {
		return makeFoodState;
	}

	public void setMakeFoodState(MakeFoodState makeFoodState) {
		this.makeFoodState = makeFoodState;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	public int getStockLimit() {
		return stockLimit;
	}

	public void setStockLimit(int stockLimit) {
		this.stockLimit = stockLimit;
	}

}
