package com.freedom.wecore.widget.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author vurtne on 15-May-18.
 */
public class HorizontalDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public HorizontalDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildPosition(view) != 0){
            outRect.top = space;
        }
    }
}
