package com.freedom.wehub.ui.fragment;

import android.annotation.SuppressLint;
import  android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wecore.tools.RxBus;
import com.freedom.wecore.widget.refresh.WeRefreshLayout;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.header.ClassicsHeader;
import com.freedom.wehub.R;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.event.FragmentVisibleEvent;
import com.freedom.wehub.presenter.AccountPresenter;
import com.freedom.wehub.test.BaseRecyclerAdapter;
import com.freedom.wehub.test.SmartViewHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * @author vurtne on 18-May-18.
 */
public class ProfileFragment extends WeFragment<AccountContract.IProfilerView, AccountPresenter> implements
        AccountContract.IProfilerView{

    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingLayout;
    private AppBarLayout mBarLayout;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ImageView mAvatarView;
    private ImageView mAvatarBackgroundView;
    private TextView mUserTv;
    private TextView mNameTv;
    private TextView mRepositoriesTv;
    private TextView mFollowersTv;
    private TextView mFollowingTv;
    private TextView mGistsTv;
    private ViewPager mContentView;


//    private ProgressBar mProProgress;
//    private ViewPager mRepoVp;
//    private PagerContainer mContainerLayout;


    private ContentAdapter mContentAdapter;
    private User mUser;




    @Override
    protected int contentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected AccountPresenter createPresenter() {
        return new AccountPresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mCoordinatorLayout = findViewById(R.id.cd_parent);
        mCollapsingLayout = findViewById(R.id.layout_collapsing);
        mBarLayout = findViewById(R.id.layout_bar);
        mToolbar = findViewById(R.id.toolbar);
        mAvatarView = findViewById(R.id.iv_avatar);
        mUserTv = findViewById(R.id.tv_user);
        mNameTv = findViewById(R.id.tv_name);
        mRepositoriesTv = findViewById(R.id.tv_repositories);
        mGistsTv = findViewById(R.id.tv_gists);
        mFollowersTv = findViewById(R.id.tv_followers);
        mFollowingTv = findViewById(R.id.tv_following);
        mContentView = findViewById(R.id.vp_content);
        mTabLayout = findViewById(R.id.tab_layout);
        mAvatarBackgroundView = findViewById(R.id.iv_avatar_bg);




        mTabLayout.setupWithViewPager(mContentView);
//        mNestedView.setFillViewport (true);

//        mProProgress = findViewById(R.id.progress_pro);
//        mRepoVp = findViewById(R.id.vp_repo);
//        mContainerLayout = findViewById(R.id.layout_container);


//        LinearLayout.LayoutParams rll = (LinearLayout.LayoutParams) mContainerLayout.getLayoutParams();
//        rll.width = (int) DeviceUtil.getScreenWidth(getActivity());
//        mContainerLayout.setLayoutParams(rll);
//
//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mRepoVp.getLayoutParams();
//        lp.width = (int) (DeviceUtil.getScreenWidth(getActivity()) / 1.4);
//        mRepoVp.setLayoutParams(lp);
//        mRepoVp.setOffscreenPageLimit(6);
//        mRepoVp.setPageTransformer(true, new ScaleInTransformer());
//        mRepoVp.setPageMargin(DeviceUtil.dip2Px(context,16));

    }

    @Override
    protected void initStatusBar(int statusHeight) {
        FrameLayout.LayoutParams toolParams = (FrameLayout.LayoutParams) mToolbar.getLayoutParams();
        toolParams.height += statusHeight / 1.5;
        mToolbar.setPadding(0, (int) (statusHeight / 1.5),0,0);
        mToolbar.setLayoutParams(toolParams);

        //        mProProgress.setVisibility(View.GONE);
//        FrameLayout.LayoutParams tobParams = (FrameLayout.LayoutParams) mTabLayout.getLayoutParams();
//        tobParams.height += statusHeight / 1.5;
//        mTabLayout.setPadding(0, (int) (statusHeight / 1.5),0,0);
//        mTabLayout.setLayoutParams(tobParams);

    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void initEvent() {
//        mContainerLayout.setOnTouchListener((v, event) -> mRepoVp.dispatchTouchEvent(event));

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        RxBus.get().post(FragmentVisibleEvent.create(mToolbar));
        Bundle bundle = getArgs();
        if (bundle == null){
            return;
        }
        String user = bundle.getString(Key.USER);
        if (TextUtils.isEmpty(user)){
            return;
        }
        showLoad();
        mPresenter.requestPersonInfo(user);
    }

    @Override
    public void requestPerson(User user) {
        hideLoad();
        mUser = user;
        if (mUser == null){
            return;
        }
        mContentAdapter = new ContentAdapter(this.getChildFragmentManager());
        mContentView.setAdapter(mContentAdapter);



        ImageBridge.displayRoundImageWithDefault(mUser.getAvatarUrl(), mAvatarView,R.drawable.ic_hub_round);
        mUserTv.setText(mUser.getLogin());
        mNameTv.setText(mUser.getName());
        ImageBridge.displayBlurImageValue(user.getAvatarUrl(),mAvatarBackgroundView,50);
        mFollowersTv.setText(String.valueOf(mUser.getFollowers()));
        mFollowingTv.setText(String.valueOf(mUser.getFollowing()));
        mRepositoriesTv.setText(String.valueOf(user.getPublicRepos()));
        mGistsTv.setText(String.valueOf(user.getPublicGists()));
    }

    public class ContentAdapter extends FragmentStatePagerAdapter {

        private final String[] mTitles = {"Overview", "Repositories", "Stars", "Followers", "Following"};
        private final Map<String,ContentFragment> mFragments = new HashMap<>();

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            ContentFragment fragment = mFragments.get(mTitles[position]);
            if (fragment == null){
                fragment = new ContentFragment();
                mFragments.put(mTitles[position],fragment);
            }
            return fragment;
        }


    }

    public static class ContentFragment extends Fragment {

        private RecyclerView mRecyclerView;
        private BaseRecyclerAdapter<Void> mAdapter;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            RefreshLayout refreshLayout = new WeRefreshLayout(inflater.getContext());
            refreshLayout.setRefreshHeader(new ClassicsHeader(inflater.getContext()));
            refreshLayout.setRefreshContent(mRecyclerView = new RecyclerView(inflater.getContext()));
            refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return refreshLayout.getLayout();
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            mRecyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter<Void>(initData(), android.R.layout.simple_list_item_2) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Void model, int position) {
                    holder.text(android.R.id.text1, "item"+position);
                    holder.text(android.R.id.text2, "This is the abstract item "+ position);
                    holder.textColorId(android.R.id.text2, R.color.color_2D3E4F);
                }
            });
        }

        private Collection<Void> initData() {
            return Arrays.asList(null,null,null);
        }

        public void onRefresh(final RefreshLayout refreshLayout) {
            refreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.refresh(initData());
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(false);
                }
            }, 2000);
        }

        public void onLoadMore(final RefreshLayout refreshLayout) {
            refreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.loadMore(initData());
                    if (mAdapter.getItemCount() > 60) {
                        Toast.makeText(getContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                        refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                    } else {
                        refreshLayout.finishLoadMore();
                    }
                }
            }, 2000);
        }
    }

}
