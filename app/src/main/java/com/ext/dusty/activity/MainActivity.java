package com.ext.dusty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.ext.dusty.R;
import com.ext.dusty.adaptor.EntryAdaptor;
import com.ext.dusty.component.AdaptorComponent;
import com.ext.dusty.component.DaggerAdaptorComponent;
import com.ext.dusty.component.DaggerEntryComponent;
import com.ext.dusty.component.DaggerMainActivityComponent;
import com.ext.dusty.component.EntryComponent;
import com.ext.dusty.component.MainActivityComponent;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv_entries)
    RecyclerView mEntries;
    @Inject
    EntryAdaptor mAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupComponent(this);

        mEntries.setAdapter(mAdaptor);

    }

    private void setupComponent(MainActivity obj) {
        EntryComponent entryComponent = DaggerEntryComponent.builder().build();
        AdaptorComponent adaptorComponent = DaggerAdaptorComponent.builder()
                .entryComponent(entryComponent)
                .build();
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .adaptorComponent(adaptorComponent)
                .entryComponent(entryComponent)
                .build();
        mainActivityComponent.inject(obj);
    }
}
