package com.freedom.wehub.presenter;

import android.content.Context;
import android.util.Log;

import com.freedom.wecore.model.AccountManager;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wehub.model.AuthModel;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.quest.AuthService;

import okhttp3.Credentials;

/**
 * @author vurtne on 1-May-18.
 */
public class AccountPresenter extends WePresenter implements AccountContract.IAccountPresenter{

    private AuthService mService;

    @Override
    protected void init(Context context) {

    }

    @Override
    public void onDestroy() {
        if (mService != null){
            mService = null;
        }
    }

    @Override
    public void requestToken(String userName, String password) {
        AuthModel.AuthRequest authRequest = new AuthModel.AuthRequest();
        if (mService == null){
            mService = new AuthService();
        }
        mService.requestToken(Credentials.basic(userName, password),authRequest, response -> {
            if (response.get() == null){
                ((AccountContract.IAccountLoginView)mView).onFailed();
                return;
            }
            AccountManager.instance().setToken(response.get());
            requestPersonInfo();
        });
    }

    @Override
    public void requestPersonInfo() {
        if (mService == null){
            mService = new AuthService();
        }
        mService.requestPersonInfo(innerResponse -> {
            AccountManager.instance().setUser(innerResponse.get());
            ((AccountContract.IAccountLoginView)mView).onLogin(innerResponse.get());
        });
    }


    @Override
    public void requestPersonInfo(String user) {
        if (mService == null){
            mService = new AuthService();
        }
        mService.requestPersonInfo(user,innerResponse -> {
            int i = 1;
            ((AccountContract.IProfilerView)mView).requestPerson(innerResponse.get());
        });
    }
}
