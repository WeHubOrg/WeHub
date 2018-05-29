package com.freedom.wehub.contract;

import com.freedom.wecore.bean.Repository;
import com.freedom.wecore.common.IWeContract;

import java.util.List;


/**
 * @author vurtne on 1-May-18.
 */

public interface ProfileContract {


    interface IOverviewView extends IWeContract.View{
        /**
         * 获取指定用户
         * @param ropes RepositoryModel
         * */
        void requestRepositories(List<Repository> ropes);
    }

    interface IProfilePresenter{
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
