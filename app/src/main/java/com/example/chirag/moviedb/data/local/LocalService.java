package com.example.chirag.moviedb.data.local;

import android.support.annotation.NonNull;

import com.example.chirag.moviedb.data.MovieDataSource;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
public class LocalService implements MovieDataSource {

    private static volatile LocalService INSTANCE;

    private LocalDao mLocalDao;

    private AppExecutors mAppExecutors;

    private LocalService(@NonNull AppExecutors appExecutors, @NonNull LocalDao localDao) {
        mAppExecutors = appExecutors;
        mLocalDao = localDao;
    }

    public static LocalService getInstance(@NonNull AppExecutors appExecutors, @NonNull LocalDao localDao) {
        if (INSTANCE == null) {
            synchronized (LocalService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalService(appExecutors, localDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getPopularMovies(final OnTaskCompletion.OnGetMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultHeaderItem> movies = mLocalDao.getMovie("POPULAR");
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.onHeaderItemFailure("LOCAL DATA FAILURE");
                        } else {
                            HeaderItem item = new HeaderItem();
                            item.setResults(movies);
                            callback.onHeaderItemSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getNowPlayingMovies(final OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultHeaderItem> movies = mLocalDao.getMovie("NOWPLAYING");
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.onNowPlayingMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            HeaderItem item = new HeaderItem();
                            item.setResults(movies);
                            callback.onNowPlayingMovieSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTopRatedMovies(OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {

    }

    @Override
    public void getUpcomingMovies(OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {

    }

    @Override
    public void getTrailers(int movieId, OnTaskCompletion.OnGetTrailerCompletion callback) {

    }

    @Override
    public void getReviews(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {

    }
}
