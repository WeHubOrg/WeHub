package com.freedom.wecore.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author vurtne on 1-May-18.
 */

public class AccountManager {

    public static final String SHARED_USERINFO = "user_info";

    private Object LOCK = new Object();
    private static String sToken;
    private static AccountManager sManager;
    private static SharedPreferences mSharedPreferences;

    private AccountManager(Context context){
        mSharedPreferences = context.getSharedPreferences(SHARED_USERINFO, Context.MODE_PRIVATE);
    }

    public static void generate(Context context){
        sManager = new AccountManager(context);
    }

    public static AccountManager instance(){
        return sManager;
    }

    public String getToken() {
        return sToken;
    }

    public void setToken(String sToken) {
        AccountManager.sToken = sToken;
    }
}
