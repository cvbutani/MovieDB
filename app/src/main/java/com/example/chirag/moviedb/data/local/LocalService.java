package com.example.chirag.moviedb.data.local;

import android.support.annotation.NonNull;

import com.example.chirag.moviedb.data.MovieDataSource;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
public class LocalService implements MovieDataSource {

    private static volatile LocalService INSTANCE;

    private LocalDao mLocalDao;

    private TrailerDao mTrailerDao;

    private AppExecutors mAppExecutors;

    private LocalService(@NonNull AppExecutors appExecutors, @NonNull LocalDao localDao,@NonNull TrailerDao trailerDao) {
        mAppExecutors = appExecutors;
        mLocalDao = localDao;
        mTrailerDao = trailerDao;
    }

    public static LocalService getInstance(@NonNull AppExecutors appExecutors, @NonNull LocalDao localDao, @NonNull TrailerDao trailerDao) {
        if (INSTANCE == null) {
            synchronized (LocalService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalService(appExecutors, localDao, trailerDao);
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
                final List<MovieResponse> movies = mLocalDao.getMovie("POPULAR");
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.onHeaderItemFailure("LOCAL DATA FAILURE");
                        } else {
                            Movies item = new Movies();
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
                final List<MovieResponse> movies = mLocalDao.getMovie("NOWPLAYING");
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.onNowPlayingMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Movies item = new Movies();
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
    public void getTopRatedMovies(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<MovieResponse> movies = mLocalDao.getMovie("TopRated");
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.onTopRatedMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Movies item = new Movies();
                            item.setResults(movies);
                            callback.onTopRatedMovieSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getUpcomingMovies(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<MovieResponse> movies = mLocalDao.getMovie("NOWPLAYING");
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.onUpcomingMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Movies item = new Movies();
                            item.setResults(movies);
                            callback.onUpcomingMovieSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTrailers(final int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TrailerResponse> trailers = mTrailerDao.getTrailers(movieId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (trailers.isEmpty()) {
                            callback.onTrailerItemFailure("LOCAL DATA FAILURE");
                        } else {
                            Trailer trailer = new Trailer();
                            trailer.setResults(trailers);
                            callback.onTrailerItemSuccess(trailer);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getReviews(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {

    }
}
