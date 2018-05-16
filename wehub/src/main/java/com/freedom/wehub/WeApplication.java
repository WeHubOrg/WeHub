package com.freedom.wehub;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.freedom.wecore.model.AccountManager;

/**
 *  @author vurtne on 1-May-18.
 */

public class WeApplication  extends Application {

    private static final String TAG = "AActivity";

    @Override
    public void onCreate() {
        super.onCreate();

        AccountManager.generate(this);
        registerActivityLifecycleCallbacks(mCallBack);
    }

    private ActivityLifecycleCallbacks mCallBack = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Log.d(TAG,activity.getClass().getSimpleName()+"--->"+"onCreated");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.d(TAG,activity.getClass().getSimpleName()+"--->"+"onStarted");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.d(TAG,activity.getClass().getSimpleName()+"--->"+"onResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.d(TAG,activity.getClass().getSimpleName()+"--->"+"onPaused");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.d(TAG,activity.getClass().getSimpleName()+"--->"+"onStopped");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            Log.d(TAG,activity.getClass().getSimpleName()+"--->"+"onSaveInstanceState");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.d(TAG,activity.getClass().getSimpleName()+"--->"+"onDestroyed");
        }
    };

}
