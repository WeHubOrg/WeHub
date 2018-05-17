package com.freedom.wehub.ui.act;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedom.wecore.common.Key;
import com.freedom.wecore.model.AccountManager;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wehub.R;
import com.freedom.wehub.ui.fragment.EventsFragment;

import java.util.HashMap;
import java.util.Map;


/**
 * @author vurtne on 1-May-18.
 */
public class MainActivity extends WeActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private AppBarLayout mBarView;
    private ImageView mAvatarView;
    private DrawerLayout mDrawerLayout;
    private ImageView mAvatarBackgroundView;
    private TextView mUserView;
    private TextView mNameView;
    private TextView mBoiView;
    private NavigationView mMenuView;

    private final Map<String, String> mFragments = new HashMap<>();

    private User mUser;

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
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

        mMenuView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mUser = AccountManager.instance().getUser();
        if (mUser == null){
            return;
        }
        ImageBridge.displayBlurImageValue(mUser.getAvatarUrl(),mAvatarBackgroundView,50);
        ImageBridge.displayRoundImage(mUser.getAvatarUrl(), mAvatarView);
        mUserView.setText(mUser.getLogin());
        mNameView.setText(mUser.getName());
        mBoiView.setText(mUser.getBio());
        showFragment(getString(R.string.title_news));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case R.id.menu_news:
                showFragment(getString(R.string.title_news));
                break;
            case R.id.menu_events:
                showFragment(getString(R.string.title_events));
                break;
            default:
                break;
        }
        if (mDrawerLayout.isDrawerOpen(mMenuView)) {
            mDrawerLayout.closeDrawer(mMenuView);
        }
        return true;
    }

    private void setTitle(String title){
        if (getSupportActionBar() != null && title != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    private void showFragment(String tag){
        setTitle(tag);
        String name = mFragments.get(tag);
        Bundle bundle = null;
        if (name == null){
            name = EventsFragment.class.getName();
            mFragments.put(tag,name);
            bundle = new Bundle();
            bundle.putString(Key.TYPE_EVENTS,Key.EVENTS);
            bundle.putParcelable(Key.USER,mUser);
        }
        showFragment(name,tag,bundle,R.id.layout_content);
    }


}
