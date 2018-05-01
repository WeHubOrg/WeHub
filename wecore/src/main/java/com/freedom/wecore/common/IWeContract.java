package com.freedom.wecore.common;


/**
 * @author vurtne on 1-May-18.
 */

public class IWeContract {

    public interface View {

    }

    interface Presenter<V extends IWeContract.View>{
        /**
         * 关联
         * @param mvpView
         */
        void attachView(V mvpView);

        /**
         * 取消关联
         */
        void detachView();
    }
}
