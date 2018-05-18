package com.freedom.wehub.ui.act;

import android.os.Bundle;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wehub.R;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.presenter.AccountPresenter;

/**
 * @author vurtne on 18-May-18.
 */
public class UserActivity extends WeActivity<AccountContract.IUserView, AccountPresenter> implements
        AccountContract.IUserView {

    @Override
    protected int contentView() {
        return R.layout.activity_user;
    }

    @Override
    protected AccountPresenter createPresenter() {
        return new AccountPresenter();
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

    }
}
