package com.freedom.wehub.presenter;

import android.content.Context;

import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.model.AccountManager;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.contract.ProfileContract;
import com.freedom.wehub.model.AuthModel;
import com.freedom.wehub.quest.AuthService;

import okhttp3.Credentials;

/**
 * @author vurtne on 1-May-18.
 */
public class ProfilePresenter extends WePresenter implements ProfileContract.IProfilePresenter{

    private AuthService mService;

    @Override
    protected void init(Context context) {

    }

    @Override
    public void onDestroy() {
        if (mService != null){
        }
    }

    @Override
    public void requestRepositories(int page,String type,String sort,String direction) {
        if (mService == null){
            mService = new AuthService();
        }
        mService.requestRepositories(page,type,sort,direction,innerResponse -> {
//            ((AccountContract.IProfilerView)mView).requestRepositories(innerResponse.get());
        });
    }

    @Override
    public void requestUserRepositories(String user,int page,String type,String sort,String direction) {
        if (mService == null){
            mService = new AuthService();
        }
        mService.requestUserRepositories(user,page,type,sort,direction,innerResponse -> {
            ((ProfileContract.IOverviewView)mView).requestRepositories(innerResponse.get());
        });
    }
}
