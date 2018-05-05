package com.freedom.wehub.ui.fragment;

import android.os.Bundle;

import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.model.AccountManager;
import com.freedom.wehub.R;
import com.freedom.wehub.contract.EventsContract;
import com.freedom.wehub.presenter.EventsPresenter;

/**
 * @author vurtne on 2-May-18.
 */
public class EventsFragment extends WeFragment<EventsContract.IEventsView, EventsPresenter> implements
        EventsContract.IEventsView{

    private User mUser;

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
}
