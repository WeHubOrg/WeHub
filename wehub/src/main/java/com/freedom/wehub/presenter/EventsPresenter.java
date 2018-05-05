package com.freedom.wehub.presenter;

import android.content.Context;

import com.freedom.wecore.bean.BaseToken;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.net.OnResponseListener;
import com.freedom.wecore.net.Response;
import com.freedom.wehub.contract.EventsContract;
import com.freedom.wehub.model.EventModel;
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
    public void requestEvents(int page,String user){
        if (mService == null){
            mService = new EventsService();
        }
        mService.requestNewsEvents(page, user, new OnResponseListener<EventModel>() {

            @Override
            public void onResponse(Response<EventModel> response) {
                int i = 1;
            }
        });
    }
}
