package com.freedom.wecore.common;

import android.content.Context;

/**
 * @author vurtne on 1-May-18.
 */
@SuppressWarnings({"unused"})
public abstract class WePresenter<V extends IWeContract.View> implements IWeContract.Presenter<V>  {

    protected V mView;
    protected Context mContext;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        this.mContext = null;
    }

    public void initial(Context context) {
        this.mContext = context;
        init(context);
    }

    /**
     * 是否关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 一些初始化操作
     **/
    protected abstract void init(Context context);

    /**
     * 取消请求
     * 若是没有接口调用则非必须实现
     */
    public abstract void onDestroy();
}
