package com.ext.dusty.component;

import com.ext.dusty.adaptor.EntryAdaptor;
import com.ext.dusty.component.module.AdaptorModule;

import dagger.Component;

@Component(dependencies = EntryComponent.class, modules = AdaptorModule.class)
public interface AdaptorComponent {
    //    void inject(MainActivity obj);
    EntryAdaptor adaptor();
}
