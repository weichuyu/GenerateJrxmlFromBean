package cn.com.sge.gems.ods.dao.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来在对象的类上加入的annotation得到excel标题
 * @author lilei
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelHeaderResources {
	/**
	 * excel的标题名称
	 * @return
	 */
	String title();
}
