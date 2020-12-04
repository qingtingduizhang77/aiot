package com.cityiot.guanxin.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Date操作工具类
 */
public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 格式: yyyy-MM-dd HH:mm:ss *
     */
    public static final String PATTERN_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式: yyyy-MM-dd *
     */
    public static final String PATTERN_DATE = "yyyy-MM-dd";

    public static final String PATTERN_MONTH = "yyyy-MM";
    /**
     * 格式: HH:mm:ss *
     */
    public static final String PATTERN_TIME = "HH:mm:ss";
    /**
     * 格式: yyyy-MM-dd HH:mm:ss.SSS *
     */
    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 解析时间/日期字符串
     *
     * @param pattern 格式
     * @param source  时间/日期字符串
     * @return Date
     */
    public static final Date parse(String pattern, String source) {
        try {
            return new SimpleDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            log.warn("Parse Date Error ==> pattern:{}, source:{}", pattern, source);
            return null;
        }
    }

    /**
     * 解析时间/日期字符串,格式：yyyy-MM-dd HH:mm:ss
     *
     * @param source 时间/日期字符串
     * @return Date
     */
    public static final Date parseTimestamp(String source) {
        try {
            if (source == null || "".equals(source)) {
                return null;
            }
            return new SimpleDateFormat(PATTERN_TIMESTAMP).parse(source);
        } catch (ParseException e) {
            log.warn("Parse Date Error ==> pattern:{}, source:{}", PATTERN_TIMESTAMP, source);
            return null;
        }
    }

    /**
     * 解析日期字符串,格式：yyyy-MM-dd
     *
     * @param source 日期字符串
     * @return Date
     */
    public static final Date parseDate(String source) {
        try {
            return new SimpleDateFormat(PATTERN_DATE).parse(source);
        } catch (ParseException e) {
            log.warn("Parse Date Error ==> pattern:{}, source:{}", PATTERN_DATE, source);
            return null;
        }
    }

    /**
     * 解析时间字符串,格式：HH:mm:ss
     *
     * @param source 时间字符串
     * @return Date
     */
    public static final Date parseTime(String source) {
        try {
            return new SimpleDateFormat(PATTERN_TIME).parse(source);
        } catch (ParseException e) {
            log.warn("Parse Date Error ==> pattern:{}, source:{}", PATTERN_TIME, source);
            return null;
        }
    }

    /**
     * 解析时间字符串,格式：yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param source 时间字符串
     * @return Date
     */
    public static final Date parseFull(String source) {
        try {
            return new SimpleDateFormat(PATTERN_FULL).parse(source);
        } catch (ParseException e) {
            log.warn("Parse Date Error ==> pattern:{}, source:{}", PATTERN_FULL, source);
            return null;
        }
    }

    /**
     * 格式化Date
     *
     * @param pattern 格式
     * @param date    date对象
     * @return String 格式化日期字符串
     */
    public static final String format(String pattern, Date date) {
        return new SimpleDateFormat(pattern, Locale.US).format(date);
    }

    /**
     * 格式化Date,格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date date对象
     * @return String
     */
    public static final String formatTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(PATTERN_TIMESTAMP).format(date);
    }

    /**
     * 格式化Date,格式：yyyy-MM-dd
     *
     * @param date date对象
     * @return String
     */
    public static final String formatDate(Date date) {
        return new SimpleDateFormat(PATTERN_DATE).format(date);
    }


    /**
     * 格式化Date,格式：HH:mm:ss
     *
     * @param date date对象
     * @return String
     */
    public static final String formatTime(Date date) {
        return new SimpleDateFormat(PATTERN_TIME).format(date);
    }

    /**
     * 格式化Date,格式：yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param date date对象
     * @return String
     */
    public static final String formatFull(Date date) {
        return new SimpleDateFormat(PATTERN_FULL).format(date);
    }

    /**
     * 格式化时间/日期字符串
     *
     * @param outPatt 输出格式
     * @param inPatt  输入格式(与source对应)
     * @param source  时间/日期字符串(与inPatt对应)
     * @return String
     */
    public static final String format(String outPatt, String inPatt, String source) {
        return format(outPatt, parse(inPatt, source));
    }

    /**
     * 按指定格式取得当前日期(时间)
     *
     * @param pattern 格式
     * @return String
     */
    public static final String getTimestamp(String pattern) {
        return format(pattern, new Date());
    }

    /**
     * 计算两个日期年份的差值
     *
     * @param fromDate 起始日期
     * @param toDate   截止日期
     * @return 年份差值
     */
    public static final int calDValueOfYear(Date fromDate, Date toDate) {
        Calendar sCal = Calendar.getInstance();
        Calendar eCal = Calendar.getInstance();
        sCal.setTime(fromDate);
        eCal.setTime(toDate);

        return eCal.get(Calendar.YEAR) - sCal.get(Calendar.YEAR);
    }

    /**
     * 计算两个日期月份的差值
     *
     * @param fromDate 起始日期
     * @param toDate   截止日期
     * @return 月份差值
     */
    public static final int calDValueOfMonth(Date fromDate, Date toDate) {
        Calendar sCal = Calendar.getInstance();
        Calendar eCal = Calendar.getInstance();
        sCal.setTime(fromDate);
        eCal.setTime(toDate);

        return 12 * (eCal.get(Calendar.YEAR) - sCal.get(Calendar.YEAR)) + (eCal.get(Calendar.MONTH) - sCal.get(Calendar.MONTH));
    }

    /**
     * 计算两个日期的差值
     *
     * @param fromDate 起始日期
     * @param toDate   截止日期
     * @return 相差天数
     */
    public static final int calDValueOfDay(Date fromDate, Date toDate) {
        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 根据日期取得所在月的第一天
     *
     * @param date 日期
     * @return Date 月第一天
     */
    public static final Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 根据日期取得所在年的第一天
     *
     * @param date 日期
     * @return Date 月第一天
     */
    public static final Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据日期取得所在周的第一天(周第一天为周一)
     *
     * @param date 日期
     * @return Date 周第一天
     */
    public static final Date getFirstDayOfWeek(Date date) {
        return getFirstDayOfWeek(date, Calendar.MONDAY);
    }

    /**
     * 根据日期取得所在周的最后一天(周第一天为周一)
     *
     * @param date 日期
     * @return Date 周最后一天
     */
    public static final Date getLastDayOfWeek(Date date) {
        return getLastDayOfWeek(date, Calendar.MONDAY);
    }

    /**
     * 根据日期取得所在周的第一天
     *
     * @param date           日期
     * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
     * @return Date 周第一天
     */
    public static final Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 根据日期取得所在周的最后一天
     *
     * @param date           日期
     * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
     * @return Date 周最后一天
     */
    public static final Date getLastDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_YEAR, 7);
        calendar.add(Calendar.MILLISECOND, -1);

        return calendar.getTime();
    }

    /**
     * 根据日期取得所在周的第一天和最后一天(周第一天为周一)
     *
     * @param date 日期
     * @return Date[]
     */
    public static final Date[] getWeek(Date date) {
        return getWeek(date, Calendar.MONDAY);
    }

    /**
     * 根据日期取得所在周的第一天和最后一天(周第一天为周一)
     *
     * @param date           日期
     * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
     * @return Date[]
     */
    public static final Date[] getWeek(Date date, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 7);
        calendar.add(Calendar.MILLISECOND, -1);
        Date lastDate = calendar.getTime();

        return new Date[]{firstDate, lastDate};
    }

    /**
     * 将java.util.Date对象转换成java.sql.Date对象
     *
     * @param date java.util.Date
     * @return java.sql.Date
     */
    public static java.sql.Date toSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取当前时间(java.util.Date)
     *
     * @return java.util.Date
     */
    public static java.sql.Date getSQLDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * @param date
     * @return
     * @Description: 查询日期条件的开始字段，yyyy-MM-dd 00:00:00
     * @Author:hxl
     * @date 2015年9月14日
     */
    public static Date getStartDate(Date date) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * @param date
     * @return
     * @Description: 查询日期条件的结束字段，yyyy-MM-dd 23:59:59
     * @Author:hxl
     * @date 2015年9月14日
     */
    public static Date getEndDate(Date date) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 加减Date各字段的值
     *
     * @param date      Date
     * @param field     字段,如Calendar.DAY_OF_YEAR,Calendar.MINUTE等
     * @param increment 增量,可为负值
     * @return Date
     */
    public static Date add(Date date, int field, int increment) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(field, increment);
        return cal.getTime();
    }

    /**
     * 设置Date各字段的值
     *
     * @param date      Date
     * @param field     字段,如Calendar.DAY_OF_YEAR,Calendar.MINUTE等
     * @param increment 增量,可为负值
     * @return Date
     */
    public static Date set(Date date, int field, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(field, value);
        return cal.getTime();
    }

    /**
     * 通过年月获得上个月的字符串 strFormat
     *
     * @param startTime string"yyyy-MM"
     * @param strFormat String 转换格式格式
     * @return String "yyyy-(MM-1)"
     */
    public static String getPreMonTime(String startTime, String strFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(startTime));
            cal.add(Calendar.MONTH, -1);
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取前一天日期
     *
     * @param startTime string"yyyy-MM"
     * @param strFormat String 转换格式格式
     * @return String "yyyy-(MM-1)"
     */
    public static String getPreDay(String startTime, String strFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(startTime));
            cal.add(Calendar.DAY_OF_MONTH, -1);
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在日期的前 几周 的周一和周日 的字付日期 （格式yyyy-MM-dd）
     *
     * @param weekTimes 日期的前几周 的周一和周日
     * @param reportDay
     * @return String[周一，周日]
     */
    public static String[] getWeekStartEnd(int weekTimes, String reportDate) {
        String[] startEnd = new String[2];
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reportDate);
        } catch (Exception e) {
            log.error("", e);
        }
        cal.setTime(date);
        int intervalDay = cal.get(Calendar.DAY_OF_WEEK);// 得到日期是一周的第几天
        int startDay = (intervalDay - 1) + (7 * (weekTimes - 1) + 6);// 周日算第一天
        // intervalDay
        // - 1
        cal.add(Calendar.DATE, -startDay);
        startEnd[0] = sdf.format(cal.getTime());
        // System.out.println("weekStart :"+sdf.format(cal.getTime()));
        cal.add(Calendar.DATE, 6);// 把日期往后增加6天 即 周日的日期. 整数往后推,负数往前移动
        startEnd[1] = sdf.format(cal.getTime());
        // System.out.println("weekEnd :"+sdf.format(cal.getTime()));
        return startEnd;
    }

    /**
     * @param day_of_week
     * @param date
     * @return
     * @Description:获取日期所在的星期，并返回星期的日期
     * @Author:hxl
     * @date 2015年9月15日
     */
    public static String getWeekDate(int day_of_week, Date date, String format) {
        if (date == null)
            return null;

        Calendar cal = Calendar.getInstance();

        if (format == null || "".equals(format)) {
            format = "yyyyMMdd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, day_of_week);

        return sdf.format(cal.getTime());

    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date) {
        //String[] weekDaysName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        int[] weekDaysCode = {0, 1, 2, 3, 4, 5, 6};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return weekDaysCode[intWeek];
    }

    /**
     * 如strDate日期大于当前日期则返回当前字符日期，否则返回 strDate
     *
     * @param strDate 字符日期
     * @param format  日期格式
     * @return 字符日期
     */
    public static String getEndDate(String strDate, String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(strDate);
            cal.setTime(date);
            Calendar calNow = Calendar.getInstance();
            calNow.setTime(new Date());
            if (cal.getTimeInMillis() > calNow.getTimeInMillis()) {
                return sdf.format(new Date());
            } else {
                return strDate;
            }
        } catch (Exception e) {
            log.error("", e);
            return null;
        }

    }

    public static int getYear(Date start) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date start) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 日期
     *
     * @param start
     * @return
     */
    public static int getDay(Date start) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期所在月的总天数
     *
     * @param start
     * @return
     */
    public static int getMonDays(Date start) {
        Calendar time = Calendar.getInstance();
        time.setTime(start);
        int year = time.get(Calendar.YEAR);// 当前年份
        int month = time.get(Calendar.MONTH); // 当前月份的上一月(Calendar对象默认一月为0)
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month); // Calendar对象默认一月为0
        int dayCount = time.getActualMaximum(Calendar.DAY_OF_MONTH); // 本月份的天数
        return dayCount;
    }

    /**
     * 在日期 start上 增加 天 days ，返回增加天之后的日期
     *
     * @param start
     * @param days
     * @return
     */
    public static Date getDateAddDays(Date start, int days) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(start);
        ca.add(Calendar.DATE, days);
        return ca.getTime();
    }

    /**
     * 在日期 start上 增加 天months ，返回增加月之后的日期
     *
     * @param start
     * @param months
     * @return
     */
    public static Date getDateAddMonths(Date start, int months) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(start);
        ca.add(Calendar.MONTH, months);
        return ca.getTime();
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前时间，格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTimeStr() {
        return formatTimestamp(getCurrentTimeStamp());
    }

    /**
     * 获取当前日期，格式yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return formatDate(getCurrentTimeStamp());
    }

    /**
     * 获取当前日期，格式yyyy-MM-dd
     *
     * @return
     */
    public static String getTomorrowDateStr() {
        return formatDate(new Timestamp(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
    }

    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getOracleIWWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        Date firstDayOfYear = getFirstDayOfYear(date);
        if (!getFirstDayOfWeek(firstDayOfYear).equals(firstDayOfYear)) {
            Date preWeekDay = getDateAddDays(date, -7);
            cal.setTime(preWeekDay);
        } else {
            cal.setTime(date);
        }
        return cal.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * 返回当月最后一天的日期 hqg
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 将日期转换为日历
     *
     * @param date 日期 hqg
     * @return 日历
     */
    private static Calendar convert(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取当前时间的后一天
     */
    public static Date getNextDay() {
        Calendar calendar = Calendar.getInstance();
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 判断是否为月头
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(calendar.DAY_OF_MONTH) == 1);
    }

    /**
     * 获取下一个月的1号
     */
    public static Date nextMonthFirstDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }


    /**
     * 计算两个时间的分钟差
     */
    public static int calMinutes(Timestamp oneTime, Timestamp otherTime) {
        long from = oneTime.getTime();
        long to = otherTime.getTime();
        int hours = (int) ((to - from) / (1000 * 60));
        return hours;
    }

    /**
     * 获取前后小时的时间
     *
     * @return
     */
    public static Date hourToNowDate(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + num);
        SimpleDateFormat df = new SimpleDateFormat(PATTERN_TIMESTAMP);
        Date date = DateUtil.parseTimestamp(df.format(calendar.getTime()));
        return date;
    }

    // public static void main(String[] args) {

    //     Date date1 = DateUtil.getStartDate(new Date());
    //     Date date2 = parseTimestamp(new SimpleDateFormat(PATTERN_TIMESTAMP).format(date1));
    //     DateUtil.formatTimestamp(date1);
    //     System.out.println(date2);
    // }

    /**
     * 获取明天的日期
     *
     * @param date
     * @return
     */
    public static Date getPreDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }


    /**
     * 判断是否是weekend
     *
     * @param sdate
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(String sdate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(sdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 判断是否节假日
     *
     * @param date     要判断的日期字符串,格式"yyyy-MM-dd"
     * @param holidays 节假日集合，一般存放于数据库中
     * @return
     */
    public static boolean isHoliday(String date, List<String> holidays) {
        if (holidays == null || holidays.size() < 1) return false;
        for (String day : holidays) {
            if (day != null && day.equals(date)) return true;
        }
        return false;
    }
}
