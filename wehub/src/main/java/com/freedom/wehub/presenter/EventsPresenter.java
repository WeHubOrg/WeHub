package com.freedom.wehub.presenter;

import android.content.Context;

import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wehub.contract.EventsContract;
import com.freedom.wehub.quest.EventsService;



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
