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

        /**
         * 获取指定用户
         * @param ropes RepositoryModel
         * */
        void requestRepositories(List<Repository> ropes);
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
        /**
         * 获取仓库列表
         * @param page page
         * @param type type
         * @param sort sort
         * @param direction direction
         * */
        void requestRepositories(int page,String type,String sort,String direction);

        /**
         * 获取仓库列表
         * @param user user
         * @param page page
         * @param type type
         * @param sort sort
         * @param direction direction
         * */
        void requestUserRepositories(String user,int page,String type,String sort,String direction);
    }



}
