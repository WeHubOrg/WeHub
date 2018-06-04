package com.freedom.wecore.widget.magical;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

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
        setRenderer( mRender = new MagicalRenderer());
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }
}
