package com.freedom.wecore.tools;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.freedom.wecore.common.WeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 2-May-18.
 */
public class FragmentHolder {

    private static List<String> sFragmentTag = new ArrayList<>();

    public static Fragment showFragment(Context context, String clazz, String tag, Bundle args, int parentLayoutId, FragmentManager manager){
        Fragment fragment = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment == null){
            if (args == null){
                args = new Bundle();
            }
            fragment = Fragment.instantiate(context,clazz,args);
            hideAllFragment(transaction,manager);
            transaction.add(parentLayoutId,fragment,tag);
            sFragmentTag.add(tag);
        }else {
            if (fragment instanceof WeFragment){
                ((WeFragment)fragment).setArguments(args);
            }
            hideAllFragment(transaction,manager);
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
        return fragment;
    }

    private static void hideAllFragment(FragmentTransaction transaction,FragmentManager manager){
        for (String tag:sFragmentTag){
            Fragment fragment = manager.findFragmentByTag(tag);
            if (fragment != null){
                transaction.hide(fragment);
            }
        }
    }
}
