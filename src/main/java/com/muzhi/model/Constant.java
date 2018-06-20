package com.muzhi.model;

public interface Constant {


	/**长token过期时间(单位/秒)24*30*3600*/
	static final Integer LONGTOKEN = 2592000;
	/**token过期时间(单位/秒)*/
	static final Integer TOKENEXPIRE = 1800;
	
	/**最多解锁雇佣个数 */
	static final Integer TOTAL_UNLOACK = 4;
	/**demo版暂时只显示最多雇佣2个迎宾*/
	static final Integer TOTAL_ASHERS = 2;
	/**员工离开时间*/
	static final Integer STAFF_LEFTTIME = 86400;
	/**品质等级最高*/
	static final Integer QUALITYLEVEL =6;
}
