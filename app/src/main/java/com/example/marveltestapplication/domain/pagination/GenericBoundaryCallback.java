package com.example.marveltestapplication.domain.pagination;

import androidx.annotation.MainThread;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.marveltestapplication.domain.model.CharacterModel;

import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.Completable;
import io.reactivex.Single;

import static android.text.TextUtils.isEmpty;

public class GenericBoundaryCallback<T> extends PagedList.BoundaryCallback<CharacterModel> {

    private Completable mRemoveAllItems;
    private Single<List<T>> mPages;
    private Completable mInsertAllItems;
    private int mNetworkPageSize;
    private int mOffsetCount;
    private PagingRequestHelper mHelper = new PagingRequestHelper(Executors.newSingleThreadExecutor());
    private MutableLiveData<NetworkState> mNetworkState = createStatusLiveData();

    public GenericBoundaryCallback(Completable removeAllItems, Single<List<T>> pages,
                                   Completable insertAllItems, int networkPageSize) {
        this.mRemoveAllItems = removeAllItems;
        this.mPages = pages;
        this.mInsertAllItems = insertAllItems;
        this.mNetworkPageSize = networkPageSize;

    }

    public GenericBoundaryCallback() {
    }

    public MutableLiveData<NetworkState> createStatusLiveData() {
        MutableLiveData<NetworkState> liveData = new MutableLiveData<>();
        mHelper.addListener(report -> {
            if (report.hasRunning()) {
                liveData.postValue(NetworkState.LOADING);
            }
            if (report.hasError()) {
                liveData.postValue(NetworkState.error(getErrorMessage(report)));
            } else {
                liveData.postValue(NetworkState.LOADED);
            }
        });
        return liveData;
    }

    private String getErrorMessage(PagingRequestHelper.StatusReport report) {
        for (PagingRequestHelper.RequestType type : PagingRequestHelper.RequestType.values()) {
            if (report.getErrorFor(type) != null) {
                if (!isEmpty(report.getErrorFor(type).getMessage())) {
                    return report.getErrorFor(type).getMessage();
                }
            }
        }
        return "";
    }

    @Override
    public void onZeroItemsLoaded() {
        mHelper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL, );
    }

    public MutableLiveData<NetworkState> getNetworkState(){
        return mNetworkState;
    }

    @MainThread
    @Override
    public void onItemAtEndLoaded(CharacterModel itemAtEnd){
        mHelper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER, );
    }

//    private fun getTop(offset: Int, pagingRequest: PagingRequestHelper.Request.Callback) {
//        Timber.d("Request a new page $offset")
//        getPage(offset)
//                .subscribeOn(Schedulers.io())
//                .flatMapCompletable {
//            insertAllItems(it)
//        }.subscribeBy(
//                onComplete = {
//                        pagingRequest.recordSuccess()
//                        offsetCount += networkPageSize
//                },
//                onError = {
//                        networkState.postValue(NetworkState.error(it.message))
//                        Timber.e(it, "Error when getTop with page $offset")
//                        pagingRequest.recordFailure(it)
//                }
//        ).addTo(compositeDisposable)
//    }
}
