package com.ext.dusty.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ext.dusty.R;

/**
 * Created by gzhenkai on 15/12/11.
 */
public class CustomItemLayout extends LinearLayout implements View.OnClickListener {


    private static final String TAG = CustomItemLayout.class.getSimpleName();
    private static final String TITLE = "昵称:";
    private static final String CONTENT = "宝宝";
    private static final int ARROW = R.drawable.arrow_selector;

    private View mItemLayout;
    private RelativeLayout mItemRelativeLayout;
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivArrow;
    private TextView tvXian;
    private TextView tvRightText;
    private ImageView ivRightCircle;
    private Boolean isRightCircle = false;

    private String mTitleText;
    private String mContentText;
    private Bitmap mImgArrow;
    private Bitmap mImgCircle;
    private String mRightText;


    private Context mContext;

    public CustomItemLayout(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public CustomItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getCustomAttrs(context, attrs);
        init(context);
    }

    private void getCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customItemLayout);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.customItemLayout_customtitle:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.customItemLayout_customContent:
                    mContentText = a.getString(attr);
                    break;
                case R.styleable.customItemLayout_customArrow:
                    mImgArrow = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.customItemLayout_customRightText:
                    mRightText = a.getString(attr);
                    break;
                case R.styleable.customItemLayout_customRightCircle:
                    mImgCircle = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.customItemLayout_customIsImg:
                    isRightCircle = a.getBoolean(attr, false);
                    break;

                default:
                    break;
            }
        }

        a.recycle();
    }


    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mItemLayout = inflater.inflate(R.layout.custom_item_layout, this);

        mItemRelativeLayout = (RelativeLayout) mItemLayout.findViewById(R.id.custom_item_layout);
        tvTitle = (TextView) mItemLayout.findViewById(R.id.custom_item_title);
        tvContent = (TextView) mItemLayout.findViewById(R.id.custom_item_content);
        ivArrow = (ImageView) mItemLayout.findViewById(R.id.custom_item_arrow);
        tvXian = (TextView) mItemLayout.findViewById(R.id.custom_item_xian);
        ivRightCircle = (ImageView) mItemLayout.findViewById(R.id.custom_item_circleimg);
        tvRightText = (TextView) mItemLayout.findViewById(R.id.custom_item_righttext);

        //暂时测试 点击事件
        tvContent.setOnClickListener(this);
        ivArrow.setOnClickListener(this);
        ivRightCircle.setOnClickListener(this);
        tvRightText.setOnClickListener(this);


        if (!TextUtils.isEmpty(mTitleText)) {
            tvTitle.setText(mTitleText);
        }
        if (!TextUtils.isEmpty(mContentText)) {
            tvContent.setText(mContentText);
        }
        if (mImgArrow != null) {
            ivArrow.setImageBitmap(mImgArrow);
        } else {
            ivArrow.setImageResource(ARROW);
        }
        if (!TextUtils.isEmpty(mRightText) && !isRightCircle) {
            tvRightText.setVisibility(VISIBLE);
            ivRightCircle.setVisibility(GONE);
            tvRightText.setText(mRightText);
        }
        if (isRightCircle && mImgCircle != null) {
            ivRightCircle.setVisibility(VISIBLE);
            tvRightText.setVisibility(GONE);
            ivRightCircle.setImageBitmap(mImgCircle);

        }

    }

    //暂时测试 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_item_content:
                Log.e(TAG, "content");
                Toast.makeText(mContext, "content", Toast.LENGTH_SHORT).show();

                break;
            case R.id.custom_item_arrow:
                Log.e(TAG, "arrow");
                Toast.makeText(mContext, "arrow", Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_item_circleimg:
                Log.e(TAG, "custom_item_circleimg");
                Toast.makeText(mContext, "custom_item_circleimg", Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_item_righttext:
                Log.e(TAG, "arrow");
                Toast.makeText(mContext, "custom_item_righttext", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public TextView getTvRightText() {
        return tvRightText;
    }

    public void setTvRightText(TextView tvRightText) {
        this.tvRightText = tvRightText;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvXian() {
        return tvXian;
    }

    public void setTvXian(TextView tvXian) {
        this.tvXian = tvXian;
    }

    public ImageView getIvRightCircle() {
        return ivRightCircle;
    }

    public void setIvRightCircle(ImageView ivRightCircle) {
        this.ivRightCircle = ivRightCircle;
    }

    public ImageView getIvArrow() {
        return ivArrow;
    }

    public void setIvArrow(ImageView ivArrow) {
        this.ivArrow = ivArrow;
    }

    public RelativeLayout getmItemRelativeLayout() {
        return mItemRelativeLayout;
    }

    public void setmItemRelativeLayout(RelativeLayout mItemRelativeLayout) {
        this.mItemRelativeLayout = mItemRelativeLayout;
    }
}
