package com.example.chirag.moviedb.moviedetail;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private Repository mRepository;

    private MovieDetailContract.View mCallback;

    MovieDetailPresenter(Context context, boolean isConnected) {
        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).userDao());
        RemoteService mRemoteService =
                RemoteService.getInstance(LocalDatabase.getInstance(context).localDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getMovieInfo(final int movieId, String movieType,
                             String contentType) {
        mRepository
                .getMovieDetailData(movieId, movieType,
                        contentType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<ResultResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultResponse resultResponse) {
                        mCallback.getMovieInfoHome(movieId, resultResponse);
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
    public void getTvInfo(final int tvId) {
        mRepository.getTvInfoData(tvId, new OnTaskCompletion.OnGetTvInfoCompletion() {
            @Override
            public void getTvInfoSuccess(TMDB data) {
                mCallback.getTvInfoHome(tvId, data);
            }

            @Override
            public void getTvInfoFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getSimilarMovie(final int movieId) {
        mRepository.getSimilarMoviesData(movieId,
                new OnTaskCompletion.OnGetSimilarMovieCompletion() {
                    @Override
                    public void getSimilarMovieSuccess(Result data) {
                        mCallback.getSimilarMovieDetail(data, movieId);
                    }

                    @Override
                    public void getSimilarMovieFailure(String errorMessage) {
                        mCallback.getResultFailure(errorMessage);
                    }
                });
    }

    @Override
    public void insertTMDB(Favourite data) {
        mRepository.updateTMDBData(data);
    }

    @Override
    public void getFavouriteTMDB(String emailId) {
        mRepository.getFavouriteTMDBData(emailId,
                new OnTaskCompletion.GetFavouriteTMDBCompletion() {
                    @Override
                    public void getFavouriteTMDBSuccess(List<Favourite> data) {
                        mCallback.getFavouriteTMDBInfo(data);
                    }

                    @Override
                    public void getFavouriteTMDBFailure(String errorMessage) {
                        mCallback.getResultFailure(errorMessage);
                    }
                });
    }

    @Override
    public void attachView(MovieDetailContract.View view, int id, String emailId,
                           String movieType, String contentType) {
        mCallback = view;
        getTvInfo(id);
        getMovieInfo(id, movieType, contentType);
        getSimilarMovie(id);
        getFavouriteTMDB(emailId);
    }
}
