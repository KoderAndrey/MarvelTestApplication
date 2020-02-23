package com.example.marveltestapplication.di.module;

import com.example.marveltestapplication.ui.activity.MainActivity;
import com.example.marveltestapplication.ui.activity.PersonageActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class})
public abstract class AppBindingModule {
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract PersonageActivity bindPersonageActivity();

}
