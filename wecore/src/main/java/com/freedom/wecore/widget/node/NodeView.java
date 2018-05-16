package com.freedom.wecore.widget.node;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
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
public class NodeView extends View implements NodeInternal{

    private Context context;
    /** 总节点数 */
    private final float MAX_NODE_NUM = 365.0f;
    /** 周期 */
    private final float DEVALUE_NODE_CYCLE = 7.0f;
    /** 默认方块的大小 */
    private final int DEVALUE_NODE_SIZE = 44;
    /** 默认方块的间隔 */
    private final int DEVALUE_NODE_INTERVAL = 6;

    /** 跳级因子 */
    private final double FACTOR  = (0.9 - 0.5) / (15000 / 50);
    /** 基概率 */
    private final double sBaseProbability  = 0.5;
    /** 参照因子 */
    private final int sReference = 10000000;
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
    /** 画笔 */
    private final Paint[] mPaints = new Paint[5];
    /** 绘制的原点 */
    private RectF mDrawOrigin;
    /** 动画中 */
    private boolean isAnimation;
    /** 动画暂停 */
    private boolean isPause;
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
        array.recycle();
        mPresenter = NodePresenter.instance(context);
    }

    public void reSet(){
        isPause = false;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawOrigin.left = 0;
        mDrawOrigin.top = 0;
        mDrawOrigin.right = mNodeSize;
        mDrawOrigin.bottom = mNodeSize;
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
        Paint devaluePaint = new Paint();
        devaluePaint.setColor(ContextCompat.getColor(context,R.color.devalue_paint));
        devaluePaint.setStyle(Paint.Style.FILL);
        devaluePaint.setAntiAlias(true);
        devaluePaint.setDither(true);
        devaluePaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaints[0] = devaluePaint;
        Paint paint0 = new Paint();
        paint0.setColor(ContextCompat.getColor(context,R.color.work_00));
        paint0.setStyle(Paint.Style.FILL);
        paint0.setStrokeWidth(mNodeSize /2);
        paint0.setAntiAlias(true);
        paint0.setDither(true);
        paint0.setStrokeCap(Paint.Cap.SQUARE);
        mPaints[1] = paint0;
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(mNodeSize /2);
        paint1.setAntiAlias(true);
        paint1.setDither(true);
        paint1.setStrokeCap(Paint.Cap.SQUARE);
        paint1.setColor((ContextCompat.getColor(context,R.color.work_01)));
        mPaints[2] = paint1;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(mNodeSize /2);
        paint2.setAntiAlias(true);
        paint2.setDither(true);
        paint2.setStrokeCap(Paint.Cap.SQUARE);
        paint2.setColor((ContextCompat.getColor(context,R.color.work_02)));
        mPaints[3] = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        paint3.setStrokeWidth(mNodeSize /2);
        paint3.setAntiAlias(true);
        paint3.setDither(true);
        paint3.setStrokeCap(Paint.Cap.SQUARE);
        paint3.setColor((ContextCompat.getColor(context,R.color.work_03)));
        mPaints[4] = paint3;
    }

    private Paint getPaint(int position){
        Paint paint;
        if (isAnimation || isAutomation || isPause){
            if (position <= mColorPosition){
                paint = mDrawPaint.get(position);
                if (paint == null) {
                    paint = getRandomPaint();
                    mDrawPaint.put(position,paint);
                }
            }else {
                paint = mPaints[0];
            }
        }else {
            paint =  mPaints[0];
        }
        return paint;
    }

    /**
     * 流量0 为最低等级 15KB 视为最高等级
     *
     * 最低等级提交一次的概率是0.3,
     * 最高提交一次的概率是0.9
     * 每 50 k 视为一个级别 0 - 15000 是300 个级别
     * */
    private Paint getRandomPaint(){
        if (mColorPosition == 0){
            return mPaints[mRandom.nextInt(4)];
        }
        long bytes = mPresenter.getRxBytes() / 50;
        double probability = sBaseProbability + FACTOR * bytes;
        return mPaints[getPosition(probability,probability,0)];
    }

    private int getPosition(double probability,double curProbability,int count){
        if (mRandom.nextInt(sReference) <= curProbability * sReference){
            if (count == 4){
                return 4;
            }
            return getPosition(probability,curProbability*probability,count + 1);
        }else {
            return count;
        }
    }

    @Override
    public void start() {
        if (isAnimation){
            return;
        }
        isAnimation = true;
        mColorPosition = 0;
        mDrawOrigin = new RectF(0,0,mNodeSize,mNodeSize);
        mDrawPaint.clear();
        invalidate();
    }

    @Override
    public void finish() {
        isPause = true;
        isAnimation = false;
    }

    @Override
    public void released() {

    }
}
