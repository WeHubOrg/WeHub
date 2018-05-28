package com.freedom.wehub.ui.fragment.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.wecore.bean.Repository;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wecore.tools.RxBus;
import com.freedom.wehub.R;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.contract.ProfileContract;
import com.freedom.wehub.event.FragmentVisibleEvent;
import com.freedom.wehub.presenter.AccountPresenter;
import com.freedom.wehub.presenter.ProfilePresenter;

import java.util.List;

public class ProfileOverviewFragment extends WeFragment<ProfileContract.IOverviewView, ProfilePresenter> implements
        ProfileContract.IOverviewView{


    @Override
    protected int contentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected ProfilePresenter createPresenter() {
        return new ProfilePresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }
}
