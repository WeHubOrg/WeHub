package com.freedom.wecore.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @author vurtne on 30-Apr-18.
 */
public class ImageBridge {


    public static final String DRAWABLEREFRENCES = "drawable://";

    /**
     * 加载图片
     */
    public static void displayImage(String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;//这句话还不能完全避免出现崩溃,只是减少了
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    private static Object getSecurityUrl(String uri) {
        Object urlRefrence = null;
        if (!TextUtils.isEmpty(uri) && uri.startsWith(DRAWABLEREFRENCES)) {
            int value = Integer.parseInt(uri.substring(DRAWABLEREFRENCES.length(), uri.length()));
            urlRefrence = value == 0 ? "" : value;
        } else {
            urlRefrence = uri;
        }
        return urlRefrence;
    }

    public static void displayImage(int uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }



    /**
     * 加载图片，失败则显示指定默认图片
     *
     * @param uri
     * @param imageView
     * @param defaultDrawable 默认图片资源id
     */
    public static void displayImage(String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getHolderOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            ImageBridge.shouldLoadImage(imageView);
        }
    }

    /**
     * 加载图片，失败则显示指定默认图片
     * dontAnimate()  去掉过度默认的加载动画，不然有时候会变形
     *
     * @param uri
     * @param imageView
     * @param defaultDrawable 默认图片资源id
     */
    public static void displayImageNoAnimate(String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getNoAnimateOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载图片，失败则显示指定默认图片
     * dontAnimate()  去掉过度默认的加载动画，不然有时候会变形
     *
     * @param uri
     * @param imageView
     * @param defaultDrawable 默认图片资源id
     */
    public static void displayImageNoAnimate(Activity activity, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getNoAnimateOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载图片，失败则显示指定默认图片
     * dontAnimate()  去掉过度默认的加载动画，不然有时候会变形
     *
     * @param uri
     * @param imageView
     * @param defaultDrawable 默认图片资源id
     */
    public static void displayImageNoAnimate(Fragment fragment, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getNoAnimateOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }

    /**
     * 加载图片，失败则显示指定默认图片
     *
     * @param uri
     * @param imageView
     * @param defaultDrawable 默认图片资源id
     */
    public static void displayImage(int uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getHolderOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载图片，失败则显示指定默认图片,自定义尺寸
     *
     * @param uri
     * @param imageView
     * @param defaultDrawable 默认图片资源id
     */
    public static void displayImage(String uri, ImageView imageView, @DrawableRes int defaultDrawable, int width, int high) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getOverriedOptions(defaultDrawable, width, high))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /***
     * 根据比例显示图片
     */
    public static void displayImageScaleType(String url, ImageView mImageView, final float scale) {
        try {
            if (ImageBridge.shouldLoadImage(mImageView)) {
                return;
            }
            Glide.with(mImageView.getContext())
                    .load(url)
                    .into(new ImageViewTarget<Drawable>(mImageView) {
                        @Override
                        protected void setResource(Drawable bitmap) {
                            if (bitmap == null) {
                                return;
                            }
                            ViewGroup.LayoutParams lp = getView().getLayoutParams();
                            lp.width = (int) (bitmap.getIntrinsicWidth() * scale);
                            lp.height = (int) (bitmap.getIntrinsicHeight() * scale);
                            getView().setImageDrawable(bitmap);
                            getView().requestLayout();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(mImageView.getContext()).clear(mImageView);
        }

    }

    public static void displayImage(String uri, ImageView imageView, ViewTarget simpleTarget) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .into(simpleTarget);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * @param uri
     * @param imageView
     * @param
     * @param simpleImageLoadingListener
     */
    public static void displayBlurImage(
            final String uri,
            final ImageView imageView,
            RequestListener<Bitmap> simpleImageLoadingListener) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .asBitmap()
                    .load(uri)
                    .listener(simpleImageLoadingListener);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 兼容包下的图片模糊效果,范围控制在0-25之间
     */
    public static void displayBlurImage(String uri, ImageView imageView) {
        try {
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getBlurOptions(0, 15))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    public static void displayBlurImageValue(String uri, ImageView imageView, int value) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getBlurOptions(0, value))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    public static void displayBlurImage(String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getBlurOptions(defaultDrawable, 15))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载图片（自定义参数，自定义监听事件）
     *
     * @param uri
     * @param imageView
     */
    public static void displayImage(String uri, ImageView imageView, RequestListener<Drawable> listener) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .listener(listener)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            ImageBridge.shouldLoadImage(imageView);
        }
    }

    /**
     * 加载圆角图片，失败则显示默认图片
     *
     * @param uri
     * @param imageView
     * @param roundRadius
     * @param defaultResId 默认图片资源id
     */
    public static void displayRoundImage(String uri, ImageView imageView, int roundRadius, @DrawableRes int defaultResId) {
        if (ImageBridge.shouldLoadImage(imageView)) {
            return;
        }
        try {
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getRoundedOption(defaultResId, roundRadius))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    public static void displayRoundImage(Activity activity, String uri, ImageView imageView, int roundRadius, @DrawableRes int defaultResId) {
        if (ImageBridge.shouldLoadImage(imageView)) {
            return;
        }
        try {
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getRoundedOption(defaultResId, roundRadius))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayRoundImage(Fragment fragment, String uri, ImageView imageView, int roundRadius, @DrawableRes int defaultResId) {
        if (ImageBridge.shouldLoadImage(imageView)) {
            return;
        }
        try {
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getRoundedOption(defaultResId, roundRadius))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }

    /**
     * 加载圆角图片，失败则显示默认图片(如果用上面的方法，图片显示不对时，使用这个方法)
     *
     * @param uri
     * @param imageView
     * @param roundRadius
     * @param defaultResId 默认图片资源id
     */
    public static void displayRoundImage2(String uri, ImageView imageView, int roundRadius, int defaultResId) {
        try {
            Context mContext = imageView.getContext();
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(mContext)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getRoundedOption(defaultResId, roundRadius))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    public static void displayRoundImage2(String uri, ImageView imageView, int defaultResId) {
        try {
            Context mContext = imageView.getContext();
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(mContext)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultResId))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }


    /**
     * 加载圆角图片
     *
     * @param uri
     * @param imageView
     * @param roundRadius
     */
    public static void displayRoundImage(String uri, ImageView imageView, int roundRadius) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getRoundedOption(0, roundRadius))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param uri
     * @param imageView
     * @param roundRadius
     */
    public static void displayRoundImage(int uri, ImageView imageView, int roundRadius) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getRoundedOption(0, roundRadius))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayRoundImage(String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(0))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    public static void displayRoundImage(Activity activity, String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(0))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayRoundImage(Activity activity, String uri, ImageView imageView,@DrawableRes int devalue) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(0))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayRoundImage(Fragment fragment, String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(0))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }

    public static void displayOvalImage(String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    public static void displayOvalImage(Activity activity, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(uri)
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayOvalImage(Fragment fragment, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(uri)
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayRoundImage(int uri, ImageView imageView) {
        if (ImageBridge.shouldLoadImage(imageView)) {
            return;
        }
        try {
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getCircleHolderOptions(0))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param uri
     * @param imageView
     */


    public static void displayRoundImageWithDefault(String uri, ImageView imageView, int defaultResId) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultResId))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayRoundImageWithDefault(int uri, ImageView imageView, int defaultResId) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(uri)
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultResId))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 删除指定url的缓存
     *
     * @param url
     */
    public static void clearCacheWithUrl(Context context, String url) {
        File file = Glide.getPhotoCacheDir(context, url);
        if (file != null) {
            file.delete();
        }

    }


    /**
     * 清除缓存
     * 需要在子线程中执行
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清除缓存
     * 需要在主线程中执行
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }


    /**
     * 加载图片
     *
     * @param activity
     * @param uri
     * @param imageView
     */
    public static void displayImage(Activity activity, String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(uri)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayImage(Fragment fragment, String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }

    public static void displayImage(Activity activity, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getHolderOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayImage(Fragment fragment, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getHolderOptions(defaultDrawable))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }


    /***
     * 根据比例显示图片
     */
    public static void displayImageScaleType(Activity activity, String url, ImageView mImageView, final int scale) {
        try {
            if (ImageBridge.shouldLoadImage(mImageView)) {
                return;
            }
            Glide.with(activity)
                    .asDrawable()
                    .load(getSecurityUrl(url))
                    .into(new ImageViewTarget<Drawable>(mImageView) {
                        @Override
                        protected void setResource(Drawable bitmap) {
                            if (bitmap == null) {
                                return;
                            }
                            ViewGroup.LayoutParams lp = getView().getLayoutParams();
                            lp.width = bitmap.getIntrinsicWidth() * scale;
                            lp.height = bitmap.getIntrinsicHeight() * scale;
                            getView().setImageDrawable(bitmap);
                            getView().requestLayout();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(mImageView);
        }

    }

    public static void displayImageScaleType(Fragment fragment, String url, ImageView mImageView, final float scale) {
        try {
            if (ImageBridge.shouldLoadImage(mImageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(url))
                    .into(new ImageViewTarget<Drawable>(mImageView) {
                        @Override
                        protected void setResource(Drawable bitmap) {
                            if (bitmap == null) {
                                return;
                            }
                            ViewGroup.LayoutParams lp = getView().getLayoutParams();
                            lp.width = (int) (bitmap.getIntrinsicWidth() * scale);
                            lp.height = (int) (bitmap.getIntrinsicHeight() * scale);
                            getView().setImageDrawable(bitmap);
                            getView().requestLayout();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(mImageView);
        }

    }

    public static void displayImageScaleType(Activity activity, String url, ImageView mImageView, final float scale) {
        try {
            if (ImageBridge.shouldLoadImage(mImageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(url))
                    .into(new ImageViewTarget<Drawable>(mImageView) {
                        @Override
                        protected void setResource(Drawable bitmap) {
                            if (bitmap == null) {
                                return;
                            }
                            ViewGroup.LayoutParams lp = getView().getLayoutParams();
                            lp.width = (int) (bitmap.getIntrinsicWidth() * scale);
                            lp.height = (int) (bitmap.getIntrinsicHeight() * scale);
                            getView().setImageDrawable(bitmap);
                            getView().requestLayout();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(mImageView);
        }

    }


    public static boolean shouldLoadImage(ImageView mImageView) {
        Context mContext = mImageView.getContext();
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
                Glide.with(mImageView.getContext()).clear(mImageView);
                return true;
            }
        }
        if (mImageView.getAnimation() != null) {
            return true;
        }
        return false;
    }

    /**
     * 兼容包下的图片模糊效果,范围控制在0-25之间
     */
    public static void displayBlurImage(Activity activity, String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getBlurOptions(0, 15))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayBlurImage(Activity activity, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getBlurOptions(defaultDrawable, 15))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    public static void displayBlurImage(Fragment fragment, String uri, ImageView imageView) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptions
                            .bitmapTransform(new BlurTransformation(15)))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }

    public static void displayBlurImage(Fragment fragment, String uri, ImageView imageView, @DrawableRes int defaultDrawable) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getBlurOptions(defaultDrawable, 15))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }


    /**
     * 加载圆形图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayRoundImageWithDefault(Activity activity, String uri, ImageView imageView, int defaultResId) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(activity)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultResId))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(activity).clear(imageView);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayRoundImageWithDefault(Fragment fragment, String uri, ImageView imageView, int defaultResId) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(fragment)
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getCircleHolderOptions(defaultResId))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(imageView);
        }
    }

    /**
     * 图片的长宽最大显示多少,取其一,按取已经达到最大的一边,进行动态设定大小
     */
    public static void displayImageMaxSize(String uri, ImageView imageView, int defaultResId, final int maxWidth, final int maxHeight) {
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            imageView.setImageResource(defaultResId);
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(uri))
                    .apply(RequestOptionManager.getHolderOptions(defaultResId))
                    .into(new ViewTarget<ImageView, Drawable>(imageView) {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> glideAnimation) {
                            if (resource == null) {
                                return;
                            }
                            ViewGroup.LayoutParams lp = getView().getLayoutParams();
                            if (resource.getIntrinsicWidth() > resource.getIntrinsicHeight()) {
                                lp.height = maxHeight;
                                lp.width = (int) (resource.getIntrinsicWidth() / (float) resource.getIntrinsicHeight() * maxHeight);
                            } else {
                                lp.width = maxWidth;
                                lp.height = (int) (resource.getIntrinsicHeight() / (float) resource.getIntrinsicWidth() * maxWidth);
                            }
                            getView().setImageDrawable(resource);
                            getView().requestLayout();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    /**
     * 保持在布局中设定的大小,然后宽按比例显示
     * ImageView的高必须在布局中设定
     */
    public static void displayImageKeepHeight(String uri, ImageView mImageView) {
        try {
            displayImageKeepHeight(uri, mImageView, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayImageKeepHeight(Activity activity, String uri, ImageView mImageView) {
        try {
            displayImageKeepHeight(activity, uri, mImageView, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayImageKeepHeight(Fragment fragment, String uri, ImageView mImageView) {
        try {
            displayImageKeepHeight(fragment, uri, mImageView, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayImageKeepHeight(String uri, ImageView mImageView, final int height) {
        try {
            if (ImageBridge.shouldLoadImage(mImageView)) {
                return;
            }
            Glide.with(mImageView.getContext()).load(uri).into(new ViewTarget<ImageView, Drawable>(mImageView) {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> glideAnimation) {
                    ViewGroup.LayoutParams layoutParams = getView().getLayoutParams();
                    if (height != 0) {
                        layoutParams.height = height;
                    }
                    layoutParams.width = layoutParams.height / resource.getIntrinsicHeight() * resource.getIntrinsicWidth();
                    getView().requestLayout();
                    getView().setImageDrawable(resource);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ImageBridge.shouldLoadImage(mImageView);
        }
    }

    public static void displayImageKeepHeight(Fragment fragment, String uri, ImageView mImageView, final int height) {
        try {
            if (ImageBridge.shouldLoadImage(mImageView)) {
                return;
            }
            Glide.with(fragment).load(uri).into(new ViewTarget<ImageView, Drawable>(mImageView) {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> glideAnimation) {
                    ViewGroup.LayoutParams layoutParams = getView().getLayoutParams();
                    if (height != 0) {
                        layoutParams.height = height;
                    }
                    layoutParams.width = layoutParams.height / resource.getIntrinsicHeight() * resource.getIntrinsicWidth();
                    getView().requestLayout();
                    getView().setImageDrawable(resource);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(fragment).clear(mImageView);
        }
    }

    public static void displayImageKeepHeight(Activity activity, String uri, ImageView mImageView, final int height) {
        try {
            if (ImageBridge.shouldLoadImage(mImageView)) {
                return;
            }
            Glide.with(activity).load(uri).into(new ViewTarget<ImageView, Drawable>(mImageView) {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> glideAnimation) {
                    ViewGroup.LayoutParams layoutParams = getView().getLayoutParams();
                    if (height != 0) {
                        layoutParams.height = height;
                    }
                    layoutParams.width = layoutParams.height / resource.getIntrinsicHeight() * resource.getIntrinsicWidth();
                    getView().requestLayout();
                    getView().setImageDrawable(resource);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (activity != null && !activity.isFinishing()) {
                Glide.with(activity).clear(mImageView);
            }
        }
    }


    public static void loadIntoUseFitWidth(final String imageUrl, final ImageView imageView, final int errorImageId, float SCALE) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
        params.height = (int) (vw * SCALE + imageView.getPaddingTop() + imageView.getPaddingBottom());
        imageView.requestLayout();
        try {
            if (ImageBridge.shouldLoadImage(imageView)) {
                return;
            }
            Glide.with(imageView.getContext())
                    .load(getSecurityUrl(imageUrl))
                    .apply(RequestOptionManager.getHolderOptions(errorImageId))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }


}
