package com.example.marveltestapplication.ui.base;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MarvelViewModel extends ViewModel {

    @Inject
    public MarvelViewModel() {
        Log.i("test_test", "create " + hashCode());
    }
}
