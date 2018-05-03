package com.freedom.wecore.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.freedom.wecore.bean.BaseToken;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.net.NetConfig;
import com.freedom.wecore.tools.GsonConvertUtils;

/**
 * @author vurtne on 1-May-18.
 */

public class AccountManager {

    public static final String SHARED_USERINFO = "user_info";

    private Object LOCK = new Object();
    private static BaseToken sToken;
    private static User sUser;
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

    public BaseToken getToken() {
        if (sToken == null){
            String token = mSharedPreferences.getString(NetConfig.BASE_TOKEN,null);
            if (!TextUtils.isEmpty(token)){
                sToken = GsonConvertUtils.getGson().fromJson(token,BaseToken.class);
            }
        }
        return sToken;
    }

    public void setToken(BaseToken sToken) {
        AccountManager.sToken = sToken;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(NetConfig.BASE_TOKEN, GsonConvertUtils.toJson(sToken));
        editor.commit();
    }

    public User getUser() {
        if (sUser == null){
            String user = mSharedPreferences.getString(NetConfig.USER_INFO,null);
            if (!TextUtils.isEmpty(user)){
                sToken = GsonConvertUtils.getGson().fromJson(user,BaseToken.class);
            }
        }
        return sUser;
    }

    public void setUser(User user) {
        AccountManager.sUser = user;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(NetConfig.USER_INFO, GsonConvertUtils.toJson(sUser));
        editor.commit();
    }
}
