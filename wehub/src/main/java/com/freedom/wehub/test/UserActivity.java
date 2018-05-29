package com.freedom.wehub.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.widget.refresh.WeRefreshLayout;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.header.ClassicsHeader;
import com.freedom.wehub.R;
import com.freedom.wehub.presenter.AccountPresenter;

import java.util.Arrays;
import java.util.Collection;


/**
 * @author vurtne on 18-May-18.
 */
public class UserActivity extends WeActivity {


    @Override
    protected int contentView() {
        return R.layout.activity_user;
    }

    @Override
    protected AccountPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new SmartPagerAdapter());

        TabLayout mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(viewPager);

//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean misAppbarExpand = true;
////            View fab = findViewById(R.id.fab);
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                int scrollRange = appBarLayout.getTotalScrollRange();
//                float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
//                if (fraction < 0.1 && misAppbarExpand) {
//                    misAppbarExpand = false;
////                    fab.animate().scaleX(0).scaleY(0);
//                }
//                if (fraction > 0.8 && !misAppbarExpand) {
//                    misAppbarExpand = true;
////                    fab.animate().scaleX(1).scaleY(1);
//                }
//            }
//        });
    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private class SmartPagerAdapter extends FragmentStatePagerAdapter {

        private final SmartFragment[] fragments;

        SmartPagerAdapter() {
            super(getSupportFragmentManager());
            this.fragments = new SmartFragment[]{
                    new SmartFragment(),new SmartFragment()
            };
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "asdsada";
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }
    }


    public static class SmartFragment extends Fragment {

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
