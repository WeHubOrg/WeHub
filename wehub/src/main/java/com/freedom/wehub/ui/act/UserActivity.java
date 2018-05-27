package com.freedom.wehub.ui.act;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.widget.PagerContainer;
import com.freedom.wecore.widget.transformer.ScaleInTransformer;
import com.freedom.wehub.R;
import com.freedom.wehub.adp.ProfileRepAdapter;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.presenter.AccountPresenter;

import java.util.ArrayList;

/**
 * @author vurtne on 18-May-18.
 */
public class UserActivity extends WeActivity<AccountContract.IUserView, AccountPresenter> implements
        AccountContract.IUserView {

    private PagerContainer container;

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
//        ((RelativeLayout)findViewById(R.id.layout_parent)).setClipChildren(false);
        ViewPager mRepoVp = findViewById(R.id.vp_repo);
        container = findViewById(R.id.pager_container);
        ProfileRepAdapter adapter = new ProfileRepAdapter(this,new ArrayList<>());


        container.setOnTouchListener((v, event) -> mRepoVp.dispatchTouchEvent(event));

        //设置可滑动Viewpager的范围
        RelativeLayout.LayoutParams rll = (RelativeLayout.LayoutParams) container.getLayoutParams();
        rll.width = (int) DeviceUtil.getScreenWidth(this);
        rll.height = (int) (DeviceUtil.getScreenHeight(this) / 4);
        container.setLayoutParams(rll);

        //设置Viewpager大小为屏幕的一半
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mRepoVp.getLayoutParams();
        lp.width = (int) (DeviceUtil.getScreenWidth(this) / 1.2);
        lp.height = (int) (DeviceUtil.getScreenHeight(this) / 4);
        mRepoVp.setLayoutParams(lp);

        mRepoVp.setAdapter(adapter);
        //设置缓存数为展示的数目
        mRepoVp.setOffscreenPageLimit(adapter.getCount());
        //设置切换动画
        mRepoVp.setPageTransformer(true, new ScaleInTransformer());
        //设置Page间间距
//        mRepoVp.setPageMargin(DeviceUtil.dip2Px(context,4));
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
