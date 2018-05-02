package com.freedom.wecore.widget.refresh.listener;

import android.support.annotation.NonNull;

import com.freedom.wecore.widget.refresh.api.RefreshLayout;

/**
 * 刷新监听器
 * Created by SCWANG on 2017/5/26.
 */

public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
