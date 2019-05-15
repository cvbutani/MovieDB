package com.example.chirag.moviedb.data.remote;

import androidx.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chirag on 4/1/2019 at 22:36.
 * Project - NBATeamViewer
 */

/**
 * Network bound resource with Rx and Retrofit Response class
 * This is based on SSOT : Single Source Of Truth
 * When ever you need a data, first ask local db to provide it.
 * And when you want force refresh, get the data from network, store it into the database
 * and query database to get the data.
 * So our local database is the single source of truth to get all the data
 * This class will only fetch data from local-database when there
 * {@link NetworkBoundResource#shouldFetch()} is true, otherwise it will get the data from local
 * database
 *
 * @param <ResultType>  Type in which form you want your data from local db
 * @param <RequestType> Type in which you will get you API response from network
 */
public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final String TAG = NetworkBoundResource.class.getSimpleName();

    private Flowable<ResultType> result;

    NetworkBoundResource() {

        // Lazy db observable.
        Flowable<ResultType> dbObservable =
                // Read from disk on Computation Scheduler
                Flowable.defer(() ->
                        loadFromDb().subscribeOn(Schedulers.computation()));

        // Lazy network observable.
        Flowable<ResultType> networkObservable = Flowable.defer(() ->
                createCall()
                        .subscribeOn(Schedulers.io())
                        // Request API on IO Scheduler
                        // Read/Write to disk on Computation Scheduler
                        .observeOn(Schedulers.computation())
                        .doOnNext(this::saveCallResult)
                        .onErrorReturn(throwable -> {
                            throw Exceptions.propagate(throwable);
                        })
                        .flatMap(__ -> loadFromDb()));

        result = shouldFetch()
                ? networkObservable
                .observeOn(AndroidSchedulers.mainThread())
                : dbObservable
                .observeOn(AndroidSchedulers.mainThread());
    }

    Flowable<ResultType> asFlowable() {
        return result;
    }

    private RequestType processResponse(RequestType response) {
        return response;
    }

    abstract void saveCallResult(@NonNull RequestType item);

    protected abstract Flowable<ResultType> loadFromDb();

    protected abstract Flowable<RequestType> createCall();

    protected abstract boolean shouldFetch();
}