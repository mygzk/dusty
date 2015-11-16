package com.ext.dusty.component.module;

import com.ext.dusty.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class EntryModule {

    public static class Entry {
        public Entry(String description, Class<?> target) {
            this.description = description;
            this.target = target;
        }

        public String description;
        public Class<?> target;
    }

    private static final List<Entry> activities = new ArrayList<>();

    static {
        activities.add(new Entry("MainActivity", MainActivity.class));
    }

    @Provides
    List<Entry> provideTargetActivities() {
        return activities;
    }
}
