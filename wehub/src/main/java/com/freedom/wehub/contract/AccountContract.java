package com.freedom.wehub.contract;

import com.freedom.wecore.bean.Repository;
import com.freedom.wecore.common.IWeContract;
import com.freedom.wecore.bean.User;
import com.freedom.wehub.model.RepositoryModel;

import java.util.List;

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
        /**
         * 获取指定用户
         * @param user user
         * */
        void requestPerson(User user);
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

        /**
         * 获取指定用户
         * @param user user
         * */
        void requestPersonInfo(String user);
    }



}
