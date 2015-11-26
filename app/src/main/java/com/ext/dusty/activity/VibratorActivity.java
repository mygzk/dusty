package com.ext.dusty.activity;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ext.dusty.R;

import butterknife.ButterKnife;

public class VibratorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);
        ButterKnife.bind(this);
        Button vibrator = (Button)findViewById(R.id.vibrator);
        vibrator.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                getSystemService(VIBRATOR_SERVICE);//获得一个震动的服务
                //震动5秒
                vibrator.vibrate(100);
//		    vibrator.cancel();
                Log.d("DEBUG", "长按了");
                return false;
            }
        });
    }

}
