package com.ext.dusty.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ext.dusty.R;

/**
 * Created by gzhenk on 15-11-26.
 */
public class CustomCircle extends View implements View.OnClickListener {

    private int mFirstColor;
    private int mSendColor;
    private int mCircleWidth;
    private int mSpeed;

    private int mCurrentProgress;

    private Paint mPaint;

    private boolean isNext;
    private boolean isDraw = true;

    private Context mContex;
    private Toast mToast;


    public CustomCircle(Context context) {
        this(context, null);
    }

    public CustomCircle(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContex = context;
        mToast = Toast.makeText(mContex, "", Toast.LENGTH_SHORT);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customCircle);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {

            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.customCircle_firstColor:
                    mFirstColor = a.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.customCircle_sendColor:
                    mSendColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.customCircle_circleSpeed:
                    mSpeed = a.getInt(attr, 20);

                    break;
                case R.styleable.customCircle_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;

                default:
                    break;
            }

        }


        a.recycle();

        mPaint = new Paint();


        setOnClickListener(this);
        startDraw();
    }

    public CustomCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private void startDraw() {
        // 绘图线程
        new Thread() {
            public void run() {
                while (isDraw) {
                    mCurrentProgress++;
                    if (mCurrentProgress == 360) {
                        mCurrentProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;
        }.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int centre = getWidth() / 2;
        int radius = centre - mCircleWidth / 2;

        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mSendColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mCurrentProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(mSendColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mCurrentProgress, false, mPaint); // 根据进度画圆弧
        }

    }


    @Override
    public void onClick(View v) {
        if (isDraw) {
            isDraw = false;

            showToast("停止画圆");
        } else {
            isDraw = true;
            startDraw();
            showToast("开始画圆");
        }
    }


    private void showToast(String str) {
        if (mToast == null) {
            mToast = Toast.makeText(mContex, str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
        }

        mToast.show();
    }


}
