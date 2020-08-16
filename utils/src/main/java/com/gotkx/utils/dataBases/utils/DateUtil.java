package com.gotkx.utils.dataBases.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String DATESTYLE = "yyyyMMdd";

	public static String toDateFormat(String str) {
		StringBuilder date = new StringBuilder();
		if (str.length() < 8) {
			str = "11110101";// 处理日期不规范数据
		}
		String year = str.substring(0, 4);
		String mon = str.substring(4, 6);
		String day = str.substring(6, 8);
		return date.append(year).append("-").append(mon).append("-")
				.append(day).toString();
	}

	/**
	 * 将字符串转为java.sql.Date类型
	 * 
	 * @author hd
	 * @date 2017-3-26
	 * @return java.sql.Date
	 * @exception
	 */
	public static java.sql.Date strToSqlDate(String strDate) {
		// String str = strDate;
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		// java.util.Date d = null;
		// try {
		// d = format.parse(str);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// java.sql.Date date = new java.sql.Date(d.getTime());
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		try {
			date = java.sql.Date.valueOf(strDate);
		} catch (Exception e) {
			// System.out.println("输入的日期字符串为：" + strDate
			// + "该成文日期不符合,日期格式需要设置为yyyy-MM-dd格式");
		}

		return date;
	}

	public static java.sql.Date strToDate(String strDate, String format) {
		String str = strDate;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.sql.Date date = null;
		try {
			date = (java.sql.Date) sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 对日期进行格式化
	 * 
	 * @param date
	 *            日期
	 * @param sf
	 *            日期格式
	 * @return 字符串
	 */
	public static String formatDate(final Date date, final String sf) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateformat = new SimpleDateFormat(sf);
		return dateformat.format(date);
	}

	public static String getYear(final Date date) {
		return formatDate(date, "yyyy");
	}

	public static void main(String[] args) {
		// System.out.println(strToDate("2017-02-02"));
		// System.out.println(strToDate("2017-12-02"));
		// System.out.println("去掉空格" + " 20178811".trim());
		// System.out.println(strToSqlDate("2017-02-02"));
		String str = "2013.03";
		System.out.println(str.substring(0, 4));

	}

}
