package com.freedom.wecore.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author vurtne on 29-Apr-18.
 *
 */

public class DeviceUtil {


    /**
     * 根据手机的分辨率从 dip 的单位 转成为px
     *
     * @param context 上下文
     * @param view  需要设置字体的view
     * @param path 字体文件的路径
     */
    public static void setTypeface(Context context, TextView view, String path){
        if (context == null || TextUtils.isEmpty(path)){
            return;
        }
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),path);
        view.setTypeface(typeface);
    }
    /**
     * 根据手机的分辨率从 dip 的单位 转成为px
     *
     * @param context 上下文
     * @param dpValue 值
     * @return int
     */
    public static int dip2Px(Context context, float dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 计算颜色 和 透明度
     *
     * @param color 颜色
     * @param alpha 透明度
     */
    public static int calculateColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float al = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * al + 0.5);
        green = (int) (green * al + 0.5);
        blue = (int) (blue * al + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * 获取版本名称
     *
     * @param context 上下文
     * @return 版本名称
     */
    public static String getVersionName(Context context){
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            versionName = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取屏幕宽
     * @param context 上下文
     * @return 版本名称
     */
    public static float getScreenWidth(Context context){
        if (context == null){
            return 0;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        float density = dm.density;
        return width / density;
    }

    /**
     * 获取屏幕高
     * @param context 上下文
     * @return 版本名称
     */
    public static float getScreenHeight(Context context){
        if (context == null){
            return 0;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        float density = dm.density;
        return  height / density;
    }

}
