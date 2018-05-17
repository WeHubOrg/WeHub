package com.freedom.wecore.common;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.freedom.wecore.R;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.tools.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * @author vurtne on 2-May-18.
 */
public abstract class WeFragment<V extends IWeContract.View,P extends WePresenter> extends Fragment{

    private final int DEFAULT_INTERVAL = 1;
    protected Context context;
    protected View mParentGroup;
    protected View mLoadGroup;
    protected View mContentGroup;
    protected View mView;
    private CompositeDisposable mCompositeDisposable;
    protected P mPresenter;
    private Bundle mArgs;
    protected boolean isRefresh;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (mPresenter == null){
            mPresenter = createPresenter();
        }
        if (mPresenter != null && !mPresenter.isViewAttached()){
            mPresenter.attachView((V)this);
            mPresenter.initial(context);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState){
        mParentGroup = inflater.inflate(R.layout.layout_parent, container, false);
        mLoadGroup = getLayoutInflater().inflate(R.layout.layout_loading,null);
        mLoadGroup.setVisibility(View.GONE);
        mContentGroup = inflater.inflate(contentView(), container, false);
        ((FrameLayout)mParentGroup.findViewById(R.id.layout_group)).addView(mContentGroup);
        ((FrameLayout)mParentGroup.findViewById(R.id.layout_group)).addView(mLoadGroup);
        return mParentGroup;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        initView(savedInstanceState);
        if (statusBarEnabled()){
            if (StatusBarUtil.canStatusChangeColor()) {
                StatusBarUtil.setTranslucentForImageView(getActivity(), 0, null, true);
                StatusBarUtil.setStatusContentColor(getActivity(),true);
            } else {
                StatusBarUtil.setTranslucentForImageView(getActivity(), 40, null);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            initStatusBar(DeviceUtil.getStatusBarHeight(getActivity()));
        }
        initData(savedInstanceState);
        initEvent();
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        mArgs = new Bundle(args);
    }

    public Bundle getArgs() {
        return mArgs;
    }

    protected CompositeDisposable getCompositeDisposable(){
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()){
            synchronized (this){
                if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()){
                    mCompositeDisposable = new CompositeDisposable();
                }
            }
        }
        return mCompositeDisposable;
    }

    protected <T extends View> T findViewById(@IdRes int id){
        return mView.findViewById(id);
    }

    /**
     * 点击事件
     */
    @NonNull
    protected void setDefaultClick(@NonNull View view, Consumer<Object> consumer) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxView.clicks(view).subscribe(consumer));
    }

    /**
     * 带防抖动点击事件
     * 不用设置值，用了自己的默认值
     *
     * @param view     控件
     * @param consumer 回调事件
     */
    @NonNull
    protected void setClick(@NonNull View view, Consumer<Object> consumer) {
        setClick(view, DEFAULT_INTERVAL, consumer);
    }


    /**
     * 带防抖动点击事件
     * 需要设置点击有效事件范围，默认事件单位是秒
     *
     * @param throttle 点击有效事件，在此事件内点击只实现第一次
     */
    @NonNull
    protected void setClick(@NonNull View view, int throttle, Consumer<Object> consumer) {
        setClick(view, throttle, TimeUnit.SECONDS, consumer);
    }

    /**
     * 带防抖动点击事件
     * 需要设置点击有效事件范围、时间单位
     *
     * @param unit 时间单位
     */
    @NonNull
    protected void setClick(@NonNull View view, int throttle, TimeUnit unit, Consumer<Object> consumer) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxView.clicks(view).throttleFirst(throttle, unit).subscribe(consumer));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            if (!mCompositeDisposable.isDisposed()) {
                mCompositeDisposable.dispose();
            }
            mCompositeDisposable = null;
        }
        if (mPresenter != null){
            mPresenter.detachView();
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    protected void showLoad(){
        if (mLoadGroup == null){
            synchronized (this){
                if (mLoadGroup == null){
                    mLoadGroup = getLayoutInflater().inflate(R.layout.layout_loading,null);
                    ((FrameLayout)mParentGroup.findViewById(R.id.layout_group)).addView(mLoadGroup);
                }
            }
        }
        mLoadGroup.setVisibility(View.VISIBLE);
    }

    protected void hideLoad(){
        if (mLoadGroup != null && mLoadGroup.getVisibility() == View.VISIBLE){
            mLoadGroup.setVisibility(View.GONE);
        }
    }

    protected boolean isLoading(){
        return mLoadGroup != null && mLoadGroup.getVisibility() == View.VISIBLE;
    }


    /**
     * 是否需要设置状态栏
     * @return boolean
     */
    protected boolean statusBarEnabled(){
        return false;
    }

    /**
     * 设置ContentView
     *
     * @return 布局ID
     */
    protected abstract int contentView();

    /**
     * createPresenter
     * @return P
     */
    protected abstract P createPresenter();

    /**
     * 界面设置
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 状态栏设置
     * @param statusHeight 状态栏高度
     */
    protected abstract void initStatusBar(int statusHeight);

    /**
     * 事件监听
     */
    protected abstract void initEvent();

    /**
     * 数据处理
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

}
