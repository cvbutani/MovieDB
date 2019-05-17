package com.example.chirag.moviedb.dbtv;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_POPULAR;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_TOP_RATED;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DBTvPresenter implements DBTvContract.Presenter {

    private DBTvContract.View mCallback;

    private Repository mRepository;

    public DBTvPresenter(Context context, boolean isConnected) {

        LocalService mLocalService = LocalService.getInstance(
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).userDao());

        RemoteService mRemoteService =
                RemoteService.getInstance(LocalDatabase.getInstance(context).localDao());

        this.mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getPopularTv() {
        mRepository
                .getHomeScreenData(CONTENT_TYPE_POPULAR, CONTENT_TV)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<ResultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResultResponse> resultResponses) {
                        mCallback.getPopularTvHome(resultResponses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCallback.getResultFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getTopRatedTv() {
        mRepository
                .getHomeScreenData(CONTENT_TYPE_TOP_RATED, CONTENT_TV)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<ResultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResultResponse> resultResponses) {
                        mCallback.getTopRatedTvHome(resultResponses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCallback.getResultFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(DBTvContract.View view) {
        this.mCallback = view;
        getPopularTv();
        getTopRatedTv();
    }
}
