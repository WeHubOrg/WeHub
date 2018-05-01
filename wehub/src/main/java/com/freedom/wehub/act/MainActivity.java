package com.freedom.wehub.act;

import android.os.Bundle;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wehub.R;

/**
 * @author vurtne on 1-May-18.
 */

public class MainActivity extends WeActivity {
    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected WePresenter createPresenter() {
        return null;
    }
}
