package com.example.marveltestapplication;

import com.example.marveltestapplication.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MarvelApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<DaggerApplication> applicationInjector() {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build();
    }
}
