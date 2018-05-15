package com.freedom.wecore.widget.refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.freedom.wecore.widget.refresh.api.RefreshHeader;
import com.freedom.wecore.widget.refresh.internal.InternalAbstract;

public class WeHeader extends InternalAbstract implements RefreshHeader {


    public WeHeader(Context context) {
        this(context, null);
    }

    public WeHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected WeHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
