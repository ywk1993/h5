package com.muzhi.model;

public class SkillReward {
	private Integer uid;
	private Integer tid;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public SkillReward(Integer uid, Integer tid) {
		super();
		this.uid = uid;
		this.tid = tid;
	}

	public SkillReward() {
		super();
	}
    
}
