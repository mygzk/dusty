package com.ext.dusty.component;

import com.ext.dusty.activity.MainActivity;

import dagger.Component;

@Component(dependencies = {AdaptorComponent.class, EntryComponent.class})
public interface MainActivityComponent {
    void inject(MainActivity obj);
}
