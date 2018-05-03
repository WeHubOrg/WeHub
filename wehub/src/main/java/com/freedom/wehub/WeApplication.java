package com.freedom.wehub;

import android.app.Application;

import com.freedom.wecore.model.AccountManager;

/**
 *  @author vurtne on 1-May-18.
 */

public class WeApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AccountManager.generate(this);
    }
}
