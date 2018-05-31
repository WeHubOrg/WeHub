package com.freedom.wehub.adp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.freedom.wecore.bean.Commits;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wecore.widget.el.EventsPair;
import com.freedom.wecore.widget.el.EventsTextView;
import com.freedom.wehub.R;

import java.util.List;


/**
 * @author vurtne on 18-May-18.
 */
public class EventsChildAdapter extends WeAdapter<Commits> {

    public EventsChildAdapter( Context context, List<Commits> datas) {
        super(context, datas,R.layout.item_events_child);
    }

    @Override
    protected void convert(WeHolder holder, int position, View convertView, Commits data) {
        if (data == null){
            return;
        }
        EventsTextView textView = holder.findView(R.id.tv_msg);
        textView.setTextPair(
                new EventsPair(data.getSha().substring(0,6), ContextCompat.getColor(getContext(),R.color.color_009ACD),new ClickableSpan(){
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(getContext(),data.getSha().substring(0,6),Toast.LENGTH_LONG).show();
                    }
                }),

                new EventsPair("- " + data.getMessage(), ContextCompat.getColor(getContext(),R.color.color_666666),null)

        );
    }
}
