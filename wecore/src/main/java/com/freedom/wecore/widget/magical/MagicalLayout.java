package com.freedom.wecore.widget.magical;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

/**
 * @author vurtne on 4-Jun-18.
 */
public class MagicalLayout extends GLSurfaceView {

    private Context context;
    private MagicalRenderer mRender;

    public MagicalLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init(){
        setEGLContextClientVersion(2);
        setRenderer( mRender = new MagicalRenderer(context));
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void loadImage(String url){
        if (TextUtils.isEmpty(url)){
            return;
        }
        Glide.with(context).load( url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                BitmapDrawable bd = (BitmapDrawable) resource;
                Bitmap bitmap = bd.getBitmap();
                mRender.setBitmap(bitmap);
                MagicalLayout.this.requestRender();
            }
        });
    }


}
