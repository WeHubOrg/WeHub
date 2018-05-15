package com.freedom.wehub.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.freedom.wecore.bean.Actor;
import com.freedom.wecore.bean.Events;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.model.AccountManager;
import com.freedom.wecore.tools.DateUtil;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.widget.decoration.HorizontalDecoration;
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
    private Toolbar mToolbar;
    private AppBarLayout mBarView;


    private User mUser;
    private EventsAdapter mAdapter;

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
        mBarView = findViewById(R.id.layout_bar);
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.layout_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new EventsAdapter(context,new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new HorizontalDecoration(DeviceUtil.dip2Px(context,8)));

        mToolbar.setTitle("News");
    }

    @Override
    protected void initStatusBar(int statusHeight) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mBarView.getLayoutParams();
        params.height += statusHeight;
        mBarView.setPadding(0,statusHeight,0,0);
        mBarView.setLayoutParams(params);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mUser = AccountManager.instance().getUser();
        showLoad();
        mPresenter.requestEvents(0,mUser.getLogin());
    }

    @Override
    public void onResponseEvents(List<Events> events) {
        if (isLoading()){
            hideLoad();
        }
        mAdapter.addDatas(events);
    }
}
