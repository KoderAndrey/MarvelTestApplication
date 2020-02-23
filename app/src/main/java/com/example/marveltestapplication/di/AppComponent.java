package com.example.marveltestapplication.di;

import com.example.marveltestapplication.MarvelApplication;
import com.example.marveltestapplication.di.module.AppBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

@Singleton
@Component(modules = {AppBindingModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MarvelApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(MarvelApplication application);

        AppComponent build();
    }

}
