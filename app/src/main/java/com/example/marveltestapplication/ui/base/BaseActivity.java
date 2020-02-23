package com.example.marveltestapplication.ui.base;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected MarvelViewModel mMarvelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMarvelViewModel = new ViewModelProvider(this).get(MarvelViewModel.class);

    }
}
