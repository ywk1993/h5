package com.muzhi.service;

/**
 * 建筑状态机
 * @author yany
 *
 */
public interface BuildState {
	/**
	 * 生产
	 */
	public void generate();
	/**
	 * 收取
	 */
	public void collect();
	/**
	 * 研究
	 */
	public void research();
	/**
	 * 升级
	 */
	public void upgrade();
	/**
	 * 取消升级
	 */
	public void quitUpgrade();
	/**
	 * 加速升级
	 */
	public void accelerateUpgrade();
	/**
	 * 加速研究
	 */
	public void accelerateResearch();
}
