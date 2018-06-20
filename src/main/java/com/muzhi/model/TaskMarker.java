package com.muzhi.model;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * 任务标记注解
 * @author yany
	1：成功售卖菜品达到X
	2：制作菜品达到X：
	3：制作X品质菜品达到X：
	4：制作X菜品达到Y：
	5：研究力达到X：
	6：豪华度达到X：
	7：收集菜谱数量达到X：
	8：X建筑等级达到X：
	9：雇佣X次数达到X：
	10:制作X等级的菜品达到Y：
	11：在订单中心完成X份订单
 */
@Documented // 说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target(value={ElementType.METHOD,ElementType.PARAMETER, ElementType.LOCAL_VARIABLE}) // 定义注解的作用目标**作用范围字段、枚举的常量/方法
@Repeatable(TaskMarkers.class)
public @interface TaskMarker {
	/**
	 * 任务类型标记
	 * @return
	 */
	@AliasFor("taskType")
	TaskMethod method();
	
	/**
	 * 任务X变量, 一般用于指定类别,需要在其在Result中的全路径地址或者在参数中的名称, 例如制作品质为X的菜n次
	 * @return
	 */
	@AliasFor("xParam")
	String xValue() default "";
	
	/**
	 * 任务Y变量， 一般用于判断数量，需要在其在Result中的全路径地址或者在参数中的名称, 例如 建筑等级达到Y等级
	 * @return
	 */
	@AliasFor("yParam")
	String yValue() default "";
	
	/**
	 * 如果当前x参数无法实现业务，参数相关但需要转换即（模块调用）获取的结果, 通过服务发现接口实现
	 * @return
	 */
	@AliasFor("xRefImpl")
	String xImpl() default "";
	/**
	 * 如果当前y参数无法实现业务，参数相关但需要转换即（模块调用）获取的结果, 通过服务发现接口实现
	 * @return
	 */
	@AliasFor("yRefImpl")
	String yImpl() default "";
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@AliasFor("Result")
	Class isSuccess() default Result.class;
}