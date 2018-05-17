package com.freedom.wecore.widget.refresh;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.freedom.wecore.R;
import com.freedom.wecore.widget.node.NodeView;
import com.freedom.wecore.widget.refresh.api.RefreshHeader;
import com.freedom.wecore.widget.refresh.api.RefreshKernel;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.constant.RefreshState;
import com.freedom.wecore.widget.refresh.constant.SpinnerStyle;
import com.freedom.wecore.widget.refresh.internal.InternalAbstract;


/**
 * @author vurtne on 15-May-18.
 */
public class WeFooter extends InternalAbstract implements RefreshHeader {

    private Context context;
    private View mHeaderLayout;
    private NodeView mLoadView;
    protected RefreshKernel mRefreshKernel;

    protected int mPaddingTop = 18;
    protected int mPaddingBottom = 18;

    public WeFooter(Context context) {
        this(context, null);
    }

    public WeFooter(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected WeFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void init(){
        LayoutInflater.from(context).inflate(R.layout.layout_loading_alone, this, true);
        mLoadView = findViewById(R.id.layout_load);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final View thisView = this;
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            thisView.setPadding(thisView.getPaddingLeft(), 0, thisView.getPaddingRight(), 0);
        } else {
            thisView.setPadding(thisView.getPaddingLeft(), mPaddingTop, thisView.getPaddingRight(), mPaddingBottom);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState,
                               @NonNull RefreshState newState) {
        switch (newState) {
            case None:
                mLoadView.reSet();
                break;
            case PullDownToRefresh:
                break;
            case Refreshing:
            case RefreshReleased:

                break;
            case ReleaseToRefresh:
                break;
            default:break;
        }
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        mRefreshKernel = kernel;
        mRefreshKernel.requestDrawBackgroundFor(this, Color.WHITE);
    }


    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int headHeight, int maxDragHeight) {
        mLoadView.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        mLoadView.finish();
        return 500;
    }


}
