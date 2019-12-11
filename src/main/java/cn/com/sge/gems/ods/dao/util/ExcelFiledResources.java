package cn.com.sge.gems.ods.dao.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用来在对象的属性上加入的annotation，通过该annotation说明某个属性所对应的标题
 * @author lilei
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelFiledResources {
	/**
	 * 属性的标题名称
	 * @return
	 */
	String title();
	/**
	 * 在excel的顺序
	 * @return
	 */
	int order() default 9999;
	/**
	 * 是否是数字类型
	 * @return
	 */
	boolean isNaN() default false;
	/**
	 * 数字类型的最大小数位
	 * @return
	 */
	int maximum() default 16;
	/**
	 * 数字类型的最小小数位
	 * @return
	 */
	int mininum() default 0;
}
