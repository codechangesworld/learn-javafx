/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.address.util
 * @file DateUtil.java
 * @author zuoguoqing
 * @date 2017年4月9日
 * @version 
 */
package name.zuoguoqing.jfx.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author zuoguoqing
 *
 */
public class DateUtil {
	private static final String DATE_PATTEN = "yyyy.MM.dd";
	private static final DateTimeFormatter FORMATTER = 
			DateTimeFormatter.ofPattern(DATE_PATTEN);
	
	public static String format(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return FORMATTER.format(localDate);
	}
	
	public static LocalDate parse(String dateString) {
		return FORMATTER.parse(dateString, LocalDate::from);
	}
	
	public static boolean validDate(String dateString) {
		return parse(dateString) != null;
	}
}
