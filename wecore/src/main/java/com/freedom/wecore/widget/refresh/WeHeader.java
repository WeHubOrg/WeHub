package com.freedom.wecore.widget.refresh;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
public class WeHeader extends InternalAbstract implements RefreshHeader {

    private Context context;
    private View mHeaderLayout;
    private NodeView mLoadView;
    protected RefreshKernel mRefreshKernel;

    protected int mPaddingTop = 20;
    protected int mPaddingBottom = 20;

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

    @NonNull
    @Override
    public View getView() {
        TextView textView = new TextView(context);
        textView.setText("我爱我到家奥 i 我到家啊我 i 哦 ");
        textView.setTextColor(Color.YELLOW);
        textView.setTextSize(23);
        return textView;
    }

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
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLoadView.finish();
    }
//
//    @Override
//    public void onReleased(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
//        mLoadView.start();
//    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState,
                               @NonNull RefreshState newState) {
        switch (newState) {
            case None:
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
        mRefreshKernel.requestDrawBackgroundFor(this, Color.RED);
//        if (this instanceof RefreshHeader) {
//            mRefreshKernel.requestDrawBackgroundForHeader(mBackgroundColor);
//        } else if (this instanceof RefreshFooter) {
//            mRefreshKernel.requestDrawBackgroundForFooter(mBackgroundColor);
//        }
    }


    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        if (success) {

        } else {

        }
        return super.onFinish(layout, success);
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int maxDragHeight) {
        mLoadView.start();
    }

}
