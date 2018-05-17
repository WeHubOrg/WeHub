package com.freedom.wehub.ui.act;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedom.wecore.model.AccountManager;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wehub.R;
import com.freedom.wehub.ui.fragment.EventsFragment;

import java.util.Map;



/**
 * @author vurtne on 1-May-18.
 */
public class MainActivity extends WeActivity {

    private Toolbar mToolbar;
    private AppBarLayout mBarView;
    private ImageView mAvatarView;
    private DrawerLayout mDrawerLayout;
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
        mBarView = findViewById(R.id.layout_bar);
        mToolbar = findViewById(R.id.toolbar);
        mMenuView = findViewById(R.id.layout_menu);
        mDrawerLayout = findViewById(R.id.layout_drawer);
        mAvatarBackgroundView = mMenuView.getHeaderView(0).findViewById(R.id.iv_avatar_bg);
        mAvatarView = mMenuView.getHeaderView(0).findViewById(R.id.iv_avatar);
        mUserView = mMenuView.getHeaderView(0).findViewById(R.id.tv_user);
        mNameView = mMenuView.getHeaderView(0).findViewById(R.id.tv_name);
        mBoiView = mMenuView.getHeaderView(0).findViewById(R.id.tv_bio);


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setTitle("News");
    }

    @Override
    protected void initStatusBar(int statusHeight) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mBarView.getLayoutParams();
        params.height += statusHeight;
        mBarView.setPadding(0,statusHeight,0,0);
        mBarView.setLayoutParams(params);
    }

    @Override
    protected void initEvent() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name,
                R.string.sign_in);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
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

        showFragment(EventsFragment.class.getName(),EventsFragment.class.getSimpleName(),new Bundle(),R.id.layout_content);
    }

    private void fragment(String tag){
       Bundle args = new Bundle();
       showFragment(Fragment.class.getName(),Fragment.class.getSimpleName(),args,R.id.layout_content);
    }

}
