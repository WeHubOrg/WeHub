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

    private static long sRxFlowBytes;
    private static long sTxFlowBytes;
    private static NodePresenter sInstance;
    private long sUid;


    @SuppressLint("WrongConstant")
    private NodePresenter(Context context){
        String packageName = null;
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)
                packageName = info.processName;
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

    public long getRxBytes(){
        long curBytes = TrafficStats.getUidRxBytes((int)sUid);
        long dRxButes = curBytes - sRxFlowBytes;
        sRxFlowBytes = curBytes;
        curBytes = TrafficStats.getUidTxBytes((int)sUid);
        long dTxButes = curBytes - sTxFlowBytes;
        sTxFlowBytes = curBytes;
        return dRxButes + dTxButes;
    }



}
