package com.freedom.wehub.adp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.wecore.bean.Repository;
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
        return 6;
//        return data == null ? 0 : Math.min(data.size(),6);
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pro_repo, null);
//        TextView nameView = view.findViewById(R.id.tv_name);
//        TextView descriptionView = view.findViewById(R.id.tv_description);
//
//        nameView.setText(data.get(position).getName());
//        descriptionView.setText(TextUtils.isEmpty(data.get(position).getDescription()) ? "":
//                data.get(position).getDescription());
        container.addView(view);
        return view;
    }
}
