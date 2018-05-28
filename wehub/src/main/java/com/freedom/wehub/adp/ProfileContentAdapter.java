package com.freedom.wehub.adp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.freedom.wehub.ui.fragment.profile.ProfileOverviewFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vurtne on 28-May-18.
 */
public class ProfileContentAdapter extends FragmentPagerAdapter {

    private final String[] mTitles = {"Overview", "Repositories", "Stars", "Followers", "Following"};
    private final Map<String,Fragment> mFragments = new HashMap<>();

    public ProfileContentAdapter(FragmentManager fm) {
        super(fm);
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
        if (fragment == null){
            fragment = new ProfileOverviewFragment();
            mFragments.put(mTitles[position],fragment);
        }
        return fragment;
    }


}
