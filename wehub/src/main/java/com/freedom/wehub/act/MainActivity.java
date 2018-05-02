package com.freedom.wehub.act;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.wecore.common.AccountManager;
import com.freedom.wecore.common.User;
import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wehub.R;

import java.util.Map;


/**
 * @author vurtne on 1-May-18.
 */

public class MainActivity extends WeActivity {

    private ImageView mAvatarView;
    private ImageView mAvatarBackgroundView;
    private TextView mUserView;
    private TextView mNameView;
    private TextView mBoiView;
    private NavigationView mMenuView;

    private Map<String,Class> mFragments;

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected WePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mMenuView = findViewById(R.id.layout_menu);
        mAvatarBackgroundView = mMenuView.getHeaderView(0).findViewById(R.id.iv_avatar_bg);
        mAvatarView = mMenuView.getHeaderView(0).findViewById(R.id.iv_avatar);
        mUserView = mMenuView.getHeaderView(0).findViewById(R.id.tv_user);
        mNameView = mMenuView.getHeaderView(0).findViewById(R.id.tv_name);
        mBoiView = mMenuView.getHeaderView(0).findViewById(R.id.tv_bio);
    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        User user = AccountManager.instance().getUser();
        if (user == null){
            return;
        }
        ImageBridge.displayBlurImageValue(user.getAvatarUrl(),mAvatarBackgroundView,50);
        ImageBridge.displayRoundImage(user.getAvatarUrl(), mAvatarView);
        mUserView.setText(user.getLogin());
        mNameView.setText(user.getName());
        mBoiView.setText(user.getBio());
    }

    private void fragment(String tag){
       Bundle args = new Bundle();
       showFragment(Fragment.class.getName(),Fragment.class.getSimpleName(),args,R.id.layout_content);
    }

}
