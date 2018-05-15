package com.freedom.wehub.adp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wecore.tools.DateUtil;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wehub.R;
import com.freedom.wehub.tools.EventsFactory;

import java.util.List;

public class EventsAdapter extends WeAdapter<Events>{

    public EventsAdapter(Context context, List<Events> data) {
        super(R.layout.item_events, context, data);
    }

    @Override
    protected void convert(WeHolder holder, int position, View convertView, Events data) {
        CardView cardView = holder.findView(R.id.view_card);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cardView.getLayoutParams();
        params.setMargins(params.leftMargin, DeviceUtil.dip2Px(getContext(),position == 0 ? 8 : 4),params.rightMargin,params.bottomMargin);
        holder
                .setText(R.id.tv_name,data.getActor().getLogin())
                .setVisibility(EventsFactory.EVENT_WATCH.equals(data.getType()),R.id.iv_start)
                .setVisibility(EventsFactory.EVENT_CREATE.equals(data.getType()),R.id.iv_create)
                .setVisibility(EventsFactory.EVENT_FORK.equals(data.getType()),R.id.iv_fork)
                .setText(R.id.tv_time, DateUtil.getLongFromStringWithTZ(data.getCreatedAt()))
                .displayRoundImage(R.id.iv_avatar,data.getActor().getAvatarUrl(),R.drawable.ic_hub_small);
    }
}
