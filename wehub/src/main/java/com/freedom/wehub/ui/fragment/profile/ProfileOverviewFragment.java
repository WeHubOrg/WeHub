package com.freedom.wehub.ui.fragment.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedom.wecore.bean.Repository;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wecore.tools.RxBus;
import com.freedom.wecore.widget.PagerContainer;
import com.freedom.wecore.widget.refresh.WeRefreshLayout;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.listener.OnRefreshLoadMoreListener;
import com.freedom.wecore.widget.transformer.ScaleInTransformer;
import com.freedom.wehub.R;
import com.freedom.wehub.adp.ProfileRepAdapter;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.contract.ProfileContract;
import com.freedom.wehub.event.FragmentVisibleEvent;
import com.freedom.wehub.presenter.AccountPresenter;
import com.freedom.wehub.presenter.ProfilePresenter;

import java.util.List;


/**
 * @author vurtne on 18-May-18.
 */
public class ProfileOverviewFragment extends WeFragment<ProfileContract.IOverviewView, ProfilePresenter> implements
        ProfileContract.IOverviewView{

    private ViewPager mRopeView;
    private WeRefreshLayout mRefreshLayout;
    private PagerContainer mContainerLayout;



    private ProfileRepAdapter mRepoAdapter;


    private User mUser;

    @Override
    protected int contentView() {
        return R.layout.fragment_profile_overview;
    }

    @Override
    protected ProfilePresenter createPresenter() {
        return new ProfilePresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRopeView = findViewById(R.id.vp_rope);
        mContainerLayout = findViewById(R.id.layout_container);
        mRefreshLayout = findViewById(R.id.rl_parent);

        LinearLayout.LayoutParams rll = (LinearLayout.LayoutParams) mContainerLayout.getLayoutParams();
        rll.width = (int) DeviceUtil.getScreenWidth(getActivity());
        mContainerLayout.setLayoutParams(rll);

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mRopeView.getLayoutParams();
        lp.width = (int) (DeviceUtil.getScreenWidth(getActivity()) / 1.4);
        mRopeView.setLayoutParams(lp);

        mRopeView.setOffscreenPageLimit(6);
        mRopeView.setPageTransformer(true, new ScaleInTransformer());
        mRopeView.setPageMargin(DeviceUtil.dip2Px(getContext(),12));

        mRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mRefreshLayout.setEnableLoadMore(true);

    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void initEvent() {
        mContainerLayout.setOnTouchListener((v, event) -> mRopeView.dispatchTouchEvent(event));

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                mPresenter.requestEvents(mType,mAdapter.getDataPage() + 1,mUser.getLogin());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                mPresenter.requestEvents(mType,mAdapter.getDataPage() + 1,mUser.getLogin());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
//                mPresenter.requestEvents(mType,0,mUser.getLogin());
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle == null){
            return;
        }
        mUser = bundle.getParcelable(Key.USER);
        if (mUser == null){
            return;
        }
        mPresenter.requestUserRepositories(mUser.getLogin(),1,"","","");
    }

    @Override
    public void requestRepositories(List<Repository> ropes) {
//        mProProgress.setVisibility(View.GONE);
        mRepoAdapter = new ProfileRepAdapter(getContext(),ropes);
        mRopeView.setAdapter(mRepoAdapter);
    }
}
