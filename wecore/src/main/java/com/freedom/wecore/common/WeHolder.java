package com.freedom.wecore.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.wecore.tools.DeviceUtil;
import com.freedom.wecore.tools.ImageBridge;

public class WeHolder extends RecyclerView.ViewHolder{
    /***
     * 用来存放所使用的控件
     */
    private SparseArray<View> mViewSparse = new SparseArray<>();

    /**
     * 父布局
     */
    private View mParentView;
    private Context mContext;
    private int position;
    private int type;
    private Activity activity;
    private Fragment fragment;

    public WeHolder(View itemView){
        super(itemView);
    }

    public WeHolder(int Layoutid, Context context) {
        this(LayoutInflater.from(context).inflate(Layoutid, null), context);
        mParentView.setTag(this);
    }

    public WeHolder(int Layoutid, ViewGroup parentGroup, Context context) {
        this(LayoutInflater.from(context).inflate(Layoutid, parentGroup, false), context);
        mParentView.setTag(this);
    }

    public WeHolder(View mParentView, Context mContext) {
        super(mParentView);
        this.mParentView = mParentView;
        this.mContext = mContext;
        mParentView.setTag(this);
    }

    public void setActivity(Activity activity){
        this.activity=activity;
    }

    public void setFragment(Fragment fragment){
        this.fragment=fragment;
    }

    public Context getmContext() {
        return mContext;
    }

    public View getmParentView() {
        return mParentView;
    }

    public View getConvertView() {
        return mParentView;
    }

    /**
     * 寻找控件,并添加到集合中去,保证实例化只有一次
     **/
    public <E extends View> E findView(int viewid) {
        View mChild = mViewSparse.get(viewid);
        if (mChild == null) {
            mChild = mParentView.findViewById(viewid);
            mViewSparse.put(viewid, mChild);
        }
        E view = null;
        try {
            view = (E) mChild;
            return view;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WeHolder setText(@IdRes int viewID, String status) {
        TextView textView = findView(viewID);
        if (textView != null) {
            textView.setText(status);
        }
        return this;
    }

    public WeHolder setText(@IdRes int viewID, @StringRes int stringRes) {
        TextView textView = findView(viewID);
        if (textView != null) {
            textView.setText(stringRes);
        }
        return this;
    }

    public WeHolder setTextColor(@IdRes int viewID, @ColorRes int color) {
        TextView textView = findView(viewID);
        if (textView != null) {
            textView.setTextColor(getmContext().getResources().getColor(color));
        }
        return this;
    }

    public WeHolder setText(@IdRes int viewID, CharSequence text) {
        TextView textView = findView(viewID);
        if (textView != null) {
            textView.setText(text, TextView.BufferType.SPANNABLE);
        }
        return this;
    }

    public WeHolder setText(@IdRes int viewID, Spanned status) {
        TextView textView = findView(viewID);
        if (textView != null) {
            textView.setText(status);
        }
        return this;
    }

    public WeHolder setCompoundDrawables(@IdRes int viewID, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        TextView textView = findView(viewID);
        if (textView != null) {
            textView.setCompoundDrawables(left, top, right, bottom);
        }
        return this;
    }

    /**
     * @param convertView
     */
    public WeHolder setText(View convertView, String text) {
        if (convertView instanceof TextView) {
            ((TextView) convertView).setText(text);
        }
        return this;
    }

    public WeHolder setImage(int imgView, Bitmap bitmap) {
        ImageView imageVIew = findView(imgView);
        imageVIew.setImageBitmap(bitmap);
        return this;
    }

    public WeHolder setImage(@IdRes int imgView, @DrawableRes int drawableid) {
        ImageView imageVIew = findView(imgView);
        if (drawableid != 0) {
            imageVIew.setImageDrawable(mContext.getResources().getDrawable(drawableid));
        }
        return this;
    }

    public WeHolder setVisibility(boolean isVisible, @IdRes int... ids) {
        for (int i = 0; i < ids.length; i++) {
            findView(ids[i]).setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public WeHolder setVisible(boolean isVisible, @IdRes int... ids) {
        for (int i = 0; i < ids.length; i++) {
            findView(ids[i]).setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        }
        return this;
    }

    public WeHolder setVisibility(boolean isVisible, View... views) {
        for (int i = 0; i < views.length; i++) {
            views[i].setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public WeHolder setVisibile(boolean isVisible, View... views) {
        for (int i = 0; i < views.length; i++) {
            views[i].setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        }
        return this;
    }

    public WeHolder setVisibile(boolean isVisible, int... views) {
        for (int i = 0; i < views.length; i++) {
            findView(views[i]).setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        }
        return this;
    }

    public WeHolder updatePosition(int position) {
        this.position = position;
        return this;
    }

    public WeHolder setPosition(int position) {
        this.position = position;
        return this;
    }

    public int getPostion() {
        return position;
    }

    public WeHolder displayImage(@IdRes int viewID, String url) {
        ImageView imageView = findView(viewID);
        if (activity!=null){
            ImageBridge.displayImage(activity,url, imageView);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImage(fragment,url, imageView);
            return this;
        }
        ImageBridge.displayImage(url, imageView);
        return this;
    }

    public WeHolder displayRoundImageWithDefault(@IdRes int viewID, String url, int defaultDrwable) {
        ImageView imageView = findView(viewID);
        displayRoundImageWithDefault(url, imageView, defaultDrwable);
        return this;
    }

    public WeHolder displayImage(@IdRes int viewID, String url, int defaultDrwable) {
        ImageView imageView = findView(viewID);
        if (activity!=null){
            ImageBridge.displayImage(activity,url, imageView, defaultDrwable);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImage(fragment,url, imageView, defaultDrwable);
            return this;
        }
        ImageBridge.displayImage(url, imageView, defaultDrwable);
        return this;
    }

    public WeHolder displayImageNoAnimate(@IdRes int viewID, String url, int defaultDrwable) {
        ImageView imageView = findView(viewID);
        displayImageNoAnimate(url, imageView, defaultDrwable);
        return this;
    }

    public WeHolder displayImage(ImageView mImageView, String url) {
        if (activity!=null){
            ImageBridge.displayImage(activity,url, mImageView);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImage(fragment,url, mImageView);
            return this;
        }
        ImageBridge.displayImage(url, mImageView);
        return this;
    }

    public WeHolder displayImage(ImageView mImageView, String url, int defaultId) {
        if (activity!=null){
            ImageBridge.displayImage(activity,url, mImageView, defaultId);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImage(fragment,url, mImageView, defaultId);
            return this;
        }
        ImageBridge.displayImage(url, mImageView, defaultId);
        return this;
    }

    public WeHolder displayRoundImageWithDefault(String uri, ImageView imageView, int defaultResId) {
        if (activity!=null){
            ImageBridge.displayRoundImageWithDefault(activity,uri, imageView, defaultResId);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayRoundImageWithDefault(fragment,uri, imageView, defaultResId);
            return this;
        }
        ImageBridge.displayRoundImageWithDefault(uri, imageView, defaultResId);
        return this;
    }

    public WeHolder displayImageNoAnimate(String uri, ImageView imageView, int defaultResId) {
        if (activity!=null){
            ImageBridge.displayImageNoAnimate(activity,uri, imageView, defaultResId);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImageNoAnimate(fragment,uri, imageView, defaultResId);
            return this;
        }
        ImageBridge.displayImageNoAnimate(uri, imageView, defaultResId);
        return this;
    }

    public WeHolder displayRoundImage(ImageView mImageView, String url, int radius) {
        if (activity!=null){
            ImageBridge.displayRoundImage(url, mImageView, DeviceUtil.dip2Px(getmContext(), radius));
            return this;
        }else if(fragment!=null){
            ImageBridge.displayRoundImage(url, mImageView, DeviceUtil.dip2Px(getmContext(), radius));
            return this;
        }
        ImageBridge.displayRoundImage(url, mImageView, DeviceUtil.dip2Px(getmContext(), radius));
        return this;
    }


    public WeHolder displayRoundImage(@IdRes int viewID, String url, int radius) {
        if (activity!=null){
            ImageBridge.displayRoundImage(url, (ImageView) findView(viewID), DeviceUtil.dip2Px(getmContext(), radius));
            return this;
        }else if(fragment!=null){
            ImageBridge.displayRoundImage(url, (ImageView) findView(viewID), DeviceUtil.dip2Px(getmContext(), radius));
            return this;
        }
        ImageBridge.displayRoundImage(url, (ImageView) findView(viewID), DeviceUtil.dip2Px(getmContext(), radius));
        return this;
    }

    public WeHolder displayImageScaleType(ImageView mImageView, String url, float scale) {
        if (activity!=null){
            ImageBridge.displayImageScaleType(activity,url, mImageView, scale);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImageScaleType(fragment,url, mImageView, scale);
            return this;
        }
        ImageBridge.displayImageScaleType(url, mImageView, scale);
        return this;
    }

    /**
     * 两倍大小展示
     */
    public WeHolder displayImageScaleType(@IdRes int viewID, String url, float scale) {
        if (activity!=null){
            ImageBridge.displayImageScaleType(activity,url, (ImageView) findView(viewID), scale);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImageScaleType(fragment,url, (ImageView) findView(viewID), scale);
            return this;
        }
        ImageBridge.displayImageScaleType(url, (ImageView) findView(viewID), scale);
        return this;
    }

    public WeHolder displayImageKeepHegiht(@IdRes int viewID, String url) {
        if (activity!=null){
            ImageBridge.displayImageKeepHeight(activity,url, (ImageView) findView(viewID));
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImageKeepHeight(fragment,url, (ImageView) findView(viewID));
            return this;
        }
        ImageBridge.displayImageKeepHeight(url, (ImageView) findView(viewID));
        return this;
    }

    public WeHolder displayImageKeepHegiht(@IdRes int viewID, String url, int height) {
        if (activity!=null){
            ImageBridge.displayImageKeepHeight(activity,url, (ImageView) findView(viewID), height);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayImageKeepHeight(fragment,url, (ImageView) findView(viewID), height);
            return this;
        }
        ImageBridge.displayImageKeepHeight(url, (ImageView) findView(viewID), height);
        return this;
    }

    public WeHolder displayRoundImage(ImageView imageView, String uri, int roundRadius, int defaultResId) {
        if (activity!=null){
            ImageBridge.displayRoundImage(activity,uri, imageView, roundRadius, defaultResId);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayRoundImage(fragment,uri, imageView, roundRadius, defaultResId);
            return this;
        }
        ImageBridge.displayRoundImage(uri, imageView, roundRadius, defaultResId);
        return this;
    }

    public WeHolder displayRoundImage(@IdRes int viewID, String uri, int roundRadius, int defaultResId) {
        return displayRoundImage((ImageView) findView(viewID), uri, DeviceUtil.dip2Px(getmContext(), roundRadius), defaultResId);
    }

    public WeHolder displayOvalImage(ImageView mImageVIew, String uri, int defaultResId) {
        if (activity!=null){
            ImageBridge.displayOvalImage(activity,uri, mImageVIew, defaultResId);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayOvalImage(fragment,uri, mImageVIew, defaultResId);
            return this;
        }
        ImageBridge.displayOvalImage(uri, mImageVIew, defaultResId);
        return this;
    }

    public WeHolder displayOvalImage(@IdRes int viewID, String uri, int defaultResId) {
        return displayOvalImage((ImageView) findView(viewID), uri, defaultResId);
    }

    public WeHolder displayRoundImage(@IdRes int viewID, String url) {
        ImageView imageView = findView(viewID);
        if (activity!=null){
            ImageBridge.displayRoundImage(activity,url, imageView);
            return this;
        }else if(fragment!=null){
            ImageBridge.displayRoundImage(fragment,url, imageView);
            return this;
        }
        ImageBridge.displayRoundImage(url, imageView);
        return this;
    }

    public WeHolder setBackgroundResource(@IdRes int viewID, @DrawableRes int colorRes) {
        return setBackgroundResource(findView(viewID), colorRes);
    }

    public WeHolder setBackgroundResource(@IdRes int viewID, Drawable mDrawable) {
        return setBackgroundResource(findView(viewID), mDrawable);
    }

    public WeHolder setBackgroundResource(View viewID, @DrawableRes int colorRes) {
        viewID.setBackgroundResource(colorRes);
        return this;
    }

    public WeHolder setBackgroundResource(View viewID, Drawable mDrawable) {
        viewID.setBackgroundDrawable(mDrawable);
        return this;
    }

    public WeHolder setOnClickListener(View.OnClickListener listener, int... viewIds) {
        for (int i = 0; i < viewIds.length; i++) {
            View mView = findView(viewIds[i]);
            if (mView != null) {
                mView.setOnClickListener(listener);
            }
        }
        return this;
    }

    public WeHolder setTag(@IdRes int viewID, Object tag) {
        findView(viewID).setTag(tag);
        return this;
    }

    public WeHolder setTag(Object tag, int... viewIDs) {
        for (int i = 0; i < viewIDs.length; i++) {
            findView(viewIDs[i]).setTag(tag);
        }
        return this;
    }

    /**
     * id作为key，来设置值
     */
    public WeHolder setIdTag(Object tag, int... viewIDs) {
        for (int i = 0; i < viewIDs.length; i++) {
            findView(viewIDs[i]).setTag(viewIDs[i], tag);
        }
        return this;
    }

    public WeHolder setTag(@IdRes int viewID, @IdRes @LayoutRes int key, Object tag) {
        findView(viewID).setTag(key, tag);
        return this;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public WeHolder setSelect(@IdRes int viewId, boolean concern) {
        findView(viewId).setSelected(concern);
        return this;
    }

    public void setEnable(boolean isEnable, int... viewIDs) {
        for (int i = 0; i < viewIDs.length; i++) {
            findView(viewIDs[i]).setEnabled(isEnable);
        }

    }

    public WeHolder setBackgroundDrawable(int tv_tag, Drawable drawable) {
        findView(tv_tag).setBackgroundDrawable(drawable);
        return this;
    }

    public WeHolder setImageDrawable(int tv_tag, Drawable drawable) {
        ImageView mImageView = findView(tv_tag);
        mImageView.setImageDrawable(drawable);
        return this;
    }

    public WeHolder setClickAble(@IdRes int viewId, boolean b) {
        findView(viewId).setClickable(b);
        return this;
    }

    public WeHolder setImageResource(@IdRes int imageId, @DrawableRes int drawableRes) {
        ImageView mImageView = findView(imageId);
        if (mImageView != null) {
            mImageView.setImageResource(drawableRes);
        }

        return this;
    }

    public WeHolder setLayoutParams(@IdRes int viewId,ViewGroup.LayoutParams params){
        if (params == null){
            return this;
        }
        View view  = findView(viewId);
        if (view != null){
            view.setLayoutParams(params);
        }
        return this;
    }



}
