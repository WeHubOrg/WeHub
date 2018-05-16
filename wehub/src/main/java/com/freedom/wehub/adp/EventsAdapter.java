package com.freedom.wehub.adp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wecore.tools.DateUtil;
import com.freedom.wecore.widget.el.EventsLayout;
import com.freedom.wecore.widget.el.EventsPair;
import com.freedom.wehub.R;
import com.freedom.wehub.tools.EventsFactory;

import java.util.List;

/**
 * @author vurtne on 15-May-18.
 */
public class EventsAdapter extends WeAdapter<Events>{

    public EventsAdapter(Context context, List<Events> data) {
        super(R.layout.item_events, context, data);
    }

    @Override
    protected void convert(WeHolder holder, int position, View convertView, Events data) {
        holder
                .setText(R.id.tv_name,data.getActor().getLogin())
                .setVisibility(EventsFactory.EVENT_WATCH.equals(data.getType()),R.id.iv_start)
                .setVisibility(EventsFactory.EVENT_CREATE.equals(data.getType()),R.id.iv_create)
                .setVisibility(EventsFactory.EVENT_FORK.equals(data.getType()),R.id.iv_fork)
                .setText(R.id.tv_time, DateUtil.getLongFromStringWithTZ(data.getCreatedAt()))
                .displayRoundImage(R.id.iv_avatar,data.getActor().getAvatarUrl(),R.drawable.ic_hub_small);

        EventsLayout layout = holder.findView(R.id.tv_msg);
        layout.setPair(new EventsPair("11111", Color.RED,null),new EventsPair("2222", Color.BLUE,null),
                new EventsPair("2222", Color.YELLOW,null),new EventsPair("2222", Color.GREEN,null));
//        textView.setText();

    }
}
