package com.freedom.wecore.tools;

import android.util.Log;

/**
 * @author vurtne on 29-Apr-18.
 * 日志工具
 */

public class LogUtil {
    private static final String TAG = "LogUtil";
    /**
     * 日志开关
     */
    private static boolean LOG_ENABLED = true;

    /**
     * 日志级别
     */
    private static int LOG_DEGREE = Log.VERBOSE;

    private static int LOG_FILE_DEGREE = Log.WARN;

    /**
     * 打开或关闭日志
     * @param flag
     */
    public static void enable(boolean flag){
        LOG_ENABLED = flag;
    }

    public static void v(String tag, String msg) {
        int logDegree = Log.VERBOSE;
        if (LOG_ENABLED && LOG_DEGREE <= logDegree) {
            Log.v(tag, msg);
        }
    }


    public static void d(String tag, String msg) {
        if (LOG_ENABLED && LOG_DEGREE <= Log.DEBUG) {
            int p = 2048;
            long length = msg.length();
            if (length < p || length == p)
                Log.d(tag, msg);
            else {
                while (msg.length() > p) {
                    String logContent = msg.substring(0, p);
                    msg = msg.replace(logContent, "");
                    Log.d(tag, logContent);
                }
                Log.d(tag, msg);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_ENABLED && LOG_DEGREE <= Log.INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (msg != null){
            if (LOG_ENABLED && LOG_DEGREE <= Log.WARN) {
                Log.w(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg, Exception e) {
        if (LOG_ENABLED && LOG_DEGREE <= Log.WARN) {
            Log.w(tag, msg, e);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_ENABLED && LOG_DEGREE <= Log.ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, Exception e) {
        if (LOG_ENABLED && LOG_DEGREE <= Log.ERROR) {
            Log.e(tag, Log.getStackTraceString(e));
        }
    }

    public static void e(String tag, Error e) {
        if (LOG_ENABLED && LOG_DEGREE <= Log.ERROR) {
            Log.e(tag, Log.getStackTraceString(e));
        }
    }

    public static void e(String tag, Throwable tr, String msg) {
        if (LOG_ENABLED && LOG_DEGREE <= Log.ERROR) {
            Log.e(tag, msg, tr);
        }
    }

}
