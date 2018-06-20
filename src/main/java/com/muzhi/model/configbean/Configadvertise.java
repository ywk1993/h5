package com.muzhi.model.configbean;

public class Configadvertise {
	//宣传编号	备注	每日免费次数	免费次数对应道具	评级需求	花费需求	成功描述	失败描述	暴击描述	名气加成值	成功率	暴击率
	private Integer id;
	private String content;
	private Integer freeNum;
	private String freeProp;
	private Integer needPingJi;
	private String cost;
	private String sucessTip;
	private String failTip;
	private String critTip;
	private Integer addFame;
	private Integer successRate;
	private Integer critRate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFreeNum() {
		return freeNum;
	}
	public void setFreeNum(Integer freeNum) {
		this.freeNum = freeNum;
	}
	public String getFreeProp() {
		return freeProp;
	}
	public void setFreeProp(String freeProp) {
		this.freeProp = freeProp;
	}
	public Integer getNeedPingJi() {
		return needPingJi;
	}
	public void setNeedPingJi(Integer needPingJi) {
		this.needPingJi = needPingJi;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSucessTip() {
		return sucessTip;
	}
	public void setSucessTip(String sucessTip) {
		this.sucessTip = sucessTip;
	}
	public String getFailTip() {
		return failTip;
	}
	public void setFailTip(String failTip) {
		this.failTip = failTip;
	}
	public String getCritTip() {
		return critTip;
	}
	public void setCritTip(String critTip) {
		this.critTip = critTip;
	}
	public Integer getAddFame() {
		return addFame;
	}
	public void setAddFame(Integer addFame) {
		this.addFame = addFame;
	}
	public Integer getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(Integer successRate) {
		this.successRate = successRate;
	}
	public Integer getCritRate() {
		return critRate;
	}
	public void setCritRate(Integer critRate) {
		this.critRate = critRate;
	}
	
	
}
