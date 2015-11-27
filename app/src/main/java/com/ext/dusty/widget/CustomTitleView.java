package com.ext.dusty.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.ext.dusty.R;

/**
 * Created by gzhenk on 15-11-26.
 */
public class CustomTitleView extends View {

    private static final int IMAGE_SCALE_FITXY=0;
    private static final String TAG = CustomTitleView.class.getSimpleName();
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private int mImageScalse;



    private Bitmap mImage;
    private Rect mTextBound;
    private final Rect mRect;
    private Paint mPaint;


    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customTitleView);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.customTitleView_titleText1:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.customTitleView_titleTextColor1:
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.customTitleView_titleTextSize1:
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                 case R.styleable.customTitleView_image:
                     mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                     break;
                case R.styleable.customTitleView_imageScaleType:
                    mImageScalse=a.getInt(attr, 0);

                    break;

                default:
                    break;
            }
        }

        a.recycle();
        
        
        mRect = new Rect();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);

        mTextBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextBound);
    }

    int mWidth;
    int mHeight;

    /**
     * 重写之前先了解MeasureSpec的specMode,一共三种类型：
     * <p/>
     * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     * <p/>
     * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     * <p/>
     * UNSPECIFIED：表示子布局想要多大就多大，很少使用
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);



        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            // 由字体决定的宽
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();
            int desire = Math.max(desireByImg, desireByTitle);
            mWidth = Math.min(desire, widthSize);


        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mTextBound.height();
            mHeight = Math.min(desire, heightSize);
        }
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        if (mTextBound.width() > mWidth)
        {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else
        {
            //正常情况，将字体居中
            canvas.drawText(mTitleText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }



        //取消使用掉的快
        mRect.bottom -= mTextBound.height();

        if (mImageScalse == IMAGE_SCALE_FITXY)
        {
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        } else
        {
            //计算居中的矩形范围
            mRect.left = mWidth / 2 - mImage.getWidth() / 2;
            mRect.right = mWidth / 2 + mImage.getWidth() / 2;
            mRect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            mRect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;

            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }

        //super.onDraw(canvas);
       /* Log.e(TAG, "====mPaint:" + mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mTextBound.width() / 2, getHeight() / 2 + mTextBound.height() / 2, mPaint);
    */

    }
}
