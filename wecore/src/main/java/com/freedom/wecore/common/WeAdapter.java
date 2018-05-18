package com.freedom.wecore.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.freedom.wecore.event.OnSelectListener;
import com.freedom.wecore.tools.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 1-May-18.
 *
 */
@SuppressWarnings({"unused"})
public abstract class WeAdapter<T> extends RecyclerView.Adapter<WeHolder> {

    protected List<T> datas = new ArrayList<>();
    private int layoutId;
    private Context mContext;
    private OnSelectListener<T> onSelectListener;
    private Activity mActivity;
    private Fragment mFragment;
    private int mDataPage;

    public WeAdapter(int layoutId, Context context, List<T> datas) {
        this.layoutId = layoutId;
        this.mContext = context;
        LogUtil.v("Adapter", this.getClass().getSimpleName());
        if (mContext instanceof Activity) {
            this.mActivity = (Activity) mContext;
        }
        if (datas != null && !datas.isEmpty()) {
            this.datas.addAll(datas);
        }
    }

    public WeAdapter(int layoutId, Fragment fragment, List<T> datas) {
        this(layoutId, fragment.getContext(), datas);
        this.mFragment = fragment;
    }


    public void setOnSelectListener(OnSelectListener<T> onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public void onCallBack(List<T> datas, T item, int position, int type) {
        if (onSelectListener != null) {
            onSelectListener.onOperation(datas, item, position, type);
        }
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public WeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeHolder viewHolders = new WeHolder(layoutId, parent, mContext);
        if (mActivity != null) {
            viewHolders.setActivity(mActivity);
        } else if (mFragment != null) {
            viewHolders.setFragment(mFragment);
        }
        onPreCreate(viewHolders, viewType);
        return viewHolders;
    }

    protected void onPreCreate(WeHolder viewHolders, int viewType) {
    }


    @Override
    public final void onBindViewHolder(WeHolder holder, int position) {
        holder.setPosition(position);
        convert(holder, position, holder.getConvertView(), datas.get(position));
    }

    protected abstract void convert(WeHolder holder, int position, View convertView, T data);

    public int getDataPage(){
        return mDataPage;
    }

    public void setDataPage(int dataPage){
        this.mDataPage = dataPage;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void addDatas(List<T> datas) {
        if (datas != null && !datas.isEmpty()) {
            if (this.datas == null) {
                this.datas = new ArrayList<>();
            }
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void addData(T data) {
        if (data != null) {
            if (this.datas == null) {
                this.datas = new ArrayList<>();
            }
            this.datas.add(data);
            notifyDataSetChanged();
        }
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void clearDatas() {
        if (this.datas != null && !this.datas.isEmpty()) {
            this.datas.clear();
        }

        if (this.datas == null) {
            this.datas = new ArrayList<>();
        }
    }

    public String getString(@StringRes int stringId) {
        return getContext().getString(stringId);
    }

    public String getString(@StringRes int stringId, Object... formatArgs) {
        return getContext().getString(stringId, formatArgs);
    }

    public T getItem(int position) {
        return datas.get(position);
    }


    public void startActivity(Intent intent) {
        getContext().startActivity(intent);
    }
}
