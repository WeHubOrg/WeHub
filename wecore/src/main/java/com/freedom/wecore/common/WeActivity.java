package com.freedom.wecore.common;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.tools.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * @author vurtne on 29-Apr-18.
 *
 */
public abstract class WeActivity<P extends IWeContract.Presenter> extends AppCompatActivity {

    /**
     * 默认点击沉默时间
     * */
    private final int DEFAULT_INTERVAL = 1;

    private CompositeDisposable mCompositeDisposable;
    protected Context context;
    protected P mPresenter;

    private View mLoadView;
    private FrameLayout mParentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mParentView = new FrameLayout(this);
//        mParentView.addView(getLayoutInflater().inflate(contentView(),null));
//        setContentView(mParentView);
        setContentView(contentView());
        context = this;
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

//    protected Fragment showFragment(Context context, String fragmentClass, String fragmentTag, Bundle args, int parentLayoutId) {
//        return FragmentHolder.showFragment(context, fragmentClass, fragmentTag, args, parentLayoutId, getSupportFragmentManager());
//    }

    protected void showLoad(){
        if (mLoadView == null){
            synchronized (this){
                if (mLoadView == null){
//                    mLoadView = getLayoutInflater().inflate(R.layout.layout_loading,null);
                    mParentView.addView(mLoadView);
                }
            }
        }
        mLoadView.setVisibility(View.VISIBLE);
    }

    protected void hideLoad(){
        if (mLoadView != null && mLoadView.getVisibility() == View.VISIBLE){
            mLoadView.setVisibility(View.GONE);
        }
    }

    /**
     * 如果跳转就直接finish 掉的话，动画有点尴尬，又没有动画监听，就只能随便来个timer了
     * */
    protected void onFinish(){
        getCompositeDisposable().add(Flowable.timer(1000, TimeUnit.MILLISECONDS).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                if (!isFinishing()){
                    finish();
                }
            }
        }));
    }

    /**
     * 设置ContentView
     *
     * @return 布局ID
     */
    protected abstract int contentView();

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
     */
    protected abstract void initData(Bundle savedInstanceState);

}
