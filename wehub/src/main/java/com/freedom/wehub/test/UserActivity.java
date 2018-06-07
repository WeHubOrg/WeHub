package com.freedom.wehub.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.widget.refresh.WeRefreshLayout;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.header.ClassicsHeader;
import com.freedom.wehub.R;
import com.freedom.wehub.presenter.AccountPresenter;

import java.util.Arrays;
import java.util.Collection;


/**
 * @author vurtne on 18-May-18.
 */
public class UserActivity extends WeActivity {


    @Override
    protected int contentView() {
        return R.layout.activity_user;
    }

    @Override
    protected AccountPresenter createPresenter() {
        return null;
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
