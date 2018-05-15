package com.freedom.wehub.quest;


import com.freedom.wecore.bean.Events;
import com.freedom.wecore.net.OnResponseListener;
import com.freedom.wecore.net.RetrofitClient;
import com.freedom.wehub.model.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 5-May-18.
 */
public class EventsService extends RetrofitClient {

    /**
     * 获取news events
     * */
    public void requestNewsEvents(int page,String user,OnResponseListener<List> listener){
        createRequest(listener)
                .clazz(Events.class)
                .request(RetrofitClient.getService().getNewsEvent(true,
                        user,page));
    }

}
