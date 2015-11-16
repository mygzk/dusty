package com.ext.dusty.component.module;


import com.ext.dusty.adaptor.EntryAdaptor;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class AdaptorModule {

    @Provides
    EntryAdaptor provideAdaptor(List<EntryModule.Entry> entries) {
        return new EntryAdaptor(entries);
    }

}
