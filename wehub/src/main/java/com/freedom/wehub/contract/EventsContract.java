package com.freedom.wehub.contract;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.IWeContract;

import java.util.List;

/**
 * @author vurtne on 5-May-18.
 */
public interface EventsContract {

    interface IEventsView extends IWeContract.View{
        void onResponseEvents(List<Events> events);
    }

    interface IEventsPresenter{
        /**
         * 请求事件
         * @param page 页码
         * @param user 用户
         * */
        void requestEvents(int page,String user);
    }
}
