package com.ext.dusty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import com.ext.dusty.R;

import butterknife.ButterKnife;

public class RevealActivity extends AppCompatActivity {


    LinearLayout mLinearLayout;
    RelativeLayout mRelativeLayout;
     FrameLayout mFramenLayout;
    TableLayout mtableLayout;
    AbsoluteLayout mAbsoluteLayout;
    ScrollView mScrollView;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        ButterKnife.bind(this);
    }

}
