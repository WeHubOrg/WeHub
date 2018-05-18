package com.freedom.wehub.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wecore.tools.RxBus;
import com.freedom.wehub.R;
import com.freedom.wehub.event.FragmentVisibleEvent;

/**
 * @author vurtne on 18-May-18.
 */
public class ProfileFragment extends WeFragment {

    private Toolbar mToolbar;
    private AppBarLayout mBarLayout;
    private ImageView mAvatarView;
    private ImageView mAvatarBackgroundView;


    private User mUser;


    @Override
    protected int contentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected WePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBarLayout = (AppBarLayout) findViewById(R.id.layout_bar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAvatarBackgroundView = (ImageView) findViewById(R.id.iv_avatar_bg);
        mAvatarView = (ImageView) findViewById(R.id.iv_avatar);
    }

    @Override
    protected void initStatusBar(int statusHeight) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mBarLayout.getLayoutParams();
        params.height += statusHeight / 1.5;
        mBarLayout.setLayoutParams(params);

        mToolbar.setPadding(0, (int) (statusHeight / 1.5),0,0);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArgs();
        if (bundle == null){
            return;
        }
        mUser = bundle.getParcelable(Key.USER);
        if (mUser == null){
            return;
        }
        mToolbar.setTitle(mUser.getLogin());
        RxBus.get().post(FragmentVisibleEvent.create(mToolbar));

        ImageBridge.displayBlurImageValue(mUser.getAvatarUrl(),mAvatarBackgroundView,50);
        ImageBridge.displayRoundImage(mUser.getAvatarUrl(), mAvatarView);

    }

}
