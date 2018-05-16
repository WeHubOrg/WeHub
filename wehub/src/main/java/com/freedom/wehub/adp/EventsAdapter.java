package com.freedom.wehub.adp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wecore.tools.DateUtil;
import com.freedom.wecore.widget.el.EventsPair;
import com.freedom.wecore.widget.el.EventsTextView;
import com.freedom.wehub.R;
import com.freedom.wehub.tools.EventsFactory;

import java.util.List;

/**
 * @author vurtne on 15-May-18.
 */
public class EventsAdapter extends WeAdapter<Events>{

    private SparseArray<EventsTextView> mSparse = new SparseArray<>();

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

        EventsTextView messageTv = mSparse.get(R.id.tv_msg);
        if (messageTv == null){
            messageTv = convertView.findViewById(R.id.tv_msg);
            messageTv.setTextPair(
                    new EventsPair(data.getActor().getLogin(), ContextCompat.getColor(getContext(),R.color.color_009ACD),new ClickableSpan(){
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getContext(),data.getActor().getLogin(),Toast.LENGTH_LONG).show();
                        }
                    }),

                    new EventsPair(EventsFactory.switchMssage(data.getType()), ContextCompat.getColor(getContext(),R.color.color_323232),null),

                    new EventsPair(data.getRepo().getName(), ContextCompat.getColor(getContext(),R.color.color_009ACD),new ClickableSpan(){
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getContext(),data.getRepo().getName(),Toast.LENGTH_LONG).show();
                        }
                    }),

                    new EventsPair(EventsFactory.switchTo(data.getType()), ContextCompat.getColor(getContext(),R.color.color_323232),null),

                    new EventsPair(EventsFactory.switchDestinationAddress(data),ContextCompat.getColor(getContext(),R.color.color_009ACD),new ClickableSpan(){
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getContext(),EventsFactory.switchDestinationAddress(data),Toast.LENGTH_LONG).show();
                        }
                    })
            );
        }
    }
}
