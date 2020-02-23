package com.example.marveltestapplication.domain;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import org.jetbrains.annotations.NotNull;

//public abstract class Listing<T> {
//     LiveData<GenericBoundaryCallback<T>> getBoundaryCallback();
//
//    abstract LiveData<PagedList<CharacterModel>> getDataSource();
//
//    @NotNull
//    LiveData<NetworkState> getNetworkState(){
//        return Transformations.switchMap(getBoundaryCallback(), new Function<NetworkState, LiveData<Y>>() {
//
//        });
//    }
//}

/*
* interface Listing<T> {

    fun getBoundaryCallback(): LiveData<GenericBoundaryCallback<T>>
    fun getDataSource(): LiveData<PagedList<CharacterModel>>
    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap(getBoundaryCallback()) { it.networkState }
}*/