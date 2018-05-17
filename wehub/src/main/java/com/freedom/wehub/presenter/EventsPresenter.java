package com.freedom.wehub.presenter;

import android.content.Context;

import com.freedom.wecore.bean.BaseToken;
import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.net.OnResponseListener;
import com.freedom.wecore.net.Response;
import com.freedom.wehub.contract.EventsContract;
import com.freedom.wehub.model.EventModel;
import com.freedom.wehub.quest.EventsService;

import java.util.List;


/**
 * @author vurtne on 5-May-18.
 */

public class EventsPresenter extends WePresenter<EventsContract.IEventsView> implements EventsContract.IEventsPresenter{

    private EventsService mService;

    @Override
    protected void init(Context context) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void requestEvents(String type,int page,String user){
        if (mService == null){
            mService = new EventsService();
        }
        if (Key.NEWS.equals(type)){
            mService.requestNewsEvents(page, user, response -> {
                if (mView != null){
                    mView.onResponseEvents(response.get());
                }
            });
        }else if (Key.EVENTS.equals(type)){
            mService.requestUserEvents(page, user, response -> {
                if (mView != null){
                    mView.onResponseEvents(response.get());
                }
            });
        }

    }
}
