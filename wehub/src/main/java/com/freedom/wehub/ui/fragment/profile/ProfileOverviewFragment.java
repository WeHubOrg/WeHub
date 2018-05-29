package com.freedom.wehub.ui.fragment.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.freedom.wecore.widget.refresh.footer.ClassicsFooter;
import com.freedom.wecore.widget.refresh.header.ClassicsHeader;
import com.freedom.wecore.widget.refresh.listener.OnRefreshLoadMoreListener;
import com.freedom.wecore.widget.transformer.ScaleInTransformer;
import com.freedom.wehub.R;
import com.freedom.wehub.adp.ProfileRepAdapter;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.contract.ProfileContract;
import com.freedom.wehub.event.FragmentVisibleEvent;
import com.freedom.wehub.presenter.AccountPresenter;
import com.freedom.wehub.presenter.ProfilePresenter;
import com.freedom.wehub.test.BaseRecyclerAdapter;
import com.freedom.wehub.test.SmartViewHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @author vurtne on 18-May-18.
 */
public class ProfileOverviewFragment extends Fragment{

    private View mParentLayout;
    private ViewPager mRopeView;
    private WeRefreshLayout mRefreshLayout;
    private PagerContainer mContainerLayout;



    private ProfileRepAdapter mRepoAdapter;


    private User mUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRefreshLayout = new WeRefreshLayout(inflater.getContext());
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(inflater.getContext()));
        mParentLayout = inflater.inflate(R.layout.fragment_profile_overview,mRefreshLayout.getLayout());
        mRefreshLayout.setRefreshContent(mParentLayout);
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(inflater.getContext()));
        mRefreshLayout.setPrimaryColorsId(R.color.color_333, android.R.color.white);
        return mRefreshLayout.getLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initEvent();
        initData(savedInstanceState);
    }

    private void initView(){
        mRopeView = mParentLayout.findViewById(R.id.vp_rope);
        mContainerLayout = mParentLayout.findViewById(R.id.layout_container);

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

    @SuppressLint("ClickableViewAccessibility")
    protected void initEvent()  {
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
//                mPresenter.requestEvents(mType,0,mUser.getLogin());
            }
        });
    }

    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle == null){
            return;
        }
        mUser = bundle.getParcelable(Key.USER);
        if (mUser == null){
            return;
        }
//        mPresenter.requestUserRepositories(mUser.getLogin(),1,"","","");
    }

    public void requestRepositories(List<Repository> ropes) {
//        mProProgress.setVisibility(View.GONE);
        mRepoAdapter = new ProfileRepAdapter(getContext(),ropes);
        mRopeView.setAdapter(mRepoAdapter);
    }


}
