package com.freedom.wehub.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView mAvatarView;
    private TextView mUserTv;
    private TextView mNameTv;
    private TextView mRepositoriesTv;
    private TextView mStarsTv;
    private TextView mFollowwersTv;
    private TextView mFollowingTv;

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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAvatarView = (ImageView) findViewById(R.id.iv_avatar);
        mUserTv = (TextView) findViewById(R.id.tv_user);
        mNameTv = (TextView) findViewById(R.id.tv_name);
        mRepositoriesTv = (TextView) findViewById(R.id.tv_repositories);
        mStarsTv = (TextView) findViewById(R.id.tv_stars);
        mFollowwersTv = (TextView) findViewById(R.id.tv_followers);
        mFollowingTv = (TextView) findViewById(R.id.tv_following);
//        mAvatarBackgroundView = (ImageView) findViewById(R.id.iv_avatar_bg);
//        mAvatarView = (ImageView) findViewById(R.id.iv_avatar);
    }

    @Override
    protected void initStatusBar(int statusHeight) {
        FrameLayout.LayoutParams toolParams = (FrameLayout.LayoutParams) mToolbar.getLayoutParams();
        toolParams.height += statusHeight / 1.5;
        mToolbar.setPadding(0, (int) (statusHeight / 1.5),0,0);
        mToolbar.setLayoutParams(toolParams);

    }

    @Override
    protected void initEvent() {

    }

    /**
     * //        ImageBridge.displayBlurImageValue(mUser.getAvatarUrl(),mAvatarBackgroundView,50);
     * */
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
        RxBus.get().post(FragmentVisibleEvent.create(mToolbar));
        ImageBridge.displayRoundImageWithDefault(mUser.getAvatarUrl(), mAvatarView,R.drawable.ic_hub_round);
        mUserTv.setText(mUser.getLogin());
        mNameTv.setText(mUser.getName());

        mFollowwersTv.setText(mUser.getFollowers()+"");
        mFollowingTv.setText(mUser.getFollowing()+"");

//        mRepositoriesTv.setText();
//        mUserTv.setText();
//        mUserTv.setText(mUser.getLogin());
//        mUserTv.setText(mUser.getLogin());

    }

}
