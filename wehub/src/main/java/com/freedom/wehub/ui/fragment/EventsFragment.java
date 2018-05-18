package com.freedom.wehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.freedom.wecore.bean.Events;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.tools.RxBus;
import com.freedom.wecore.widget.decoration.HorizontalDecoration;
import com.freedom.wecore.widget.refresh.WeRefreshLayout;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.listener.OnRefreshLoadMoreListener;
import com.freedom.wehub.R;
import com.freedom.wehub.adp.EventsAdapter;
import com.freedom.wehub.contract.EventsContract;
import com.freedom.wehub.event.FragmentVisibleEvent;
import com.freedom.wehub.presenter.EventsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 2-May-18.
 */
public class EventsFragment extends WeFragment<EventsContract.IEventsView, EventsPresenter> implements
        EventsContract.IEventsView{

    private Toolbar mToolbar;
    private AppBarLayout mBarView;
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
        mBarView = findViewById(R.id.layout_bar);
        mToolbar = findViewById(R.id.toolbar);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRecyclerView = findViewById(R.id.layout_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new EventsAdapter(context,new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new HorizontalDecoration(DeviceUtil.dip2Px(context,8)));
        mRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mRefreshLayout.setEnableLoadMore(true);
    }

    @Override
    protected void initStatusBar(int statusHeight) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mBarView.getLayoutParams();
        params.height += statusHeight;
        mBarView.setPadding(0, (int) (statusHeight / 1.5),0,0);
        mBarView.setLayoutParams(params);
    }

    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.requestEvents(mType,mAdapter.getDataPage() + 1,mUser.getLogin());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.requestEvents(mType,mAdapter.getDataPage() + 1,mUser.getLogin());
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
        mToolbar.setTitle(mType);
        RxBus.get().post(FragmentVisibleEvent.create(mToolbar));
        showLoad();
        mPresenter.requestEvents(mType,0,mUser.getLogin());
    }

    @Override
    public void onResponseEvents(List<Events> events) {
        if (isLoading()){
            hideLoad();
            mAdapter.setDataPage(0);
            mAdapter.setDatas(events);
            return;
        }
        if (isRefresh){
            isRefresh = false;
            mAdapter.setDataPage(0);
            mRefreshLayout.finishRefresh();
            mAdapter.setDatas(events);
            return;
        }
        mAdapter.setDataPage(mAdapter.getDataPage() + 1);
        mAdapter.addDatas(events);
        mRefreshLayout.finishLoadMore(500);
    }
}
