package com.freedom.wecore.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author vurtne on 27-May-18.
 */

public class PagerContainer extends FrameLayout {
    private ViewPager mViewPager;

    public PagerContainer(Context context) {
        this(context, null);
    }

    public PagerContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData();
    }

    private void initData() {
        setClipChildren(false);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try {
            View view;
            for (int i=0;i<getChildCount();i++){
                view = getChildAt(i);
                if (ViewPager.class.isInstance(view)){
                    mViewPager = (ViewPager) getChildAt(i);
                    break;
                }
            }

        } catch (Exception e) {
            throw new IllegalStateException("The root child of PagerContainer must be a ViewPager");
        }
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}
