package com.freedom.wehub.adp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.WeAdapter;
import com.freedom.wecore.common.WeHolder;
import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.widget.PagerContainer;
import com.freedom.wecore.widget.transformer.ScaleInTransformer;
import com.freedom.wehub.R;
import com.freedom.wehub.model.ProfileChildAdapterModel;
import com.freedom.wehub.presenter.ProfilePresenter;

import java.util.List;

/**
 * @author vurtne on 1-Jun-18.
 */
public class ProfileChildAdapter extends WeAdapter<ProfileChildAdapterModel>{

    /** 流行知识库 */
    private final int POPULAR_REPOSITORIES = 0x0001;

    private static enum TypeEnum{
        Overview,Repositories,Stars,Followers,Following
    }

    /** 流行库是否加载完成 */
    private boolean isPopularRepositories;
    private TypeEnum type;
    private ProfilePresenter mPresenter;
    private User mUser;
    private ProfileRepAdapter mPopularRepositoriesAdapter;

    public ProfileChildAdapter(Context context, List<ProfileChildAdapterModel> datas,
                               ProfilePresenter presenter, User user,TypeEnum type) {
        super(context, datas, R.layout.item_events_child,R.layout.item_profile_child_over);
        this.mPresenter = presenter;
        this.mUser = user;
        this.type = type;
    }

    @Override
    protected void convert(WeHolder holder, int position, View convertView, ProfileChildAdapterModel data) {
        if (data == null){
            return;
        }
        if (type == TypeEnum.Overview){
            if (position == 0){
                setPopularRepositories(holder, position, convertView);
            }

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (type == TypeEnum.Overview){
            if (position == 0){
                return POPULAR_REPOSITORIES;
            }
        }
        return 0;
    }

    @Override
    protected int bindChildViewLayout(int[] layoutId, int viewType) {
        if (viewType == POPULAR_REPOSITORIES){
            return R.layout.item_profile_child_over;
        }
        return 0;
    }

    private void setPopularRepositories(WeHolder holder, int position, View convertView){
        PagerContainer pagerContainer = holder.findView(R.id.layout_container);
        ViewPager ropeView = holder.findView(R.id.vp_rope);
        if (isPopularRepositories){
            LinearLayout.LayoutParams rll = (LinearLayout.LayoutParams) pagerContainer.getLayoutParams();
            rll.width = (int) DeviceUtil.getScreenWidth((Activity) getContext());
            pagerContainer.setLayoutParams(rll);

            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) ropeView.getLayoutParams();
            lp.width = (int) (DeviceUtil.getScreenWidth((Activity) getContext()) / 1.4);
            ropeView.setLayoutParams(lp);

            ropeView.setOffscreenPageLimit(6);
            ropeView.setPageTransformer(true, new ScaleInTransformer());
            ropeView.setPageMargin(DeviceUtil.dip2Px(getContext(),12));
            mPresenter.requestUserRepositories(mUser.getLogin(),1,"","","");
            isPopularRepositories = true;
        }
    }

}
