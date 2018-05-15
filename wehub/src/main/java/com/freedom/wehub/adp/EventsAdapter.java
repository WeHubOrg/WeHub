package com.freedom.wehub.adp;

import android.content.Context;
import android.view.View;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wecore.tools.DateUtil;
import com.freedom.wehub.R;
import com.freedom.wehub.tools.EventsFactory;

import java.util.List;

public class EventsAdapter extends WeAdapter<Events>{

    public EventsAdapter(Context context, List<Events> data) {
        super(R.layout.item_events, context, data);
    }

    @Override
    protected void convert(WeHolder holder, int position, View convertView, Events data) {
        holder
                .setText(R.id.tv_name,data.getActor().getLogin())
                .setVisibile(EventsFactory.EVENT_WATCH.equals(data.getType()),R.id.iv_start)
                .setVisibile(EventsFactory.EVENT_CREATE.equals(data.getType()),R.id.iv_create)
                .setVisibile(EventsFactory.EVENT_FORK.equals(data.getType()),R.id.iv_fork)
                .setText(R.id.tv_time, DateUtil.getLongFromStringWithTZ(data.getCreatedAt()));
    }
}
