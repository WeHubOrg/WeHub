package com.freedom.wehub.ui.fragment;

import  android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freedom.wecore.bean.Repository;
import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wecore.common.WeFragment;
import com.freedom.wecore.tools.ImageBridge;
import com.freedom.wecore.tools.RxBus;
import com.freedom.wecore.widget.transformer.WeTransformer;
import com.freedom.wehub.R;
import com.freedom.wehub.adp.ProfileRepAdapter;
import com.freedom.wehub.contract.AccountContract;
import com.freedom.wehub.event.FragmentVisibleEvent;
import com.freedom.wehub.presenter.AccountPresenter;

import java.util.List;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

/**
 * @author vurtne on 18-May-18.
 */
public class ProfileFragment extends WeFragment<AccountContract.IProfilerView, AccountPresenter> implements
        AccountContract.IProfilerView{

    private Toolbar mToolbar;
    private ImageView mAvatarView;
    private TextView mUserTv;
    private TextView mNameTv;
    private TextView mRepositoriesTv;
    private TextView mFollowersTv;
    private TextView mFollowingTv;
    private TextView mGistsTv;
    private ProgressBar mProProgress;
    private ViewPager mRepoVp;


    private ProfileRepAdapter mRepoAdapter;
    private User mUser;




    @Override
    protected int contentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected AccountPresenter createPresenter() {
        return new AccountPresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.toolbar);
        mAvatarView = findViewById(R.id.iv_avatar);
        mUserTv = findViewById(R.id.tv_user);
        mNameTv = findViewById(R.id.tv_name);
        mRepositoriesTv = findViewById(R.id.tv_repositories);
        mGistsTv = findViewById(R.id.tv_gists);
        mFollowersTv = findViewById(R.id.tv_followers);
        mFollowingTv = findViewById(R.id.tv_following);
        mProProgress = findViewById(R.id.progress_pro);
        mRepoVp = findViewById(R.id.vp_repo);

//        mAvatarBackgroundView = (ImageView) findViewById(R.id.iv_avatar_bg);
    }

    @Override
    protected void initStatusBar(int statusHeight) {
        FrameLayout.LayoutParams toolParams = (FrameLayout.LayoutParams) mToolbar.getLayoutParams();
        toolParams.height += statusHeight / 1.5;
        mToolbar.setPadding(0, (int) (statusHeight / 1.5),0,0);
        mToolbar.setLayoutParams(toolParams);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArgs();
        if (bundle == null){
            return;
        }
        String user = bundle.getString(Key.USER);
        if (TextUtils.isEmpty(user)){
            return;
        }
        showLoad();
        mPresenter.requestPersonInfo(user);
    }

    @Override
    public void requestPerson(User user) {
        hideLoad();
        mProProgress.setVisibility(View.VISIBLE);
        mUser = user;
        if (mUser == null){
            return;
        }
        mPresenter.requestUserRepositories(mUser.getLogin(),1,"","","");
        RxBus.get().post(FragmentVisibleEvent.create(mToolbar));
        ImageBridge.displayRoundImageWithDefault(mUser.getAvatarUrl(), mAvatarView,R.drawable.ic_hub_round);
        mUserTv.setText(mUser.getLogin());
        mNameTv.setText(mUser.getName());
        mFollowersTv.setText(String.valueOf(mUser.getFollowers()));
        mFollowingTv.setText(String.valueOf(mUser.getFollowing()));
        mRepositoriesTv.setText(String.valueOf(user.getPublicRepos()));
        mGistsTv.setText(String.valueOf(user.getPublicGists()));
    }

    @Override
    public void requestRepositories(List<Repository> ropes) {
        mProProgress.setVisibility(View.GONE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (getResources().getDisplayMetrics().widthPixels * 0.8f),
                (int) (getResources().getDisplayMetrics().widthPixels * 0.8f));
        layoutParams.addRule(CENTER_IN_PARENT);
        mRepoVp.setLayoutParams(layoutParams);
        mRepoAdapter = new ProfileRepAdapter(getContext(),ropes);
        mRepoVp.setAdapter(mRepoAdapter);
        mRepoVp.setPageTransformer(true, new WeTransformer());
        mRepoVp.setOffscreenPageLimit(Math.min(6,ropes.size()));
    }


}
