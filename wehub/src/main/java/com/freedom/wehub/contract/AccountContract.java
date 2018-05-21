package com.freedom.wehub.contract;

import com.freedom.wecore.common.IWeContract;
import com.freedom.wecore.bean.User;

/**
 * @author vurtne on 1-May-18.
 */

public interface AccountContract {

    interface IAccountLoginView extends IWeContract.View{
        /**
         * 登录
         * @param user user
         * */
        void onLogin(User user);
        /**
         * 失败
         * */
        void onFailed();

    }

    interface IProfilerView extends IWeContract.View{

    }

    interface IUserView extends IWeContract.View{

    }

    interface IAccountPresenter{
        /**
         * 获取token
         * @param userName userName
         * @param password password
         * */
        void requestToken(String userName,String password);
        /**
         * 获取用户
         * */
        void requestPersonInfo();
    }



}
