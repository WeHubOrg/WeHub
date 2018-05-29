package com.freedom.wehub.adp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.freedom.wecore.bean.User;
import com.freedom.wecore.common.Key;
import com.freedom.wehub.ui.fragment.profile.ProfileOverviewFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vurtne on 28-May-18.
 */
public class ProfileContentAdapter extends FragmentPagerAdapter {

    private final String[] mTitles = {"Overview", "Repositories", "Stars", "Followers", "Following"};
    private final Map<String,Fragment> mFragments = new HashMap<>();
    private final User mUser;

    public ProfileContentAdapter(FragmentManager fm,User user) {
        super(fm);
        this.mUser = user;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(mTitles[position]);
        Bundle bundle;
        if (fragment == null){
            fragment = new ProfileOverviewFragment();
            bundle = new Bundle();
            bundle.putParcelable(Key.USER,mUser);
            fragment.setArguments(bundle);
            mFragments.put(mTitles[position],fragment);
        }
        return fragment;
    }


}
