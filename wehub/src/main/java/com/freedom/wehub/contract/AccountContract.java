package com.freedom.wehub.contract;

import com.freedom.wecore.common.IWeContract;
import com.freedom.wecore.common.User;

/**
 * @author vurtne on 1-May-18.
 */

public interface AccountContract {

    interface IAccountLoginView extends IWeContract.View{
        void onLogin(User user);
        void onFailed();

    }

    interface IAccountPresenter{
        void requestToken(String userName,String password);
        void requestPersonInfo();
    }
}
