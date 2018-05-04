package com.freedom.wecore.widget.node;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;

import java.util.List;

/**
 /**
 * @author vurtne on 3-May-18.
 */
public class NodePresenter {

    private Context context;
    private static long sFlowBytes;
    private static NodePresenter sInstance;
    private long sUid;


    @SuppressLint("WrongConstant")
    private NodePresenter(Context context){
        String packageName = null;
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                packageName = info.processName;//返回包名
        }
        if (packageName == null){
            return;
        }
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, PackageManager.GET_ACTIVITIES);
            String app = Integer.toString(ai.uid, 10);
            sUid = Long.parseLong(app);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static NodePresenter instance(Context context){
        if (sInstance == null){
            synchronized (NodePresenter.class){
                if (sInstance == null){
                    sInstance = new NodePresenter(context);
                }
            }
        }
        return sInstance;
    }

    private long getRxBytes(){
        long curBytes = TrafficStats.getUidRxBytes((int)sUid);
        long dButes = curBytes - sFlowBytes;
        sFlowBytes = curBytes;
        return dButes;
    }

}
