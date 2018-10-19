package com.example.chirag.moviedb.data.local;

import android.support.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.MovieDao;
import com.example.chirag.moviedb.data.local.dao.ReviewDao;
import com.example.chirag.moviedb.data.local.dao.TrailerDao;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_NOW_PLAYING;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_POPULAR;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_TOP_RATED;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_UPCOMING;

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
    public void getMovieInfoRepo(int movieId, OnTaskCompletion.OnGetMovieInfoCompletion callback) {

    }

    @Override
    public void getTvInfoRepo(int tvId, OnTaskCompletion.OnGetTvInfoCompletion callback) {

    }

    @Override
    public void getPopularMoviesRepo(final OnTaskCompletion.OnGetMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mMovieDao.getMovie(CONTENT_TYPE_POPULAR);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getPopularMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getPopularMovieSuccess(item);
                        }
                    }
                });
            }
        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getNowPlayingMoviesRepo(final OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mMovieDao.getMovie(CONTENT_TYPE_NOW_PLAYING);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getNowPlayingMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getNowPlayingMovieSuccess(item);
                        }
                    }
                });
            }
        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTopRatedMoviesRepo(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mMovieDao.getMovie(CONTENT_TYPE_TOP_RATED);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getTopRatedMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getTopRatedMovieSuccess(item);
                        }
                    }
                });
            }
        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getUpcomingMoviesRepo(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mMovieDao.getMovie(CONTENT_TYPE_UPCOMING);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getUpcomingMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getUpcomingMovieSuccess(item);
                        }
                    }
                });
            }
        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTrailersRepo(final int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TrailerResponse> trailers = mTrailerDao.getTrailers(movieId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (trailers.isEmpty()) {
                            callback.getTrailerItemFailure("LOCAL DATA FAILURE");
                        } else {
                            Trailer trailer = new Trailer();
                            trailer.setResults(trailers);
                            callback.getTrailerItemSuccess(trailer);
                        }
                    }
                });
            }
        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getReviewsRepo(final int movieId, final OnTaskCompletion.OnGetReviewCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ReviewResponse> reviews = mReviewDao.getReviews(movieId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (reviews.isEmpty()) {
                            callback.getReviewResponseFailure("LOCAL DATA FAILURE");
                        } else {
                            Reviews reviewList = new Reviews();
                            reviewList.setResults(reviews);
                            callback.getReviewResponseSuccess(reviewList);
                        }
                    }
                });
            }
        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getSimilarMoviesRepo(int movieId, OnTaskCompletion.OnGetSimilarMovieCompletion callback) {

    }

    @Override
    public void getPopularTvRepo(OnTaskCompletion.OnGetPopularTvCompletion callback) {

    }

    @Override
    public void getTopRatedTvRepo(OnTaskCompletion.GetTopRatedTvCompletion callback) {

    }

    @Override
    public void getLatestTvRepo(OnTaskCompletion.GetLatestTvCompletion callback) {

    }
}
