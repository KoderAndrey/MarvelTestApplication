package com.example.marveltestapplication.domain.pagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.marveltestapplication.domain.model.CharacterModel;

import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.Completable;
import io.reactivex.Single;

public class GenericBoundaryCallback<T> extends PagedList.BoundaryCallback<CharacterModel> {

    private Completable mRemoveAllItems;
    private Single<List<T>> mPages;
    private Completable mInsertAllItems;
    private int mNetworkPageSize;
    private PagingRequestHelper mHelper =new PagingRequestHelper(Executors.newSingleThreadExecutor());
    private MutableLiveData<NetworkState> mNetworkState = mHelper.createStatusLiveData();

    public GenericBoundaryCallback(Completable removeAllItems, Single<List<T>> pages,
                                   Completable insertAllItems, int networkPageSize) {
        this.mRemoveAllItems = removeAllItems;
        this.mPages = pages;
        this.mInsertAllItems = insertAllItems;
        this.mNetworkPageSize = networkPageSize;

    }

    public GenericBoundaryCallback() {
    }

    public MutableLiveData<NetworkState> createStatusLiveData(){
        MutableLiveData<NetworkState> liveData = new MutableLiveData<>();
        mHelper.addListener(new PagingRequestHelper.Listener() {
            @Override
            public void onStatusChange(@NonNull com.example.marveltestapplication.domain.pagination.PagingRequestHelper.StatusReport report) {
                if (report.hasRunning()){
                    liveData.postValue(NetworkState.LOADING);
                }
                if (report.hasError()){
                    liveData.postValue(getErrorMessage());
                } else {
                    liveData.postValue(NetworkState.LOADED);
                }
            }
        });
        return liveData;
    }

    private String getErrorMessage( PagingRequestHelper.StatusReport report){
        return PagingRequestHelper.RequestType.values()
    }


    private fun getErrorMessage(report: PagingRequestHelper.StatusReport): String {
        return PagingRequestHelper.RequestType.values().mapNotNull {
            report.getErrorFor(it)?.message
        }.first()
    }

}

//class GenericBoundaryCallback<T>(
//    private val removeAllItems: () -> Completable,
//    private val getPage: (page: Int) -> Single<List<T>>,
//    private val insertAllItems: (items: List<T>) -> Completable,
//    private val networkPageSize: Int
//) : PagedList.BoundaryCallback<CharacterModel>() {
//
//    private val helper = PagingRequestHelper(Executors.newSingleThreadExecutor())
//    val networkState: MutableLiveData<NetworkState> = helper.createStatusLiveData()
//
//    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
//    private var offsetCount = 0
//
//    fun refreshPage() {
//        networkState.value = NetworkState.LOADING
//        getPage(0)
//            .subscribeOn(Schedulers.io())
//            .flatMapCompletable {
//                removeAllItems()
//                    .andThen(insertAllItems(it))
//            }.subscribeBy(
//                onComplete = {
//                    networkState.postValue(NetworkState.LOADED)
//                },
//                onError = {
//                    networkState.value = NetworkState.error(it.message)
//                }
//            ).addTo(compositeDisposable)
//    }
//
//    /**
//     * Database returned 0 items. We should query the backend for more items.
//     */
//    @MainThread
//    override fun onZeroItemsLoaded() {
//        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
//            getTop(offsetCount, it)
//        }
//    }
//
//    /**
//     * User reached to the end of the list.
//     */
//    @MainThread
//    override fun onItemAtEndLoaded(itemAtEnd: CharacterModel) {
//        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
//            getTop(offsetCount, it)
//        }
//    }
//
//
//    override fun onItemAtFrontLoaded(itemAtFront: CharacterModel) {
//        // ignored, since we only ever append to what's in the DB
//    }
//
//
//    private fun getTop(offset: Int, pagingRequest: PagingRequestHelper.Request.Callback) {
//        Timber.d("Request a new page $offset")
//        getPage(offset)
//            .subscribeOn(Schedulers.io())
//            .flatMapCompletable {
//                insertAllItems(it)
//            }.subscribeBy(
//                onComplete = {
//                    pagingRequest.recordSuccess()
//                    offsetCount += networkPageSize
//                },
//                onError = {
//                    networkState.postValue(NetworkState.error(it.message))
//                    Timber.e(it, "Error when getTop with page $offset")
//                    pagingRequest.recordFailure(it)
//                }
//            ).addTo(compositeDisposable)
//    }
//
//
//    /** Clear all references **/
//    fun cleared() {
//        compositeDisposable.clear()
//    }
//
//    fun retryPetitions() = helper.retryAllFailed()
//
//
//}