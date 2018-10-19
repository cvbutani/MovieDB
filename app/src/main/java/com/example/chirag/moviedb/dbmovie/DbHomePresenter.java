package com.example.chirag.moviedb.dbmovie;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
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
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getNowPlayingMovies() {
        mRepository.getNowPlayingMoviesData(new OnTaskCompletion.OnGetNowPlayingCompletion() {
            @Override
            public void getNowPlayingMovieSuccess(Movies data) {
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
            public void getPopularMovieSuccess(Movies data) {
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
            public void getTopRatedMovieSuccess(Movies data) {
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
            public void getUpcomingMovieSuccess(Movies data) {
                mCallback.getUpcomingMovieHome(data);
            }

            @Override
            public void getUpcomingMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getGenreMovies() {
        mRepository.getMovieGenreListData(new OnTaskCompletion.OnGetGenresCompletion() {
            @Override
            public void getMovieGenreItemSuccess(Genre data) {
                mCallback.getMovieGenreHome(data);
            }

            @Override
            public void getMovieGenreItemFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DbHomeContract.View view) {
        mCallback = view;
        getPopularMovies();
        getGenreMovies();
        getNowPlayingMovies();
        getTopRatedMovies();
        getUpcomingMovies();
    }
}
