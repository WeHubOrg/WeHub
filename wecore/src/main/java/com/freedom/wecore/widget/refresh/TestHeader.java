package com.freedom.wecore.widget.refresh;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedom.wecore.widget.refresh.api.RefreshHeader;
import com.freedom.wecore.widget.refresh.api.RefreshKernel;
import com.freedom.wecore.widget.refresh.api.RefreshLayout;
import com.freedom.wecore.widget.refresh.constant.RefreshState;
import com.freedom.wecore.widget.refresh.constant.SpinnerStyle;
import com.freedom.wecore.widget.refresh.util.DensityUtil;


public class TestHeader extends LinearLayout implements RefreshHeader {

    private Context context;
    private TextView mHeaderText;

    public TestHeader(Context context) {
        this(context,null);
    }

    public TestHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void init(){
        setGravity(Gravity.CENTER);
        mHeaderText = new TextView(context);
        mHeaderText.setText("我爱我到家奥 i 我到家啊我 i 哦 ");
        mHeaderText.setTextColor(Color.YELLOW);
        mHeaderText.setTextSize(23);
        addView(mHeaderText, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setMinimumHeight(DensityUtil.dp2px(60));
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

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
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
}
