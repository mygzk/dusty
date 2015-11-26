package com.ext.dusty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ext.dusty.R;

import butterknife.ButterKnife;

public class RevealActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        ButterKnife.bind(this);
    }

}
