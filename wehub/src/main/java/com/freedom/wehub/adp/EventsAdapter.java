package com.freedom.wehub.adp;

import android.content.Context;
import android.view.View;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wehub.R;

import java.util.List;

public class EventsAdapter extends WeAdapter<Events>{

    public EventsAdapter(Context context, List<Events> data) {
        super(R.layout.item_events, context, data);
    }

    @Override
    protected void convert(WeHolder holder, int position, View convertView, Events data) {

    }
}
