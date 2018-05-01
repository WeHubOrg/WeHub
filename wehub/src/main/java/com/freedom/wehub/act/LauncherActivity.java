package com.freedom.wehub.act;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.wecore.common.WeActivity;
import com.freedom.wecore.common.WePresenter;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.tools.TransitionHelper;
import com.freedom.wehub.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * @author vurtne on 29-Apr-18.
 *
 * */
public class LauncherActivity extends WeActivity {

    private TextView mLogoNameView;
    private ImageView mLogoView;

    @Override
    protected int contentView() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mLogoNameView = findViewById(R.id.tv_name);
        mLogoView = findViewById(R.id.iv_logo);
        DeviceUtil.setTypeface(this,mLogoNameView,"fonts/affiliation.ttf");
    }

    @Override
    protected WePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initStatusBar(int statusHeight) {

    }

    @Override
    protected void initEvent() {
        setClick(mLogoView, o -> initData(null));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getCompositeDisposable().add(Flowable.timer(1, TimeUnit.SECONDS).observeOn(
                AndroidSchedulers.mainThread()).subscribe(aLong -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(
                                LauncherActivity.this, false,
                                new Pair<>(mLogoView,getString(R.string.transition_logo))
                                ,new Pair<>(mLogoNameView,getString(R.string.transition_logo_name))
                        );
                        Intent intent  = new Intent(LauncherActivity.this,LoginActivity.class);
                        ActivityOptionsCompat options =
                                ActivityOptionsCompat.makeSceneTransitionAnimation(LauncherActivity.this, pairs);
                        ActivityCompat.startActivity(LauncherActivity.this.context,intent, options.toBundle());
                        onFinish();
                    }else {
                        startActivity(new Intent(LauncherActivity.this,LoginActivity.class));
                        finish();
                    }
                }));
    }


}
