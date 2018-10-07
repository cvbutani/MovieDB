package com.example.chirag.moviedb.dbmovie;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;
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

    private RemoteRepository mRemoteRepository;


    public DbHomePresenter(Context context) {
        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());
        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        mRemoteRepository = RemoteRepository.getInstance(mLocalService, mRemoteService);
    }

    @Override
    public void getNowPlayingMovies() {
        mRemoteRepository.getNowPlayingMoviesData(new OnTaskCompletion.OnGetNowPlayingCompletion() {
            @Override
            public void onNowPlayingMovieSuccess(Movies data) {
                mCallback.onNowPlayingMovieSuccess(data);
            }

            @Override
            public void onNowPlayingMovieFailure(String errorMessage) {
                mCallback.onNowPlayingMovieFailure(errorMessage);
            }
        });
    }

    @Override
    public void getPopularMovies() {
        mRemoteRepository.getPopularMoviesData(new OnTaskCompletion.OnGetMovieCompletion() {
            @Override
            public void onHeaderItemSuccess(Movies data) {
                mCallback.onHeaderResultSuccess(data);
            }

            @Override
            public void onHeaderItemFailure(String errorMessage) {
                mCallback.onHeaderResultFailure(errorMessage);
            }

        });
    }

    @Override
    public void getTopRatedMovies() {
        mRemoteRepository.getTopRatedMoviesData(new OnTaskCompletion.OnGetTopRatedMovieCompletion() {
            @Override
            public void onTopRatedMovieSuccess(Movies data) {
                mCallback.onTopRatedMovieSuccess(data);
            }

            @Override
            public void onTopRatedMovieFailure(String errorMessage) {
                mCallback.onTopRatedMovieFailure(errorMessage);
            }
        });
    }

    @Override
    public void getUpcomingMovies() {
        mRemoteRepository.getUpcomingMoviesData(new OnTaskCompletion.OnGetUpcomingMovieCompletion() {
            @Override
            public void onUpcomingMovieSuccess(Movies data) {
                mCallback.onUpcomingMovieSuccess(data);
            }

            @Override
            public void onUpcomingMovieFailure(String errorMessage) {
                mCallback.onUpcomingMovieFailure(errorMessage);
            }
        });
    }

    @Override
    public void getGenreList() {
        mRemoteRepository.getGenreList(new OnTaskCompletion.OnGetGenresCompletion() {
            @Override
            public void onGenreListSuccess(Genre data) {
                mCallback.onGenreListSuccess(data);
            }

            @Override
            public void onGenreListFailure(String errorMessage) {
                mCallback.onGenreListFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DbHomeContract.View view) {
        mCallback = view;
        getPopularMovies();
        getGenreList();
        getNowPlayingMovies();
        getTopRatedMovies();
        getUpcomingMovies();
    }
}
