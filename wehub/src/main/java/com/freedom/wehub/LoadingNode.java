package com.freedom.wehub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.freedom.wecore.tools.DeviceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * @author vurtne on 2-May-18.
 */
public class LoadingNode extends View{

    private Context context;
    /** 总节点数 */
    private int MAX_NODE_NUM = 365;
    /** 周期 */
    private final int NODE_CYCLE = 7;
    /** 月期 */
//    private final String[] NODE_MONTH = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    /** 宽 */
    private int DEVALUE_WIDTH;
    /** 高 */
    private int DEVALUE_HEIGHT;
    /** 默认方块的大小 */
    private int DEVALUE_NODE_SIZE;
    /** 默认方块的间隔 */
    private int DEVALUE_NODE_INTERVAL;
    /** 默认列高 */
    private int DEVALUE_CLO_HEIGHT;
    /** 默认画笔 */
    private final Paint mDevaluePaint = new Paint();
    /** 工作画笔 */
    private final Paint[] mWorkPaints = new Paint[4];
    /** 绘制的原点 */
    private RectF mDrawOrigin;
    /** 动画中 */
    private boolean isAnimation;
    /** 随机数 */
    private Random mRandom;
    /** 动画角标 */
    private int mColorPoistion;
    private SparseArray<Paint> mDrawPaint = new SparseArray();



    public LoadingNode(Context context) {
        this(context,null);
    }

    public LoadingNode(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingNode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(DEVALUE_WIDTH,DEVALUE_HEIGHT);
    }

    private void init(){
//        DEVALUE_WIDTH = (int) (DeviceUtil.getScreenWidth(context) / 3 * 2);
//        DEVALUE_HEIGHT = DEVALUE_WIDTH / 2;
        DEVALUE_WIDTH = 1000;
        DEVALUE_HEIGHT = 250;
        DEVALUE_NODE_SIZE = DeviceUtil.dip2Px(context,6);
        DEVALUE_NODE_INTERVAL = DeviceUtil.dip2Px(context,1);
        DEVALUE_CLO_HEIGHT = DEVALUE_NODE_SIZE * NODE_CYCLE + DEVALUE_NODE_INTERVAL * (NODE_CYCLE - 1);
        mDrawOrigin = new RectF(0,0,DEVALUE_NODE_SIZE,DEVALUE_NODE_SIZE);
        mDevaluePaint.setColor(ContextCompat.getColor(context,R.color.devalue_paint));
        mDevaluePaint.setStyle(Paint.Style.FILL);
        mDevaluePaint.setAntiAlias(true);
        mDevaluePaint.setDither(true);
        mDevaluePaint.setStrokeCap(Paint.Cap.SQUARE);
        Paint paint0 = new Paint();
        paint0.setColor(ContextCompat.getColor(context,R.color.work_00));
        paint0.setStyle(Paint.Style.FILL);
        paint0.setStrokeWidth(DEVALUE_NODE_SIZE /2);
        paint0.setAntiAlias(true);
        paint0.setDither(true);
        paint0.setStrokeCap(Paint.Cap.SQUARE);
        mWorkPaints[0] = paint0;
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(DEVALUE_NODE_SIZE /2);
        paint1.setAntiAlias(true);
        paint1.setDither(true);
        paint1.setStrokeCap(Paint.Cap.SQUARE);
        paint1.setColor((ContextCompat.getColor(context,R.color.work_01)));
        mWorkPaints[1] = paint1;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(DEVALUE_NODE_SIZE /2);
        paint2.setAntiAlias(true);
        paint2.setDither(true);
        paint2.setStrokeCap(Paint.Cap.SQUARE);
        paint2.setColor((ContextCompat.getColor(context,R.color.work_02)));
        mWorkPaints[2] = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        paint3.setStrokeWidth(DEVALUE_NODE_SIZE /2);
        paint3.setAntiAlias(true);
        paint3.setDither(true);
        paint3.setStrokeCap(Paint.Cap.SQUARE);
        paint3.setColor((ContextCompat.getColor(context,R.color.work_03)));
        mWorkPaints[3] = paint3;

        mRandom = new Random();
        setOnClickListener(v -> {
            isAnimation = true;
            mColorPoistion = 0;
            mDrawOrigin = new RectF(0,0,DEVALUE_NODE_SIZE,DEVALUE_NODE_SIZE);
            mDrawPaint.clear();
            invalidate();
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<MAX_NODE_NUM;i++){
            if (mDrawOrigin.top != 0){
                mDrawOrigin.top += DEVALUE_NODE_INTERVAL;
            }
            mDrawOrigin.bottom = mDrawOrigin.top + DEVALUE_NODE_SIZE;
            canvas.drawRect(mDrawOrigin,getPaint(i));
            mDrawOrigin.top += DEVALUE_NODE_SIZE;
            if (mDrawOrigin.top >= DEVALUE_CLO_HEIGHT){
                mDrawOrigin.top = 0;
                mDrawOrigin.left += DEVALUE_NODE_SIZE + DEVALUE_NODE_INTERVAL;
                mDrawOrigin.right = mDrawOrigin.left + DEVALUE_NODE_SIZE;
            }
        }
        if (isAnimation){
            postDelayed(mDrawRunnable,50);
        }
    }

    private Runnable mDrawRunnable = new Runnable() {
        @Override
        public void run() {
            mColorPoistion ++;
            mDrawOrigin = new RectF(0,0,DEVALUE_NODE_SIZE,DEVALUE_NODE_SIZE);
            if (mColorPoistion < MAX_NODE_NUM){
                postInvalidate();
            }else {
                isAnimation = false;
            }
        }
    };

    private Paint getPaint(int position){
        Paint paint;
        if (isAnimation){
            if (position <= mColorPoistion){
                paint = mDrawPaint.get(position);
                if (paint == null) {
                    paint = mWorkPaints[mRandom.nextInt(4)];
                    mDrawPaint.put(position,paint);
                }
            }else {
                paint =  mDevaluePaint;
            }
        }else {
            paint = mDevaluePaint;
        }
        return paint;
    }


}
