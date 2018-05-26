package com.freedom.wehub.ui.act;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.listener.OnRefreshListener;
import com.freedom.wecore.widget.transformer.ScaleInTransformer;
import com.freedom.wecore.widget.transformer.WeTransformer;
import com.freedom.wehub.R;
import com.freedom.wehub.adp.ProfileRepAdapter;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.presenter.AccountPresenter;

import java.util.ArrayList;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

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
        ViewPager mRepoVp = findViewById(R.id.vp_repo);
        ProfileRepAdapter adapter ;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRepoVp.getLayoutParams();
        layoutParams.width = (int) (DeviceUtil.getScreenWidth(this) - DeviceUtil.dip2Px(UserActivity.this.getApplicationContext(),80f));
        mRepoVp.setLayoutParams(layoutParams);
        mRepoVp.setPageMargin(DeviceUtil.dip2Px(this, 10));
        adapter = new ProfileRepAdapter(this,new ArrayList<>());
        mRepoVp.setAdapter(adapter);
        mRepoVp.setOffscreenPageLimit(6);
//        mRepoVp.setPageTransformer(true, new ScaleInTransformer(1.0f));
        mRepoVp.setPageTransformer(true, new WeTransformer());
    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//
    }
}
