package com.freedom.wehub.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.model.AccountManager;
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
        mRecyclerView = findViewById(R.id.layout_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new EventsAdapter(context,new ArrayList<>());
    }

    @Override
    protected void initStatusBar(int statusHeight) {

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
        mAdapter.addDatas(events);
    }
}
