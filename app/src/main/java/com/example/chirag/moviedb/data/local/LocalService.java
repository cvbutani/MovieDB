package com.example.chirag.moviedb.data.local;

import android.support.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.MovieDao;
import com.example.chirag.moviedb.data.local.dao.ReviewDao;
import com.example.chirag.moviedb.data.local.dao.TrailerDao;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
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
public class LocalService implements RepositoryContract {

    private static volatile LocalService INSTANCE;

    private MovieDao mMovieDao;

    private TrailerDao mTrailerDao;

    private ReviewDao mReviewDao;


    private AppExecutors mAppExecutors;

    private LocalService(@NonNull AppExecutors appExecutors,
                         @NonNull MovieDao movieDao,
                         @NonNull TrailerDao trailerDao,
                         @NonNull ReviewDao reviewDao) {
        mAppExecutors = appExecutors;
        mMovieDao = movieDao;
        mTrailerDao = trailerDao;
        mReviewDao = reviewDao;
    }

    public static LocalService getInstance(@NonNull AppExecutors appExecutors,
                                           @NonNull MovieDao movieDao,
                                           @NonNull TrailerDao trailerDao,
                                           @NonNull ReviewDao reviewDao) {
        if (INSTANCE == null) {
            synchronized (LocalService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalService(appExecutors, movieDao, trailerDao, reviewDao);
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
                final List<MovieResponse> movies = mMovieDao.getMovie("POPULAR");
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
                final List<MovieResponse> movies = mMovieDao.getMovie("NOWPLAYING");
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
                final List<MovieResponse> movies = mMovieDao.getMovie("TopRated");
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
                final List<MovieResponse> movies = mMovieDao.getMovie("NOWPLAYING");
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
    public void getReviews(final int movieId, final OnTaskCompletion.OnGetReviewCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ReviewResponse> reviews = mReviewDao.getReviews(movieId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (reviews.isEmpty()) {
                            callback.onReviewResponseFailure("LOCAL DATA FAILURE");
                        } else {
                            Reviews reviewList = new Reviews();
                            reviewList.setResults(reviews);
                            callback.onReviewResponseSuccess(reviewList);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getGenres(final OnTaskCompletion.OnGetGenresCompletion callback) {
        //  Genre will be loaded automatically from database
    }

    @Override
    public void getSimilarMovies(int movieId, OnTaskCompletion.OnGetSimilarMovieCompletion callback) {

    }

    @Override
    public void getPopularTv(OnTaskCompletion.OnGetPopularTvCompletion callback) {

    }
}
