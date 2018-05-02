package com.freedom.wecore.common;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.freedom.wecore.R;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.tools.FragmentHolder;
import com.freedom.wecore.tools.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * @author vurtne on 1-May-18.
 *
 */
public abstract class WeActivity<V extends IWeContract.View,P extends WePresenter> extends AppCompatActivity {

    /**
     * 默认点击沉默时间
     * */
    private final int DEFAULT_INTERVAL = 1;

    private CompositeDisposable mCompositeDisposable;
    protected Context context;
    protected P mPresenter;
    private View mLoadGroup;
    private View mContentGroup;
    private FrameLayout mParentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentGroup = new FrameLayout(this);
        mContentGroup = getLayoutInflater().inflate(contentView(),null);
        mLoadGroup = getLayoutInflater().inflate(R.layout.layout_loading,null);
        mLoadGroup.setVisibility(View.GONE);
        mParentGroup.addView(mContentGroup);
        mParentGroup.addView(mLoadGroup);
        setContentView(mParentGroup);
        context = this;
        createPresenters();
        initView(savedInstanceState);
        if (StatusBarUtil.canStatusChangeColor()) {
            StatusBarUtil.setStatusContentColor(this,true);
            StatusBarUtil.setTranslucentForImageView(this, 0, null, true);
        } else {
            StatusBarUtil.setTranslucentForImageView(this, 40, null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            initStatusBar(DeviceUtil.getStatusBarHeight(this));
        }
        initEvent();
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
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

    protected CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()){
            synchronized (this){
                if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()){
                    mCompositeDisposable = new CompositeDisposable();
                }
            }
        }
        return mCompositeDisposable;
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

    protected Fragment showFragment(String fragmentClass, String fragmentTag, Bundle args, int parentLayoutId) {
        return FragmentHolder.showFragment(context, fragmentClass, fragmentTag, args, parentLayoutId, getSupportFragmentManager());
    }

    protected void showLoad(){
        if (mLoadGroup == null){
            synchronized (this){
                if (mLoadGroup == null){
                    mLoadGroup = getLayoutInflater().inflate(R.layout.layout_loading,null);
                    mParentGroup.addView(mLoadGroup);
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

    /**
     * 如果跳转就直接finish 掉的话，动画有点尴尬，又没有动画监听，就只能随便来个timer了
     * */
    protected void onFinish(){
        getCompositeDisposable().add(Flowable.timer(1000, TimeUnit.MILLISECONDS).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                if (!isFinishing()){
                    finish();
                }
            }
        }));
    }

    private void createPresenters() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mPresenter != null && !mPresenter.isViewAttached()) {
            mPresenter.attachView((V) this);
            mPresenter.initial(this);
        }
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
