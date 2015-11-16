package com.ext.dusty.component;

import com.ext.dusty.component.module.EntryModule;

import java.util.List;

import dagger.Component;

@Component(modules = {EntryModule.class})
public interface EntryComponent {
    //    void inject(EntryAdaptor obj);
    List<EntryModule.Entry> entries();
}
