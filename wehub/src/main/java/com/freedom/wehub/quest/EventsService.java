package com.freedom.wehub.quest;


import com.freedom.wecore.bean.BaseToken;
import com.freedom.wecore.net.OnResponseListener;
import com.freedom.wecore.net.RetrofitClient;
import com.freedom.wehub.model.EventModel;

/**
 * @author vurtne on 5-May-18.
 */
public class EventsService extends RetrofitClient {

    /**
     * 获取news events
     * */
    public void requestNewsEvents(int page,String user,OnResponseListener<EventModel> listener){
        createRequest(listener)
                .clazz(BaseToken.class)
                .request(RetrofitClient.getService().getNewsEvent(true,
                        user,page));
    }

}
