package com.freedom.wecore.widget.magical;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author vurtne on 4-Jun-18.
 */
public class MagicalRenderer implements GLSurfaceView.Renderer {

    private Context mContext;
    private int mProgram;
    private int glHPosition;
    private int glHTexture;
    private int glHCoordinate;
    private int glHMatrix;
    private int hIsHalf;
    private int glHUxy;
    private int hChangeType;
    private int hChangeColor;
    private Bitmap mBitmap;

    private FloatBuffer bPos;
    private FloatBuffer bMiniaturePos;
    private FloatBuffer bCoord;
    private FloatBuffer bMiniatureCoord;

    private boolean isGary;

    private int textureId;
    private boolean isHalf;

    private float uXY;

    private String vertex;
    private String fragment;
    private float[] mViewMatrix=new float[16];
    private float[] mProjectMatrix=new float[16];
    private float[] mMVPMatrix=new float[16];

    static final float[] sGary = new float[]{0.299f,0.587f,0.114f};
//    static final float[] sBlur = new float[]{0.006f,0.004f,0.002f};
    static final float[] sBlur = new float[]{0.008f,0.008f,0.008f};

    private final float[] sPos={
            -1.0f,1.0f,
            -1.0f,-1.0f,
            1.0f,1.0f,
            1.0f,-1.0f
    };

    private final float[] sMiniaturePos={
            -1.0f,-0.5f,
            -1.0f,-0.96f,
            1.0f,-0.5f,
            1.0f,-0.96f
    };


    private final float[] sCoord={
            0.0f,0.0f,
            0.0f,1.0f,
            1.0f,0.0f,
            1.0f,1.0f,
    };

    private final float[] sMiniatureCoord={
            0.0f,0.75f,
            0.0f,0.0f,
            1.0f,0.75f,
            1.0f,0.0f,
    };
//    private final float[] sMiniatureCoord={
//            0.0f,0.75f,
//            0.0f,0.08f,
//            1.0f,0.75f,
//            1.0f,0.98f,
//    };


    MagicalRenderer(Context context) {
        this.mContext = context;
        this.mContext=context;
        this.vertex="magical/half_color_vertex.sh";
        this.fragment="magical/half_color_fragment.sh";
        ByteBuffer bb=ByteBuffer.allocateDirect(sPos.length*4);
        bb.order(ByteOrder.nativeOrder());
        bPos=bb.asFloatBuffer();
        bPos.put(sPos);
        bPos.position(0);


        ByteBuffer bb2=ByteBuffer.allocateDirect(sMiniaturePos.length*4);
        bb2.order(ByteOrder.nativeOrder());
        bMiniaturePos=bb2.asFloatBuffer();
        bMiniaturePos.put(sMiniaturePos);
        bMiniaturePos.position(0);

        ByteBuffer cc=ByteBuffer.allocateDirect(sCoord.length*4);
        cc.order(ByteOrder.nativeOrder());
        bCoord=cc.asFloatBuffer();
        bCoord.put(sCoord);
        bCoord.position(0);

        ByteBuffer c2=ByteBuffer.allocateDirect(sMiniatureCoord.length*4);
        c2.order(ByteOrder.nativeOrder());
        bMiniatureCoord=c2.asFloatBuffer();
        bMiniatureCoord.put(sMiniatureCoord);
        bMiniatureCoord.position(0);
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("magical/icon_head_bg_");
            Random ran=new Random();
            builder.append(String.valueOf(ran.nextInt(5) + 1));
            builder.append(".png");
            mBitmap = BitmapFactory.decodeStream(context.getResources().getAssets().open(builder.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f,1.0f,1.0f,1.0f);
        GLES20.glEnable(GLES20.GL_TEXTURE_2D);
        mProgram=ShaderUtils.createProgram(mContext.getResources(),vertex,fragment);
        glHPosition=GLES20.glGetAttribLocation(mProgram,"vPosition");
        glHCoordinate=GLES20.glGetAttribLocation(mProgram,"vCoordinate");
        glHTexture=GLES20.glGetUniformLocation(mProgram,"vTexture");
        glHMatrix=GLES20.glGetUniformLocation(mProgram,"vMatrix");
        hIsHalf=GLES20.glGetUniformLocation(mProgram,"vIsHalf");
        glHUxy=GLES20.glGetUniformLocation(mProgram,"uXY");
        onDrawCreatedSet(mProgram);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width,height);
        int w=mBitmap.getWidth();
        int h=mBitmap.getHeight();
        float sWH=w/(float)h;
        float sWidthHeight=width/(float)height;
        uXY=sWidthHeight;
        if(width>height){
            if(sWH>sWidthHeight){
                Matrix.orthoM(mProjectMatrix, 0, -sWidthHeight*sWH,sWidthHeight*sWH, -1,1, 3, 5);
            }else{
                Matrix.orthoM(mProjectMatrix, 0, -sWidthHeight/sWH,sWidthHeight/sWH, -1,1, 3, 5);
            }
        }else{
            if(sWH>sWidthHeight){
                Matrix.orthoM(mProjectMatrix, 0, -1, 1, -1/sWidthHeight*sWH, 1/sWidthHeight*sWH,3, 5);
            }else{
                Matrix.orthoM(mProjectMatrix, 0, -1, 1, -sWH/sWidthHeight, sWH/sWidthHeight,3, 5);
            }
        }
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 5.0f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix,0,mProjectMatrix,0,mViewMatrix,0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glUseProgram(mProgram);
        onDrawSet2();
        GLES20.glUniform1i(hIsHalf,isHalf?1:0);
        GLES20.glUniform1f(glHUxy,uXY);
        GLES20.glUniformMatrix4fv(glHMatrix,1,false,mMVPMatrix,0);
        GLES20.glEnableVertexAttribArray(glHPosition);
        GLES20.glEnableVertexAttribArray(glHCoordinate);
        GLES20.glUniform1i(glHTexture, 0);
        textureId=createTexture();
        GLES20.glVertexAttribPointer(glHPosition,2,GLES20.GL_FLOAT,false,0,bPos);
        GLES20.glVertexAttribPointer(glHCoordinate,2,GLES20.GL_FLOAT,false,0,bCoord);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP,0,4);

        if (!isGary){
            return;
        }
        onDrawSet();
        GLES20.glUniform1i(hIsHalf,isHalf?1:0);
        GLES20.glUniform1f(glHUxy,uXY);
        GLES20.glUniformMatrix4fv(glHMatrix,1,false,mMVPMatrix,0);
        GLES20.glEnableVertexAttribArray(glHPosition);
        GLES20.glEnableVertexAttribArray(glHCoordinate);
        GLES20.glUniform1i(glHTexture, 0);
        GLES20.glVertexAttribPointer(glHPosition,2,GLES20.GL_FLOAT,false,0,bMiniaturePos);
        GLES20.glVertexAttribPointer(glHCoordinate,2,GLES20.GL_FLOAT,false,0,bMiniatureCoord);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP,0,4);
    }

    private void onDrawCreatedSet(int mProgram) {
        hChangeType=GLES20.glGetUniformLocation(mProgram,"vChangeType");
        hChangeColor=GLES20.glGetUniformLocation(mProgram,"vChangeColor");
    }

    private void onDrawSet() {
        GLES20.glUniform1i(hChangeType,1);
        GLES20.glUniform3fv(hChangeColor,1,sGary,0);
    }

    private void onDrawSet2() {
        GLES20.glUniform1i(hChangeType,3);
        GLES20.glUniform3fv(hChangeColor,1,sBlur,0);
    }


    public void setBitmap(Bitmap bitmap){
        this.mBitmap = bitmap;
        this.isGary = true;
    }


    private int createTexture(){
        int[] texture=new int[1];
        if(mBitmap!=null&&!mBitmap.isRecycled()){
            GLES20.glGenTextures(1,texture,0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,texture[0]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_CLAMP_TO_EDGE);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_CLAMP_TO_EDGE);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mBitmap, 0);
            return texture[0];
        }
        return 0;
    }
}
