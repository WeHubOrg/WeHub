package com.freedom.wehub.ui.act;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedom.wecore.model.AccountManager;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wehub.R;



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

    private SparseArray<Class> mFragments;

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
//            getSupportActionBar().setTitle(getString(R.string.title_news));
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
        User user = AccountManager.instance().getUser();
        if (user == null){
            return;
        }
        ImageBridge.displayBlurImageValue(user.getAvatarUrl(),mAvatarBackgroundView,50);
        ImageBridge.displayRoundImage(user.getAvatarUrl(), mAvatarView);
        mUserView.setText(user.getLogin());
        mNameView.setText(user.getName());
        mBoiView.setText(user.getBio());
        Bundle bundle = new Bundle();
//        bundle.p
        showFragment(getString(R.string.title_news),null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setTitle(item.getTitle());
        item.setChecked(true);
        if (mDrawerLayout.isDrawerOpen(mMenuView)) {
            mDrawerLayout.closeDrawer(mMenuView);
        }
        return true;
    }

    private void setTitle(String title){
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_news));
        }
    }

    private void showFragment(String tag,Bundle bundle){
       showFragment(Fragment.class.getName(),tag,bundle,R.id.layout_content);
    }


}
