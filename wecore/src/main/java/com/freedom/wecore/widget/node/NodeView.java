package com.freedom.wecore.widget.node;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

import com.freedom.wecore.R;
import com.freedom.wecore.tools.DeviceUtil;

import java.util.Random;


/**
 * @author vurtne on 2-May-18.
 */
public class NodeView extends View{

    private Context context;
    /** 总节点数 */
    private final float MAX_NODE_NUM = 365.0f;
    /** 周期 */
    private final float DEVALUE_NODE_CYCLE = 7.0f;
    /** 默认方块的大小 */
    private final int DEVALUE_NODE_SIZE = 44;
    /** 默认方块的间隔 */
    private final int DEVALUE_NODE_INTERVAL = 6;
    /** 月期 */
//    private final String[] NODE_MONTH = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private float mNodeCount;
    /** 宽 */
    private int mWidth;
    /** 高 */
    private int mHeight;
    /** 方块的大小 */
    private float mNodeSize;
    /** 方块的间隔 */
    private float mNodeInterval;
    /** 方块的间隔 */
    private float mNodeCycle;
    /** 是否自动动画 */
    private boolean isAutomation;
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
    private int mColorPosition;
    private SparseArray<Paint> mDrawPaint = new SparseArray();

    private NodePresenter mPresenter;

    private Runnable mDrawRunnable = new Runnable() {
        @Override
        public void run() {
            mColorPosition ++;
            mDrawOrigin.left = 0;
            mDrawOrigin.top = 0;
            mDrawOrigin.right = mNodeSize;
            mDrawOrigin.bottom = mNodeSize;
            if (mColorPosition < mNodeCount){
                postInvalidate();
            }else {
                mColorPosition = 0;
                mDrawPaint.clear();
                postInvalidate();
            }
        }
    };

    public NodeView(Context context) {
        this(context,null);
    }

    public NodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWidth,mHeight);
    }

    private void init( @Nullable AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NodeView);
        isAutomation = array.getBoolean(R.styleable.NodeView_nodeAutomation,false);
        mNodeCount = array.getFloat(R.styleable.NodeView_nodeCount,MAX_NODE_NUM);
        mNodeCycle = array.getFloat(R.styleable.NodeView_nodeCycle,DEVALUE_NODE_CYCLE);
        mNodeSize = array.getDimension(R.styleable.NodeView_nodeSize,DEVALUE_NODE_SIZE);
        mNodeInterval = array.getDimension(R.styleable.NodeView_nodeInterval,DEVALUE_NODE_INTERVAL);
        mWidth = (int)((mNodeSize + mNodeInterval) * Math.ceil(mNodeCount / mNodeCycle) - mNodeInterval);
        mHeight = (int)(mNodeSize * mNodeCycle + mNodeInterval * (mNodeCycle - 1));
        //防止超屏
        mWidth = (int)Math.min( mWidth , DeviceUtil.getScreenWidth((Activity)context));
        mHeight = (int)Math.min( mHeight , DeviceUtil.getScreenHeight((Activity) context));
        initPaint();
        mRandom = new Random();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnimation = true;
                mColorPosition = 0;
                mDrawOrigin = new RectF(0,0,mNodeSize,mNodeSize);
                mDrawPaint.clear();
                invalidate();
            }
        });
        array.recycle();
        mPresenter = NodePresenter.instance(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<mNodeCount;i++){
            if (mDrawOrigin.top != 0){
                mDrawOrigin.top += mNodeInterval;
            }
            mDrawOrigin.bottom = mDrawOrigin.top + mNodeSize;
            canvas.drawRect(mDrawOrigin,getPaint(i));
            mDrawOrigin.top += mNodeSize;
            if (mDrawOrigin.top >= mHeight){
                mDrawOrigin.top = 0;
                mDrawOrigin.left += mNodeSize + mNodeInterval;
                mDrawOrigin.right = mDrawOrigin.left + mNodeSize;
            }
        }
        if (isAnimation || isAutomation){
            postDelayed(mDrawRunnable,50);
        }
    }

    private void initPaint(){
        mDrawOrigin = new RectF(0,0,mNodeSize,mNodeSize);
        mDevaluePaint.setColor(ContextCompat.getColor(context,R.color.devalue_paint));
        mDevaluePaint.setStyle(Paint.Style.FILL);
        mDevaluePaint.setAntiAlias(true);
        mDevaluePaint.setDither(true);
        mDevaluePaint.setStrokeCap(Paint.Cap.SQUARE);
        Paint paint0 = new Paint();
        paint0.setColor(ContextCompat.getColor(context,R.color.work_00));
        paint0.setStyle(Paint.Style.FILL);
        paint0.setStrokeWidth(mNodeSize /2);
        paint0.setAntiAlias(true);
        paint0.setDither(true);
        paint0.setStrokeCap(Paint.Cap.SQUARE);
        mWorkPaints[0] = paint0;
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(mNodeSize /2);
        paint1.setAntiAlias(true);
        paint1.setDither(true);
        paint1.setStrokeCap(Paint.Cap.SQUARE);
        paint1.setColor((ContextCompat.getColor(context,R.color.work_01)));
        mWorkPaints[1] = paint1;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(mNodeSize /2);
        paint2.setAntiAlias(true);
        paint2.setDither(true);
        paint2.setStrokeCap(Paint.Cap.SQUARE);
        paint2.setColor((ContextCompat.getColor(context,R.color.work_02)));
        mWorkPaints[2] = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        paint3.setStrokeWidth(mNodeSize /2);
        paint3.setAntiAlias(true);
        paint3.setDither(true);
        paint3.setStrokeCap(Paint.Cap.SQUARE);
        paint3.setColor((ContextCompat.getColor(context,R.color.work_03)));
        mWorkPaints[3] = paint3;
    }

    private Paint getPaint(int position){
        Paint paint;
        if (isAnimation || isAutomation){
            if (position <= mColorPosition){
                paint = mDrawPaint.get(position);
                if (paint == null) {
                    paint = getRandomPaint();
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
    //       0  1  2  3  4
    //   0 - 15 8  4  2  1
    //   1 - 8  11 5  4  2
    //   2 - 2  4  5  11 8
    //   3 - 1  2  4  8  15
    private Paint getRandomPaint(){
        int i = mRandom.nextInt(30);
        switch (NodeConfig.instance().getCurState()){
            case NodeConfig.LAZY:
                if (i < 15){
                    return mDevaluePaint;
                }else if (i < 23){
                    return mWorkPaints[0];
                }else if (i < 27){
                    return mWorkPaints[1];
                }else if (i < 29){
                    return mWorkPaints[2];
                }else if (i < 30){
                    return mWorkPaints[3];
                }
                break;
            case NodeConfig.NEGATIVE:
                if (i < 8){
                    return mDevaluePaint;
                }else if (i < 19){
                    return mWorkPaints[0];
                }else if (i < 24){
                    return mWorkPaints[1];
                }else if (i < 28){
                    return mWorkPaints[2];
                }else if (i < 30){
                    return mWorkPaints[3];
                }
                break;
            case NodeConfig.DILIGENT:
                if (i < 2){
                    return mDevaluePaint;
                }else if (i < 6){
                    return mWorkPaints[0];
                }else if (i < 11){
                    return mWorkPaints[1];
                }else if (i < 22){
                    return mWorkPaints[2];
                }else if (i < 30){
                    return mWorkPaints[3];
                }
                break;
            case NodeConfig.METAMORPHOSIS:
                if (i < 1){
                    return mDevaluePaint;
                }else if (i < 3){
                    return mWorkPaints[0];
                }else if (i < 7){
                    return mWorkPaints[1];
                }else if (i < 15){
                    return mWorkPaints[2];
                }else if (i < 30){
                    return mWorkPaints[3];
                }
                break;
            default:break;
        }
        return null;
    }



}
