package com.freedom.wehub.act;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.widget.ImageView;

import com.freedom.wecore.common.AccountManager;
import com.freedom.wecore.common.User;
import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.tools.RoundTransform;
import com.freedom.wehub.R;
import com.squareup.picasso.Picasso;

/**
 * @author vurtne on 1-May-18.
 */

public class MainActivity extends WeActivity {

    private ImageView mAvatarView;
    private ImageView in;
    private ImageView getAvatarBackgroundView;
    private NavigationView mMenuView;

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mMenuView = findViewById(R.id.layout_menu);
        in = findViewById(R.id.iv);
        getAvatarBackgroundView = mMenuView.getHeaderView(0).findViewById(R.id.iv_avatar_bg);
        mAvatarView = mMenuView.getHeaderView(0).findViewById(R.id.iv_avatar);
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
        Picasso.with(context).load(user.getAvatarUrl()).into(in);
//        Picasso.with(context).load(user.getAvatarUrl()).transform(new RoundTransform(
//                (int) context.getResources().getDimension(R.dimen.dimen_24))).into(mAvatarView);
        Picasso.with(context).load(user.getAvatarUrl()).into(getAvatarBackgroundView);
    }

    @Override
    protected WePresenter createPresenter() {
        return null;
    }
}
