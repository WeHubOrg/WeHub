package com.freedom.wecore.tools;

import android.support.annotation.DrawableRes;
import android.util.SparseArray;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * @author vurtne on 30-Apr-18.
 */

public class RequestOptionManager {
    private static RequestOptions mHolderOptions;
    private static RequestOptions mHolderEmptyOptions;
    private static RequestOptions mCricleHolderOptions;
    private static RequestOptions mCircleHolderEmptyOptions;
    private static RequestOptions mRoundHolderOptions;
    private static RequestOptions mRoundHolderEmptyOptions;
    private static RequestOptions mBlurOptions;
    private static RequestOptions mBlurEmptyOptions;
    private static RequestOptions mOverriedOptions;
    private static RequestOptions mOverriedEmptyOptions;
    private static RequestOptions mNoAnimateOptions;
    private static RequestOptions mNoAnimateEmptyOptions;
    private static SparseArray<RequestOptions> mSparseRoundOptions = new SparseArray();

    public static RequestOptions getHolderOptions(@DrawableRes int drawable) {
        if (drawable == 0) {
            if (mHolderEmptyOptions == null) {
                mHolderEmptyOptions = new RequestOptions();
            }
            return mHolderEmptyOptions;
        }
        if (mHolderOptions == null) {
            mHolderOptions = new RequestOptions();
        }
        return mHolderOptions.placeholder(drawable);
    }

    public static RequestOptions getCircleHolderOptions(@DrawableRes int drawable) {
        if (drawable == 0) {
            if (mCircleHolderEmptyOptions == null) {
                mCircleHolderEmptyOptions = new RequestOptions().circleCrop();
            }
            return mCircleHolderEmptyOptions;
        }
        if (mCricleHolderOptions == null) {
            mCricleHolderOptions = new RequestOptions().circleCrop();
        }
        return mCricleHolderOptions.placeholder(drawable);
    }

    public static RequestOptions getRoundedOption(@DrawableRes int drawable, int roundRadius) {
        RequestOptions roundOptions = mSparseRoundOptions.get(roundRadius);
        if (roundOptions == null) {
            roundOptions = bitmapTransform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(roundRadius)));
            mSparseRoundOptions.put(roundRadius, roundOptions);
        }
        if (drawable == 0) {
            if (mRoundHolderEmptyOptions == null) {
                mRoundHolderEmptyOptions = new RequestOptions();
            }
            return mRoundHolderEmptyOptions.apply(roundOptions);
        }

        if (mRoundHolderOptions == null) {
            mRoundHolderOptions = new RequestOptions()
                    .apply(roundOptions)
                    .placeholder(drawable)
                    .centerCrop();
        } else {
            mRoundHolderOptions.apply(roundOptions).placeholder(drawable);
        }
        return mRoundHolderOptions;
    }


    public static RequestOptions getBlurOptions(@DrawableRes int drawable, int blur) {

        if (drawable == 0) {
            if (mBlurEmptyOptions == null) {
                mBlurEmptyOptions = new RequestOptions();
            }
            return bitmapTransform(new BlurTransformation(blur));
        }


        if (mBlurOptions == null) {
            mBlurOptions = new RequestOptions()
                    .apply(bitmapTransform(new BlurTransformation(blur)))
                    .placeholder(drawable);
        } else {
            mBlurOptions.apply(bitmapTransform(new BlurTransformation(blur)))
                    .placeholder(drawable);
        }

        return mBlurOptions;
    }

    public static RequestOptions getOverriedOptions(@DrawableRes int drawable, int width, int height) {

        if (drawable == 0) {
            if (mOverriedEmptyOptions == null) {
                mOverriedEmptyOptions = new RequestOptions();
            }
            return mOverriedOptions.override(width, height);
        }

        if (mOverriedOptions == null) {
            mOverriedOptions = new RequestOptions()
                    .override(width, height)
                    .placeholder(drawable);
        } else {
            mOverriedOptions
                    .override(width, height)
                    .placeholder(drawable);
        }

        return mOverriedOptions;
    }

    public static RequestOptions getNoAnimateOptions(@DrawableRes int drawable) {
        if (drawable == 0) {
            if (mNoAnimateEmptyOptions == null) {
                mNoAnimateEmptyOptions = new RequestOptions()
                        .centerCrop()
                        .dontAnimate();
            }
            return mNoAnimateEmptyOptions;
        }

        if (mNoAnimateOptions == null) {
            mNoAnimateOptions = new RequestOptions()
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(drawable);
        }
        mNoAnimateOptions.placeholder(drawable);
        return mNoAnimateOptions;
    }
}
