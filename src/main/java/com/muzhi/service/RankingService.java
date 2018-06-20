package com.muzhi.service;

import com.muzhi.model.vo.RankVO;

/**
* @author ykw
* @version 创建时间：2018年6月14日 下午2:18:51
*/
public interface RankingService {
	/**
	 * 获取排行榜
	 * @param token
	 * @param page
	 * @param pageNum
	 * @return
	 */
	public RankVO getRanking(String token,Integer page,Integer pageNum);
}
