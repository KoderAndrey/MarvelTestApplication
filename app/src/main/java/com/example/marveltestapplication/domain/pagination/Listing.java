package com.example.marveltestapplication.domain.pagination;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.PagedList;

import com.example.marveltestapplication.domain.model.CharacterModel;

import org.jetbrains.annotations.NotNull;

public abstract class Listing<T> {
    abstract LiveData<GenericBoundaryCallback<T>> getBoundaryCallback();

    abstract LiveData<PagedList<CharacterModel>> getDataSource();

    @NotNull
    LiveData<NetworkState> getNetworkState() {
        return Transformations.switchMap(getBoundaryCallback(), GenericBoundaryCallback::getNetworkState);
    }
}

