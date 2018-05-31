package com.freedom.wehub.adp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wecore.tools.DateUtil;
import com.freedom.wecore.tools.DeviceUtil;
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
    private SparseArray<EventsChildAdapter> mAdapterSparse = new SparseArray<>();

    public EventsAdapter(Context context, List<Events> data) {
        super( context, data,R.layout.item_events);
    }

    @Override
    protected void convert(WeHolder holder, int position, View convertView, Events data) {
        holder
                .setText(R.id.tv_name,data.getActor().getLogin())
                .setVisibility(EventsFactory.EVENT_WATCH.equals(data.getType()),R.id.iv_start)
                .setVisibility(EventsFactory.EVENT_CREATE.equals(data.getType()),R.id.iv_create)
                .setVisibility(EventsFactory.EVENT_FORK.equals(data.getType()),R.id.iv_fork)
                .setVisibility(EventsFactory.EVENT_PUSH.equals(data.getType()),R.id.iv_push)
                .setVisibility(EventsFactory.EVENT_ISSUES.equals(data.getType()),R.id.iv_issues)
                .setVisibility(EventsFactory.EVENT_ISSUES_COMMENT.equals(data.getType()),R.id.iv_issues_comment)
                .setText(R.id.tv_time, DateUtil.getLongFromStringWithTZ(data.getCreatedAt()))
                .displayRoundImage(R.id.iv_avatar,data.getActor().getAvatarUrl(),
                        DeviceUtil.dip2Px(getContext(),1),R.drawable.ic_hub_small);
        setContent(holder,data);
        setChildAdapter(holder,data);
        if (EventsFactory.switchNews(data.getType())){
            setNewsMessage(convertView,data);
        }else if (EventsFactory.switchEvents(data.getType())){
            setEventsMessage(convertView,data);
        }
    }

    /** 设置新闻消息 */
    private void setNewsMessage(View convertView, Events data){
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

                    new EventsPair(EventsFactory.switchMessage(data), ContextCompat.getColor(getContext(),R.color.color_323232),null),

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

    /** 设置事件消息 */
    private void setEventsMessage(View convertView, Events data){
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

                    new EventsPair(EventsFactory.switchMessage(data), ContextCompat.getColor(getContext(),R.color.color_323232),null),


                    new EventsPair(getEventsNumber(data), ContextCompat.getColor(getContext(),R.color.color_009ACD),new ClickableSpan(){
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getContext(),data.getRepo().getName(),Toast.LENGTH_LONG).show();
                        }
                    }),

                    new EventsPair(EventsFactory.switchTo(data.getType()), ContextCompat.getColor(getContext(),R.color.color_323232),null),

                    new EventsPair(data.getRepo().getName(), ContextCompat.getColor(getContext(),R.color.color_009ACD),new ClickableSpan(){
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getContext(),data.getRepo().getName(),Toast.LENGTH_LONG).show();
                        }
                    })
            );
        }
    }

    /** 获取提交的分之 和 issues 的序号 */
    private String getEventsNumber(Events data){
        if (EventsFactory.switchIssues(data.getType())){
            return "#"+data.getPayload().getIssue().getNumber();
        }else if (EventsFactory.EVENT_PUSH.equals(data.getType())){
            return data.getPayload().getRef().substring(data.getPayload().getRef().
                            lastIndexOf("/") + 1,
                    data.getPayload().getRef().length());
        }
        return "";
    }

    /** 设置事件的正文 */
    private void setContent(WeHolder holder, Events data){
        boolean isVisible = false;
        String msg = "";
        if (EventsFactory.switchContent(data)){
            isVisible = true;
            if (EventsFactory.EVENT_CREATE.equals(data.getType())){
                msg = TextUtils.isEmpty(data.getPayload().getDescription())?"":data.getPayload().getDescription();
            }else if (EventsFactory.EVENT_ISSUES.equals(data.getType())){
                if (data.getPayload() != null && data.getPayload().getIssue() != null && data.getPayload().getIssue().getTitle() != null){
                    msg = data.getPayload().getIssue().getTitle();
                }
            }else if ( EventsFactory.EVENT_ISSUES_COMMENT.equals(data.getType())){
                if (data.getPayload() != null && data.getPayload().getComment() != null && data.getPayload().getComment().getBody() != null){
                    msg = data.getPayload().getComment().getBody();
                }
            }
        }
        holder.setVisibility(isVisible,R.id.tv_description)
              .setText(R.id.tv_description,msg);
    }

    private void setChildAdapter(WeHolder holder,Events data){
        boolean isVisible = false;
        if (EventsFactory.EVENT_PUSH.equals(data.getType())){
            isVisible = true;
            //TODO 暂时代替
            RecyclerView recyclerView = holder.findView(R.id.rv_child);
            EventsChildAdapter adapter = new EventsChildAdapter(getContext(),data.getPayload().getCommits());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);

            //TODO 为什么这里代码之前好好的，今天编译就报错了 奇怪奇怪
//            EventsChildAdapter adapter = mAdapterSparse.get(R.id.rv_child);
//            if (adapter == null){
//                adapter = new EventsChildAdapter(getContext(),data.getPayload().getCommits());
//            }
//            RecyclerView recyclerView = holder.findView(R.id.rv_child);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            recyclerView.setAdapter(adapter);
        }
        holder.setVisibility(isVisible,R.id.rv_child);

    }
}
