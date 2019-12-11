package cn.com.sge.gems.ods.dao.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface Label {
	/**
	 * label，类似别名，用用给类或者字段取一个可读性强的名字
	 * */
	String label() default "";

	/**
	 * 用于说明获取名字调用的方法
	 * @return
	 */
	String getter() default "";

	/**
	 * 标识一个字段是否可见。例如密码等敏感字段可设置为false从而不可见。
	 * */
	boolean visible() default true;
}
