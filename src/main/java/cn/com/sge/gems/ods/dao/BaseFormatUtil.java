package cn.com.sge.gems.ods.dao;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 将数字格式转换成字符串
 * @author ouan
 *
 */
public class BaseFormatUtil {
	/**
	 * 将数字格式转换成字符串保留2位小数
	 * @param number
	 * @return
	 */
	public static String numberFormat(BigDecimal number) {
		if (number == null) {
			return null;
		}
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		return format.format(number);
	}
	/**
	 * 将数字格式转换成字符串保留指定位小数
	 * @param number
	 * @param fractionDigit
	 * @return
	 */
	public static String numberFormat(BigDecimal number, int fractionDigit) {
		if (number == null) {
			return null;
		}
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(fractionDigit);
		format.setMaximumFractionDigits(fractionDigit);
		return format.format(number);
	}
	/**
	 * 将数字格式转换成字符串保留最大最小指定位小数
	 * @param number
	 * @param minfractionDigit
	 * @param maxfractionDigit
	 * @return
	 */
	public static String numberFormat(BigDecimal number, int minfractionDigit,
			int maxfractionDigit) {
		if (number == null) {
			return null;
		}
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(minfractionDigit);
		format.setMaximumFractionDigits(maxfractionDigit);
		return format.format(number);
	}
	public static String percentFormat(BigDecimal number, int minfractionDigit,
			int maxfractionDigit){
		if (number == null) {
			return null;
		}
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMinimumFractionDigits(minfractionDigit);
		percent.setMaximumFractionDigits(maxfractionDigit);
		percent.setGroupingUsed(false);
        return percent.format(number);
	}
	/**
	 * 增加是否要千分位符
	 */
	public static String numberFormat(BigDecimal number, int minfractionDigit,
			int maxfractionDigit,boolean groupingUsed) {
		if (number == null) {
			return null;
		}
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(minfractionDigit);
		format.setMaximumFractionDigits(maxfractionDigit);
		format.setGroupingUsed(groupingUsed);
		return format.format(number);
	}
}
