package com.freedom.wehub.contract;

import com.freedom.wecore.common.IWeContract;


/**
 * @author vurtne on 1-May-18.
 */

public interface ProfileContract {


    interface IOverviewView extends IWeContract.View{

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
