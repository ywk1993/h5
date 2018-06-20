package com.muzhi.service;

import com.muzhi.model.Generator;

/**
 * 资源生产时间
 * @author yany
 *
 */
public interface GeneratorService {
	public int deleteByPrimaryKey(Integer id);

    public int insert(Generator record);

    public int insertSelective(Generator record);

    public Generator selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Generator record);

    public int updateByPrimaryKey(Generator record);
    
    public void initGenerator(Integer userId);
}
