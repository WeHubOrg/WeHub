package com.freedom.wehub.adp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.wecore.bean.Repository;
import com.freedom.wecore.tools.LanguageUtil;
import com.freedom.wehub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vurtne on 24-May-18.
 */
public class ProfileRepAdapter extends PagerAdapter {

    private List<Repository> data = new ArrayList<>();
    private Context context;


    public ProfileRepAdapter(Context context,List<Repository> data) {
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data == null ? 0 : Math.min(data.size(),6);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "asdasd";
    }
//    @Override
//    public float getPageWidth(int position) {
//        return 0.9f;
//    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Repository bean = data.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_pro_repo, null);
//        if (bean == null){
//            return view;
//        }
        TextView nameView = view.findViewById(R.id.tv_name);
        TextView descriptionView = view.findViewById(R.id.tv_description);
        ImageView languageIV = view.findViewById(R.id.iv_language);
        TextView languageTV = view.findViewById(R.id.tv_language);
        TextView starTv = view.findViewById(R.id.tv_star);
        TextView forkTv = view.findViewById(R.id.tv_fork);

        nameView.setText(bean.getName());
        descriptionView.setText(TextUtils.isEmpty(bean.getDescription()) ? "": bean.getDescription());
        languageTV.setText(bean.getLanguage());
        languageIV.getBackground().setColorFilter(ContextCompat.getColor(context,
                LanguageUtil.switchLanguageColor(bean.getLanguage())), PorterDuff.Mode.SRC_ATOP);
        starTv.setText(String.valueOf(bean.getStargazersCount()));
        forkTv.setText(String.valueOf(bean.getForksCount()));

        container.addView(view);
        return view;
    }
}
