package com.example.chirag.moviedb.dbmovie;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.util.AppExecutors;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public class DbHomePresenter implements DbHomeContract.Presenter {

    private DbHomeContract.View mCallback;

    private Repository mRepository;

    public DbHomePresenter(Context context, boolean isConnected) {

        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao());

        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getNowPlayingMovies() {
        mRepository.getNowPlayingMoviesData(new OnTaskCompletion.OnGetNowPlayingCompletion() {
            @Override
            public void getNowPlayingMovieSuccess(Result data) {
                mCallback.getNowPlayingMovieHome(data);
            }

            @Override
            public void getNowPlayingMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getPopularMovies() {
        mRepository.getPopularMoviesData(new OnTaskCompletion.OnGetMovieCompletion() {
            @Override
            public void getPopularMovieSuccess(Result data) {
                mCallback.getPopularMovieHome(data);
            }

            @Override
            public void getPopularMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }

        });
    }

    @Override
    public void getTopRatedMovies() {
        mRepository.getTopRatedMoviesData(new OnTaskCompletion.OnGetTopRatedMovieCompletion() {
            @Override
            public void getTopRatedMovieSuccess(Result data) {
                mCallback.getTopRatedMovieHome(data);
            }

            @Override
            public void getTopRatedMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getUpcomingMovies() {
        mRepository.getUpcomingMoviesData(new OnTaskCompletion.OnGetUpcomingMovieCompletion() {
            @Override
            public void getUpcomingMovieSuccess(Result data) {
                mCallback.getUpcomingMovieHome(data);
            }

            @Override
            public void getUpcomingMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
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
