package com.freedom.wehub.event;

import android.support.v7.widget.Toolbar;

import com.freedom.wecore.event.IEvent;

/**
 * @author vurtne on 18-May-18.
 */
public class FragmentVisibleEvent implements IEvent {

    private Toolbar mToolbar;

    private FragmentVisibleEvent(Toolbar toolbar){
        this.mToolbar = toolbar;
    }

    public static FragmentVisibleEvent create(Toolbar toolbar){
        return new FragmentVisibleEvent(toolbar);
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.mToolbar = toolbar;
    }
}
