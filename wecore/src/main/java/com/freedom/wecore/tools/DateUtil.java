package com.freedom.wecore.tools;


/**
 * 时间的工具类
 *
 * @author vurtne on 15-May-18.
 */

import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    final public static String FORMAT_STRING_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    final public static String FORM_STRING_YEAR = "yyyy";

    final public static String FORMAT_STRING_DATE_YEAR_MONTH = "yyyy-MM";

    final public static String FORMAT_STRING_DATE_DAY = "dd";

    final public static String FORMAT_STRING_DATE_ALL_TIME = "HH:mm:ss";

    final public static String FORMAT_STRING_DATE = "yyyy-MM-dd";

    final public static String FORMAT_STRING_DATE_CHINESE = "yyyy年MM月dd日";

    final public static String FORMAT_STRING_DATE_TIME_POINT = "yyyy.MM.dd HH:mm";

    final public static String FORMAT_STRING_DATE_MONTH_DAY = "MM月dd日";

    final public static String FORMAT_STRING_DATE_MONTH = "MM月";

    public final static String MY_FORMAT = "yyyy-MM-dd HH:mm";


    public static String getLongFromStringWithTZ(String date){
        String dateStr = date.replace("T"," ");
        dateStr = dateStr.replace("Z","");
        return DateUtil.timestamp2Humans(DateUtil.stringToDate(dateStr,"yyyy-MM-dd HH:mm:ss") == null?
                new Date(System.currentTimeMillis()).getTime():
                DateUtil.stringToDate(dateStr,"yyyy-MM-dd HH:mm:ss").getTime());
    }


    public static Date stringToDate(String dateString, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date stringToDate(String date) {
        return stringToDate(date, FORMAT_STRING_DATE_TIME);
    }

    public static String dateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, FORMAT_STRING_DATE_TIME);
    }


    public static String dateToString(String date, String format) {
        return dateToString(new Date(StringUtil.secureLong(date) * 1000), format);
    }

    public static String dateToString(long date, String format) {
        return dateToString(new Date(date * 1000), format);
    }


    public static String calendarToString(Calendar calendar) {
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
    }

    public static Calendar stringToCalendar(String dateString) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.stringToDate(dateString));
        return calendar;
    }

    public static String toString(int year, int monthOfYear, int dayOfMonth) {
        String date = year + "-";
        if (monthOfYear < 9) {
            date += "0";
        }
        date += (monthOfYear + 1) + "-";
        if (dayOfMonth < 10) {
            date += "0";
        }
        return date + dayOfMonth;
    }

    public static String todayString() {
        return calendarToString(Calendar.getInstance());
    }

    /**
     * 按指定的格式返回当前表示当前时间的字符串
     *
     * @param format
     * @return
     */
    public static String nowString(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String nowString() {
        return nowString(FORMAT_STRING_DATE_TIME);
    }

    /**
     * 判断第一个日期是否晚于第二个日期
     *
     * @param firstDate
     * @param secondDate
     * @return 晚于返回true，早于或等于返回false
     */
    public static boolean isLaterThan(String firstDate, String secondDate) {
        return stringToCalendar(firstDate).getTimeInMillis() > stringToCalendar(secondDate).getTimeInMillis();
    }

    /**
     * 获取时间戳（不带时区信息）
     *
     * @return
     */
    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取时间戳（不带时区信息）
     *
     * @return
     */
    public static long getTimeStamp(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);
        return calendar.getTimeInMillis();
    }

    /**
     * 将时间戳转化为可读的字符串
     *
     * @param stamp
     * @return
     */
    public static String stampToString(long stamp) {
        return dateToString(new Date(stamp));
    }

    /**
     * 将时间戳按照指定格式转化为可读的字符串
     *
     * @param stamp
     * @param format
     * @return
     */
    public static String stampToString(long stamp, String format) {
        return dateToString(new Date(stamp), format);
    }

    /**
     * 返回当前是星期几。
     *
     * @return 返回一个代表当期日期是星期几。
     */
    public static String getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        String day = "";
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                day = "星期一";
                break;
            case Calendar.TUESDAY:
                day = "星期二";
                break;
            case Calendar.WEDNESDAY:
                day = "星期三";
                break;
            case Calendar.THURSDAY:
                day = "星期四";
                break;
            case Calendar.FRIDAY:
                day = "星期五";
                break;
            case Calendar.SATURDAY:
                day = "星期六";
                break;
            case Calendar.SUNDAY:
                day = "星期日";
                break;

            default:
                break;
        }
        return day;
    }

    /**
     * 返回当前是星期几。
     *
     * @return 返回一个代表当期日期是星期几。
     */
    public static String getDayOfWeekForPickerDialog(int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);
        String day = "";
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                day = "周一";
                break;
            case Calendar.TUESDAY:
                day = "周二";
                break;
            case Calendar.WEDNESDAY:
                day = "周三";
                break;
            case Calendar.THURSDAY:
                day = "周四";
                break;
            case Calendar.FRIDAY:
                day = "周五";
                break;
            case Calendar.SATURDAY:
                day = "周六";
                break;
            case Calendar.SUNDAY:
                day = "周日";
                break;

            default:
                break;
        }
        return day;
    }

    /**
     * 返回友好的时间格式,如果是今天，则返回今天，如果是昨天，就返回昨天，如果是昨天以前的，就返回yyyy-mm-dd
     *
     * @param time
     * @return
     */
    public static String friendTime(Date time) {
        return friendTimeBase(time, FORMAT_STRING_DATE);
    }

    /**
     * 返回友好的时间格式,如果是今天，则返回今天，如果是昨天，就返回昨天，如果是昨天以前的，就返回dd
     *
     * @param time
     * @return
     */
    public static String friendTimeForDay(Date time) {
        return friendTimeBase(time, FORMAT_STRING_DATE_DAY);
    }

    public static String friendTimeBase(Date time, String type) {
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        target.setTime(time);
        int day_sub = Math.abs(now.get(Calendar.DAY_OF_MONTH) - target.get(Calendar.DAY_OF_MONTH));

        if (now.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {// 如果年份一样
            if (now.get(Calendar.MONTH) == target.get(Calendar.MONTH)) {// 如果月份一样
                if (day_sub == 0) {// 如果是同一天
                    // 返回今天
                    return "今天";
                } else if (day_sub == 1) { //
                    // 返回昨天
                    return "昨天";
                }
            }
        }
        return dateToString(time, type.equals(FORMAT_STRING_DATE) ? FORMAT_STRING_DATE : FORMAT_STRING_DATE_DAY);
    }

    public static String chatFriendTime(Date time) {
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        if (null == time) {
            time = new Date();
        }
        target.setTime(time);
        int day_sub = Math.abs(now.get(Calendar.DAY_OF_MONTH) - target.get(Calendar.DAY_OF_MONTH));

        if (now.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {// 如果年份一样
            if (now.get(Calendar.MONTH) == target.get(Calendar.MONTH)) {// 如果月份一样
                if (day_sub == 0) {// 如果是同一天
                    // 返回今天
                    return "今天 " + dateToString(time, "HH:mm");
                } else if (day_sub == 1) { //
                    // 返回昨天
                    return "昨天 " + dateToString(time, "HH:mm");
                }
            }
        }
        return dateToString(time, DateUtil.FORMAT_STRING_DATE_TIME_POINT);
    }

    /****
     * 取得聊天的时间字符串：不是今天或昨天的时间，按formatStr格式显示
     *
     * @param time
     * @param formatStr
     * @return
     */
    public static String chatFriendTime(Date time, String formatStr) {
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        if (null == time) {
            time = new Date();
        }
        target.setTime(time);
        int day_sub = Math.abs(now.get(Calendar.DAY_OF_MONTH) - target.get(Calendar.DAY_OF_MONTH));

        if (now.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {// 如果年份一样
            if (now.get(Calendar.MONTH) == target.get(Calendar.MONTH)) {// 如果月份一样
                if (day_sub == 0) {// 如果是同一天
                    // 返回今天
                    return "今天 " + dateToString(time, "HH:mm");
                } else if (day_sub == 1) { //
                    // 返回昨天
                    return "昨天 " + dateToString(time, "HH:mm");
                }
            }
        }
        return dateToString(time, formatStr);
    }

    /**
     * 如果看得消息是今年的则显示8月20日,如果看的消息是去年的则显示2013年8月20日
     *
     * @param time
     * @return
     */
    public static String friendTimeMMDD(Date time) {
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        target.setTime(time);
        if (now.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {
            return dateToString(time, FORMAT_STRING_DATE_MONTH_DAY);
        } else {
            return dateToString(time, FORMAT_STRING_DATE_CHINESE);
        }
    }

    /**
     * 以友好的方式显示时间
     *
     * @param time
     * @return
     */
    public static String friendly_time(Date time) {
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateToString(cal.getTime(), FORMAT_STRING_DATE);
        String paramDate = dateToString(time, FORMAT_STRING_DATE);
        if (curDate.equals(paramDate)) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = Math.max(t / 1000, 1) + "秒前";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = Math.max(t / 1000, 1) + "秒前";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2) {
            ftime = dateToString(time, FORMAT_STRING_DATE);
        }
        return ftime;
    }


    /**
     * 一分钟以内，显示为“刚刚”
     * -一小时以内，显示为“x分钟前”
     * -当天时间，显示为“x小时前”
     * -当天零点往前24小时内，显示为“昨天 ”
     * -当天零点往前24-48小时间，显示为“前天 ”
     * -当天零点往前72小时间外，显示为“x天前”（3天前、30天前）
     * -当天零点往前30天外，显示为“年/月/日”（2016/9/20）
     *
     * @param times
     * @return
     */
    public static String friendlyTime(int times) {
        return friendlyTime(Long.parseLong(String.valueOf(times)) * 1000);
    }

    public static String friendlyTimeNew(int times) {
        return friendlyTimeNew(Long.parseLong(String.valueOf(times)) * 1000);
    }

    /**
     * 以友好的方式显示时间
     *
     * @param times
     * @return
     */
    public static String friendlyTime(long times) {
        Date time = new Date(times);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateToString(cal.getTime(), FORMAT_STRING_DATE);
        String paramDate = dateToString(time, FORMAT_STRING_DATE);
        if (curDate.equals(paramDate)) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = "刚刚";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = "刚刚";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days < 30) {
            ftime = days + "天前";
        } else {
            String format = "yyyy/MM/dd";
            SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
            ftime = sDateFormat.format(time);
        }
        return ftime;
    }

    /**
     * 有一套时间
     *
     * @param times
     * @return
     */
    public static String friendlyTimeNew(long times) {
        Date time = new Date(times);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = "刚刚";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            String format = "HH点mm分";
            SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
            ftime = sDateFormat.format(time);
        } else if (days > 2) {
            String format = "MM月dd日HH点";
            SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
            ftime = sDateFormat.format(time);
        }
        return ftime;
    }

    public static String friendlyTimeHaveYesterdayNew(long times) {
        Date time = new Date(times);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = "刚刚";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            String format = "昨天HH点mm分";
            SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
            ftime = sDateFormat.format(time);
        } else if (days > 2) {
            String format = "MM月dd日HH点";
            SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
            ftime = sDateFormat.format(time);
        }
        return ftime;
    }

    /**
     * 时间逻辑：
     * -1小时内：剩x分钟，如剩12分钟
     * -1天内：剩x小时，如剩18小时
     * -1月内：剩x天，如剩3天
     * -＞1月：剩x月
     */
    public static String remainFriendlyTime(long times) {
        DateUtil.TimeDuring timeDuring = DateUtil.getTimeDuring(times);
        long days = timeDuring.days();
        int hours = timeDuring.hours();
        int minutes = timeDuring.minutes();
        String ftime = "";
        if (days == 0) {
            if (hours == 0) {
                ftime = "剩" + Math.max(minutes, 1) + "分钟";
            } else {
                ftime = "剩" + hours + "小时";
            }
        } else if (days < 30) {
            ftime = "剩" + days + "天";
        } else if (days > 30) {
            ftime = "剩" + days / 30 + "月";
        }
        return ftime;
    }

    /**
     * 返回友好的时间格式,如果是本月，就返回本月，否则返回yyyy-MM格式
     *
     * @param time 时间
     * @param type 格式
     * @return
     */
    public static String friendlyMonth(Date time, String type) {
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        if (null == time) {
            time = new Date();
        }
        target.setTime(time);
        if (now.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {// 如果年份一样
            if (now.get(Calendar.MONTH) == target.get(Calendar.MONTH)) {// 如果月份一样
                return "本月";
            }
        }
        return dateToString(time, type == null ? FORMAT_STRING_DATE_MONTH : type);
    }

    public static boolean isSameMonth(Date firstTime, Date secondTime) {
        Calendar first = Calendar.getInstance();
        Calendar second = Calendar.getInstance();
        if (null == firstTime) {
            firstTime = new Date();
        }
        if (null == secondTime) {
            secondTime = new Date();
        }
        first.setTime(firstTime);
        second.setTime(secondTime);
        if (first.get(Calendar.YEAR) == second.get(Calendar.YEAR)) {// 如果年份一样
            if (first.get(Calendar.MONTH) == second.get(Calendar.MONTH)) {// 如果月份一样
                return true;
            }
        }
        return false;
    }

    /**
     * 日期时间转换
     *
     * @param time    时间毫秒
     * @param pattern 转换格式：如 yyyy-MM-dd hh:mm
     * @return
     */
    public static String paserTimeToYMD(String time, String pattern) {
        return paserTimeToYMD(Long.valueOf(time), pattern);
    }

    /**
     * 日期时间转换
     *
     * @param time    时间毫秒
     * @param pattern 转换格式：如 yyyy-MM-dd hh:mm
     * @return
     */
    public static String paserTimeToYMD(long time, String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(time));
    }

    /**
     * 日期时间转换
     *
     * @param time 时间毫秒
     *             转换格式 FORMAT_1、FORMAT_2
     * @return
     */
    public static String paserTimeToYMD(long time) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(MY_FORMAT);
        return sDateFormat.format(new Date(time));
    }

    /**
     * 日期时间转换成yyyy-MM-dd
     *
     * @param time 时间毫秒
     * @return
     */
    public static String paserTime(long time) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(FORMAT_STRING_DATE);
        return sDateFormat.format(new Date(time));
    }

    /****
     * 得到日期的月
     *
     * @param time ms
     * @return
     */
    public static String getMonthStr(long time) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int month = c.get(Calendar.MONTH) + 1;
        switch (month) {
            case 1:
                return "一月";
            case 2:
                return "二月";
            case 3:
                return "三月";
            case 4:
                return "四月";
            case 5:
                return "五月";
            case 6:
                return "六月";
            case 7:
                return "七月";
            case 8:
                return "八月";
            case 9:
                return "九月";
            case 10:
                return "十月";
            case 11:
                return "十一月";
            case 12:
                return "十二月";
        }
        return "";
    }


    /**
     * @param timestamp 要转换的毫秒数
     * @return
     * @see DateUtil.TimeDuring
     */
    public static DateUtil.TimeDuring getTimeDuring(long timestamp) {
        return new DateUtil.TimeDuring(timestamp);
    }

    /**
     * 转换时间戳为中文显示
     * <p/>
     * 今天显示为“今天”；昨天显示为“昨天”；再往前则按日/月份显示
     *
     * @param timestamp
     * @param isMinify  是否精减显示.昨天、前天和更早时间的具体时分隐藏。年份不显示
     * @return
     */
    public static String timestamp2Chinese(int timestamp, boolean isMinify) {
        return timestamp2Chinese(Long.parseLong(String.valueOf(timestamp)) * 1000, isMinify);
    }

    public static String timestamp2Chinese(long timeOri, boolean isMinify) {
        String rs = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long intervalTime = calendar.getTimeInMillis() - timeOri;
        long currentIntervalTime = Calendar.getInstance().getTimeInMillis() - timeOri;
        if (currentIntervalTime > 0) {
            SimpleDateFormat sDateFormat = null;
            if (intervalTime < 24 * 60 * 60 * 1000) {//今天
                if (isMinify) {
                    rs = "今天";
                } else {
                    sDateFormat = new SimpleDateFormat("HH:mm");
                    rs = "今天 " + sDateFormat.format(new Date(timeOri));
                }
            } else if (intervalTime < 2 * 24 * 60 * 60 * 1000) {// 昨天
                if (isMinify) {
                    rs = "昨天";
                } else {
                    sDateFormat = new SimpleDateFormat("HH:mm");
                    rs = "昨天 " + sDateFormat.format(new Date(timeOri));
                }
            } else {
                String format = "";
                if (isMinify) {
                    format = "MM月dd日";
                } else {
                    format = "yy/MM/dd MM月dd日";
                }
                sDateFormat = new SimpleDateFormat(format);
                rs = sDateFormat.format(new Date(timeOri));
            }
        } else {
            rs = "火星时间";
        }

        return rs;
    }

    /**
     * 转换时间戳的显示
     * <p/>
     * -- 1分钟内，显示为  “刚刚 ”
     * -- 当天时间，显示为“ 时：分”（13:08）
     * -- 当天零点往前24小时内，显示为“昨天 时：分”（昨天 13:08）
     * -- 当天零点往前24-48小时间，显示为“前天 时：分”（前天 13:08）
     * -- 当天零点往前48小时间外，显示为“年/月/日 时：分”（16/9/20 13:08）
     * --
     *
     * @param timestamp 时间戳.非毫秒
     * @param isMinify  是否精减显示.昨天、前天和更早时间的具体时分隐藏
     * @return
     */
    public static String timestamp2Human(int timestamp, boolean isMinify) {
        return timestamp2Human(Long.parseLong(String.valueOf(timestamp)) * 1000, isMinify);
    }

    public static String timestamp2Humans(int timestamp) {
        return timestamp2Humans(Long.parseLong(String.valueOf(timestamp)) * 1000);
    }


    public static String timestamp2Human(long timeOri, boolean isMinify) {
        String rs = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long intervalTime = calendar.getTimeInMillis() - timeOri;  //要以当天的24点为零点
        long currentIntervalTime = calendar.getTimeInMillis() - timeOri;  //当前时间戳,判断是否在一分钟内专用
        if (currentIntervalTime > 0) {
            SimpleDateFormat sDateFormat = null;
            if (currentIntervalTime < 60 * 1000) {//1分钟内
                rs = "刚刚";
            } else if (intervalTime < 24 * 60 * 60 * 1000) {//今天
                sDateFormat = new SimpleDateFormat("HH:mm");
                rs = sDateFormat.format(new Date(timeOri));
            } else if (intervalTime < 2 * 24 * 60 * 60 * 1000) {// 昨天
                if (isMinify) {
                    rs = "昨天";
                } else {
                    sDateFormat = new SimpleDateFormat("HH:mm");
                    rs = "昨天 " + sDateFormat.format(new Date(timeOri));
                }
            } else if (intervalTime < 3 * 24 * 60 * 60 * 1000) {// 前天
                if (isMinify) {
                    rs = "前天";
                } else {
                    sDateFormat = new SimpleDateFormat("HH:mm");
                    rs = "前天 " + sDateFormat.format(new Date(timeOri));
                }
            } else {
                String format = "";
                if (isMinify) {
                    format = "yy/MM/dd";
                } else {
                    format = "yy/MM/dd HH:mm";
                }
                sDateFormat = new SimpleDateFormat(format);
                rs = sDateFormat.format(new Date(timeOri));
            }
        } else {
            rs = "火星时间";
        }

        return rs;
    }

    /**
     * -一分钟以内，显示为“刚刚”
     * -一小时以内，显示为“x分钟前”
     * -当天时间，显示为“x小时前”
     * -当天零点往前24小时内，显示为“昨天 ”
     * -当天零点往前24-48小时间，显示为“前天 ”
     * -当天零点往前72小时间外，显示为“x天前”（3天前、29天前）
     *
     * @return
     */
    public static String timestamp2Humans(long times) {
        Date time = new Date(times);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateToString(cal.getTime(), FORMAT_STRING_DATE);
        String paramDate = dateToString(time, FORMAT_STRING_DATE);
        if (curDate.equals(paramDate)) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = "just";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + " minutes ago";
            } else {
                ftime = hour + " hours ago";
            }
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days / 30 / 12 > 0) {
            ftime = (days / 30 / 12) + " years ago";
        }else if (days / 30 > 0){
            ftime = (days / 30) + " months ago";
        }else if (days == 0) {
            long t = cal.getTimeInMillis() - time.getTime();
            int min = (int) (t / 60000);
            int hour = (int) (t / 3600000);
            if (min == 0) {
                ftime = "just";
            } else if (hour == 0) {
                ftime = Math.max(min, 1) + " minutes ago";
            } else {
                ftime = hour + " hours ago";
            }
        } else if (days == 1) {
            ftime = "yesterday";
        }else if (days > 1) {
            ftime = days + " days ago";
        } else {
            ftime = "Unknown";
        }
        return ftime;
    }


    /**
     * 当前时间阶段
     * 早上,中午,下午,晚上
     *
     * @return
     */
    public static String nowHour2Chinese() {
        String str = "";
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 6) {
            str = "凌晨";
        } else if (hour >= 6 && hour < 8) {
            str = "早上";
        } else if (hour >= 8 && hour < 11) {
            str = "上午";
        } else if (hour >= 11 && hour < 14) {
            str = "中午";
        } else if (hour >= 14 && hour < 19) {
            str = "下午";
        } else if (hour >= 19 && hour <= 23) {
            str = "晚上";
        }

        return str;
    }

    /**
     * 时间戳在当前时间24小时内
     *
     * @param timestamp
     * @return
     */
    public static boolean isTimestampIn24Hours(int timestamp) {
        int currentIntervalTime = (int) Calendar.getInstance().getTimeInMillis() / 1000;
        if (timestamp < currentIntervalTime + 86400) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 得到系统当前日期的前或者后几天
     *
     * @param iDate 如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @return Date 返回系统当前日期的前或者后几天
     * @see Calendar#add(int, int)
     */
    public static Date getDateBeforeOrAfter(int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后几天
     *
     * @param iDate 如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几天
     * @see Calendar#add(int, int)
     */
    public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 判断两个时间戳相差多少秒
     *
     * @param d
     * @return
     */
    public static int dateDiffer(long d) {
        try {
            long l1 = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10));
            long interval = l1 - d;
            return (int) Math.abs(interval);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 判断是否是2017春节时间
     *
     * @return
     */
    public static boolean dateIsIn() {
        boolean flag = false;
        String s = nowString("yyyy-MM-dd");
        if (s != null && s.length() == 10) {
            Calendar date1 = Calendar.getInstance();
            date1.set(Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s.substring(5, 7)), Integer.parseInt(s.substring(8, s.length())));
            Calendar from = Calendar.getInstance();
            from.set(2017, 1, 15);
            Calendar to = Calendar.getInstance();
            to.set(2017, 2, 18);
            if (date1.after(from) && date1.before(to)) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算剩余多少时间
     */
    public static String surplusTime(String s, String format) {
        DateUtil.TimeDuring timeDuring = new DateUtil.TimeDuring(StringUtil.secureLong(s) * 1000);
        if (format.contains("dd")) {
            format = format.replace("dd", String.valueOf(timeDuring.days()));
        }
        if (format.contains("HH")) {
            format = format.replace("HH", String.valueOf(timeDuring.hours()));
        }
        if (format.contains("mm")) {
            format = format.replace("mm", String.valueOf(timeDuring.minutes()));
        }
        if (format.contains("ss")) {
            format = format.replace("ss", String.valueOf(timeDuring.seconds()));
        }
        char[] mTimes = format.toCharArray();
        StringBuilder mBuilder = new StringBuilder();
        for (int i = 0; i < mTimes.length; i++) {
            char time = mTimes[i];
            if (TextUtils.isEmpty(mBuilder.toString())) {
                String currentString = String.valueOf(time);
                if (TextUtils.isDigitsOnly(currentString) && !"0".equals(currentString)) {
                    mBuilder.append(time);
                }
            } else {
                mBuilder.append(time);
            }
        }
        return mBuilder.toString();
    }

    /**
     * 计算剩余多少时间
     */
    public static String surplusTime(String s) {
        return surplusTime(s, "dd天HH小时mm分ss秒");
    }

    /**
     * 将毫秒转为天、小时、分钟、秒,并可进行加减计算可以用于计时和倒数
     *
     * @author 李欣
     * @see DateUtil#getTimeDuring(long)
     */
    public static class TimeDuring {

        /**
         * 时间戳
         */
        private long mTimestamp;

        private TimeDuring(long timestamp) {
            mTimestamp = timestamp;
        }

        /**
         * 将给定时间加入(可通过正负数修改时间)
         *
         * @param timestamp 毫秒
         */
        public void applyTime(long timestamp) {
            mTimestamp += timestamp;
        }

        /**
         * 返回当前时间戳
         *
         * @return
         */
        public long getTime() {
            return mTimestamp;
        }

        /**
         * 天
         *
         * @return
         * @see #days
         */
        public long days() {
            return mTimestamp / (1000 * 60 * 60 * 24);
        }

        /**
         * 小时
         *
         * @return
         * @see #hours
         */
        public int hours() {
            return (int) (mTimestamp % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        }

        /**
         * 分钟
         *
         * @return
         * @see #minutes
         */
        public int minutes() {
            return (int) (mTimestamp % (1000 * 60 * 60)) / (1000 * 60);
        }

        /**
         * 秒
         *
         * @return
         * @see #seconds
         */
        public int seconds() {
            return (int) (mTimestamp % (1000 * 60)) / 1000;
        }

    }

}
