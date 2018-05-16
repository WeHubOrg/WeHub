package com.freedom.wecore.widget.refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.freedom.wecore.R;
import com.freedom.wecore.widget.node.NodeView;
import com.freedom.wecore.widget.refresh.api.RefreshHeader;
import com.freedom.wecore.widget.refresh.internal.InternalAbstract;

/**
 * @author vurtne on 15-May-18.
 */
public class WeHeader extends InternalAbstract implements RefreshHeader {

    private Context context;
    private View mHeaderLayout;
    private NodeView mLoadView;

    public WeHeader(Context context) {
        this(context, null);
    }

    public WeHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected WeHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void init(){
        mHeaderLayout = LayoutInflater.from(context).inflate(R.layout.layout_loading_alone, null);
        mLoadView = mHeaderLayout.findViewById(R.id.layout_load);
    }

}
