package com.changhong.xbrl.gateway.util;

import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <p>Title: 日期处理公共类</p>
 * <p>Description: 用于定义日期相关的公共静态方法</p>
 * <p>Company: changhong </p>
 * @author liBo
 * @date 2015-3-10
 * @version 1.0
 */
public class UtilDataTime {

	protected static org.slf4j.Logger logger = LoggerFactory.getLogger(UtilDataTime.class);
	public static String fullPattern2 = "yyyyMMddHHmmssSSS";
	public static String ymdhPattern = "yyyyMMddHH";

	/**
	 * 返回减去指定小时数的日期.
	 *
	 * @param date
	 *            将运算日期.
	 * @param hour
	 *            减去的小时. return
	 */
	public static Date subtractTime(final Date date, final int hour) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currHour = Calendar.HOUR_OF_DAY;
			calendar.set(currHour, calendar.get(currHour) - hour); // 让日期减 day天
			return calendar.getTime();
		} catch (Exception ex) {

			return null;
		}
	}
	
	/**
	 * 获取指定格式日期字附后串
	 * @param format 日期格式
	 * @return 日期字附串
	 */
	public static String getformatdate(String format)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sp = new SimpleDateFormat(format);
		return sp.format(calendar.getTime());
	}
	



	/**
	 * 将字符串转换成指定格式的日期.
	 * 
	 * @param str
	 *            日期字符串.
	 * @param dateFormat
	 *            日期格式. 如果为空，默认为:yyyy-MM-dd HH:mm:ss.
	 * @return
	 */
	public static Date strToDate(final String str, String dateFormat) {
		if (str == null || str.trim().length() == 0)
			return null;
		try {
			if (dateFormat == null || dateFormat.length() == 0)
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			DateFormat fmt = new SimpleDateFormat(dateFormat);
			return fmt.parse(str.trim());

		} catch (Exception ex) {
			/*
			 * log.error("将字符串(" + str + ")转换成指定格式(" + dateFormat +
			 * ")的日期时失败！错误原因：" + ex.getMessage());
			 */
			return null;
		}
	}

	/**
	 * 将当前日期转换成yyyyMMddHHmmss的字符串. 如：20071012141350
	 * 
	 * @return
	 */
	public static String currDateToStr() {
		return dateToStr(new Date(), "yyyyMMddHHmmss");
	}






	/**
	 * 数据库的日期类型转换成JAVA的DATE类型
	 *
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static Date dateToDate(final Date date, String dateFormat) {
		if (date == null) {
			// log.debug("未知时间");
			return null;
			// return "未知时间";
		}
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		String time = sf.format(date);
		return Timestamp.valueOf(time);
	}

	/**
	 * 将日期转换成指定格式的字符串.
	 *
	 * @param date
	 *            日期
	 * @param dateFormat
	 *            日期格式. 如果为空，默认为:yyyy-MM-dd HH:mm:ss.
	 * @return
	 */
	public static String dateToStr(final Date date, String dateFormat) {
		if (date == null) {
			// log.debug("未知时间");
			return "";
			// return "未知时间";
		}
		try {
			if (dateFormat == null || dateFormat.trim().length() == 0)
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			if ("yyyy-MM-dd".equals(dateFormat))
				dateFormat = "yyyy-MM-dd";
			DateFormat fmt = new SimpleDateFormat(dateFormat.trim());

			return fmt.format(date);
		} catch (Exception ex) {
			logger.error("将日期转换成指定格式(" + dateFormat + ")的字符串时失败！错误原因：" +
			 ex.getMessage());

			return "";
			// return "日期格式不匹配";
		}
	}

	/**
	 * 返回减去指定天数的日期.
	 *
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            减去的天数. return
	 */
	public static Date subtractDate(final Date date, final int day) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currDay = Calendar.DAY_OF_MONTH;
			calendar.set(currDay, calendar.get(currDay) - day); // 让日期减 day天

			return calendar.getTime();
		} catch (Exception ex) {

			return null;
		}
	}

	/**
	 * 取得指定日期date月份最大天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMaxDayOfMonth(Date date) {
		int max = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		max = cal.get(Calendar.DAY_OF_MONTH);

		return max;
	}


	/**
	 * 计算当前日期至此日期的天数差
	 * 
	 * @param c1
	 * @return
	 */
	public static int getDateNumber(Calendar c1) {
		long bts1, bts2;

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());

		bts1 = c1.getTimeInMillis() - cal1.getTimeInMillis();
		bts2 = bts1 / (24 * 60 * 60 * 1000); // 结果也有可能超出int,故用long bts2

		return (int) bts2;
	}

    public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
    	  int elapsed = 0;
    	  GregorianCalendar gc1, gc2;

    	  if (g2.after(g1)) {
    	   gc2 = (GregorianCalendar) g2.clone();
    	   gc1 = (GregorianCalendar) g1.clone();
    	  } else {
    	   gc2 = (GregorianCalendar) g1.clone();
    	   gc1 = (GregorianCalendar) g2.clone();
    	  }

    	  gc1.clear(Calendar.MILLISECOND);
    	  gc1.clear(Calendar.SECOND);
    	  gc1.clear(Calendar.MINUTE);
    	  gc1.clear(Calendar.HOUR_OF_DAY);

    	  gc2.clear(Calendar.MILLISECOND);
    	  gc2.clear(Calendar.SECOND);
    	  gc2.clear(Calendar.MINUTE);
    	  gc2.clear(Calendar.HOUR_OF_DAY);

    	  while (gc1.before(gc2)) {
    	   gc1.add(Calendar.DATE, 1);
    	   elapsed++;
    	  }
    	  return elapsed;
    	 }

	/**
	 * 计算特定日期加上/减去数组的中年月日对应Calendar类型  
	 * @param baseDate
	 * @param overplus
	 * @param sign
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Calendar getCalendarForOverplus(Date baseDate, String overplus, String sign){
		// 解析传入X年X个月X天字符
		Calendar c1 = Calendar.getInstance();
		c1.setTime(baseDate);
		int year = 0;				//年
		int month = 0;				//月
		int day = 0;				//天
		int indexYear = overplus.indexOf("年");
		int indexMonth1 = overplus.indexOf("个");
		int indexMonth2 = overplus.indexOf("月");
		int indexDay = overplus.indexOf("天");
		boolean flag = false;
		
		try {
			
			if (indexYear != -1) {
				year = Integer.parseInt(overplus.substring(0,indexYear));
				flag = true;
			}
			if (indexMonth1 != -1) {
				month = Integer.parseInt(overplus.substring(indexYear+1,indexMonth1));
				flag = true;
			}
			if (indexDay != -1) {
				day = Integer.parseInt(overplus.substring(indexMonth2+1,indexDay));
				flag = true;
			}

			if (!flag){
				year = Integer.valueOf(overplus);
			}
			if("+".equals(sign)){
				c1.set(c1.YEAR, c1.get(c1.YEAR) + year);
				c1.set(c1.MONTH, c1.get(c1.MONTH) + month);
				c1.set(c1.DAY_OF_MONTH, c1.get(c1.DAY_OF_MONTH) + day);
			}else if("-".equals(sign)){
				c1.set(c1.YEAR, c1.get(c1.YEAR) - year);
				c1.set(c1.MONTH, c1.get(c1.MONTH) - month);
				c1.set(c1.DAY_OF_MONTH, c1.get(c1.DAY_OF_MONTH) - day);
			}				
		} catch (Exception ex) {
			logger.error("计算时间段时失败！错误原因：" + ex.getMessage());
		}
		return c1;
	}
	
	/**
	 * 转化为数据库使用的时间格式
	 * @param date
	 * @return
	 */
	public static java.sql.Date toSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	/**
     * 获取当前时间 精确到毫秒
     * @return
     */
    public static String getDateFormatString() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String str ="";
        try {  
             str = format1.format(new Date());
        } catch (Exception e) {
			logger.error("获取当前时间:"+e.getMessage());
        }  
        return str;  
    }  
}
