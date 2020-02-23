package com.example.marveltestapplication.ui.base;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected MarvelViewModel mChatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChatViewModel = ViewModelProviders.of(this).get(MarvelViewModel.class);
    }
}
