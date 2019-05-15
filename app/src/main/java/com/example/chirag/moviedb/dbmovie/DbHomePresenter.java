package com.example.chirag.moviedb.dbmovie;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_NOW_PLAYING;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_POPULAR;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_TOP_RATED;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_UPCOMING;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public class DbHomePresenter implements DbHomeContract.Presenter {

    private DbHomeContract.View mCallback;

    private Repository mRepository;

    public DbHomePresenter(Context context, boolean isConnected) {

        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).userDao());

        RemoteService mRemoteService = RemoteService.getInstance(
                LocalDatabase.getInstance(context).localDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getNowPlayingMovies() {
        mRepository
                .getHomeScreenData(CONTENT_TYPE_NOW_PLAYING, CONTENT_MOVIE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<ResultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResultResponse> resultResponses) {
                        mCallback.getNowPlayingMovieHome(resultResponses);
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
    public void getPopularMovies() {
        mRepository
                .getHomeScreenData(CONTENT_TYPE_POPULAR, CONTENT_MOVIE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<ResultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResultResponse> resultResponses) {
                        mCallback.getPopularMovieHome(resultResponses);
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
    public void getTopRatedMovies() {
        mRepository
                .getHomeScreenData(CONTENT_TYPE_TOP_RATED, CONTENT_MOVIE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<ResultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResultResponse> resultResponses) {
                        mCallback.getTopRatedMovieHome(resultResponses);
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
    public void getUpcomingMovies() {
        mRepository
                .getHomeScreenData(CONTENT_TYPE_UPCOMING, CONTENT_MOVIE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<ResultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ResultResponse> resultResponses) {
                        mCallback.getUpcomingMovieHome(resultResponses);
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
    public void attachView(DbHomeContract.View view) {
        mCallback = view;
        getPopularMovies();
        getNowPlayingMovies();
        getTopRatedMovies();
        getUpcomingMovies();
    }
}
