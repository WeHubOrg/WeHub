package com.freedom.wehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.widget.decoration.HorizontalDecoration;
import com.freedom.wecore.widget.refresh.WeRefreshLayout;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.listener.OnRefreshLoadMoreListener;
import com.freedom.wehub.R;
import com.freedom.wehub.adp.EventsAdapter;
import com.freedom.wehub.contract.EventsContract;
import com.freedom.wehub.presenter.EventsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 2-May-18.
 */
public class EventsFragment extends WeFragment<EventsContract.IEventsView, EventsPresenter> implements
        EventsContract.IEventsView{


    private RecyclerView mRecyclerView;
    private WeRefreshLayout mRefreshLayout;

    private User mUser;
    private EventsAdapter mAdapter;
    private String mType;

    @Override
    protected int contentView() {
        return R.layout.fragment_news;
    }

    @Override
    protected EventsPresenter createPresenter() {
        return new EventsPresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRecyclerView = findViewById(R.id.layout_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new EventsAdapter(context,new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new HorizontalDecoration(DeviceUtil.dip2Px(context,8)));
    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                mPresenter.requestEvents(mType,0,mUser.getLogin());
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArgs();
        if (bundle == null){
            return;
        }
        mType = bundle.getString(Key.TYPE_EVENTS);
        mUser = bundle.getParcelable(Key.USER);
        showLoad();
        mPresenter.requestEvents(mType,0,mUser.getLogin());
    }

    @Override
    public void onResponseEvents(List<Events> events) {
        if (isLoading()){
            hideLoad();
            mAdapter.setDatas(events);
            return;
        }
        if (isRefresh){
            mRefreshLayout.finishRefresh(500);
            mAdapter.setDatas(events);
            return;
        }
        mAdapter.addDatas(events);

    }
}
