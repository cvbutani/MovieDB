package com.example.chirag.moviedb.data.remote;

import android.support.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.MovieDao;
import com.example.chirag.moviedb.data.local.dao.ReviewDao;
import com.example.chirag.moviedb.data.local.dao.TrailerDao;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.model.TvInfo;
import com.example.chirag.moviedb.network.ServiceInstance;
import com.example.chirag.moviedb.service.GetDataService;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.LANGUAGE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_NOW_PLAYING;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_POPULAR;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_TOP_RATED;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_UPCOMING;
import static com.example.chirag.moviedb.data.Constant.TMDB_API_KEY;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteService implements RepositoryContract {

    private GetDataService mServiceApi;

    private static volatile RemoteService INSTANCE;

    private MovieDao mMovieDao;

    private TrailerDao mTrailerDao;

    private ReviewDao mReviewDao;

    private AppExecutors mAppExecutors;

    public RemoteService(@NonNull AppExecutors appExecutors, @NonNull MovieDao movieDao, @NonNull TrailerDao trailerDao, @NonNull ReviewDao reviewDao) {
        mAppExecutors = appExecutors;
        mMovieDao = movieDao;
        mTrailerDao = trailerDao;
        mReviewDao = reviewDao;
        mServiceApi = ServiceInstance.getServiceInstance().create(GetDataService.class);
    }

    public static RemoteService getInstance(@NonNull AppExecutors appExecutors, @NonNull MovieDao movieDao, @NonNull TrailerDao trailerDao, @NonNull ReviewDao reviewDao) {
        if (INSTANCE == null) {
            synchronized (RemoteService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteService(appExecutors, movieDao, trailerDao, reviewDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getMovieInfoRepo(int movieId, final OnTaskCompletion.OnGetMovieInfoCompletion callback) {
        mServiceApi.getMovieInfoDataService(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                if (response.isSuccessful()) {
                    MovieInfo movieInfo = response.body();
                    if (movieInfo != null) {
                        callback.getMovieInfoSuccess(movieInfo);
                    } else {
                        callback.getMovieInfoFailure("FAILURE");
                    }
                } else {
                    callback.getMovieInfoFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {
                callback.getMovieInfoFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getTvInfoRepo(int tvId, final OnTaskCompletion.OnGetTvInfoCompletion callback) {
        mServiceApi.getTvInfoDataService(tvId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<TvInfo>() {
            @Override
            public void onResponse(Call<TvInfo> call, Response<TvInfo> response) {
                if (response.isSuccessful()) {
                    TvInfo tvInfo = response.body();
                    if (tvInfo != null) {
                        callback.getTvInfoSuccess(tvInfo);
                    } else {
                        callback.getTvInfoFailure("FAILURE");
                    }
                } else {
                    callback.getTvInfoFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<TvInfo> call, Throwable t) {
                callback.getTvInfoFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getPopularMoviesRepo(final OnTaskCompletion.OnGetMovieCompletion callback) {
        mServiceApi.getContentDataService(CONTENT_TYPE_POPULAR, TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            Result item = response.body();
                            if (item != null) {
                                callback.getPopularMovieSuccess(item);
                                insertMovie(item, CONTENT_TYPE_POPULAR);
                            } else {
                                callback.getPopularMovieFailure("FAILURE");
                            }
                        } else {
                            callback.getPopularMovieFailure("FAILURE");
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        callback.getPopularMovieFailure(t.getMessage());
                    }
                });
    }

    @Override
    public void getNowPlayingMoviesRepo(final OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        mServiceApi.getContentDataService(CONTENT_TYPE_NOW_PLAYING, TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            Result item = response.body();
                            if (item != null) {
                                callback.getNowPlayingMovieSuccess(item);
                                insertMovie(item, CONTENT_TYPE_NOW_PLAYING);
                            } else {
                                callback.getNowPlayingMovieFailure("FAILURE");
                            }
                        } else {
                            callback.getNowPlayingMovieFailure("FAILURE");
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        callback.getNowPlayingMovieFailure(t.getMessage());
                    }
                });
    }

    @Override
    public void getTopRatedMoviesRepo(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        mServiceApi.getContentDataService(CONTENT_TYPE_TOP_RATED, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.getTopRatedMovieSuccess(item);
                        insertMovie(item, CONTENT_TYPE_TOP_RATED);
                    } else {
                        callback.getTopRatedMovieFailure("FAILURE");
                    }
                } else {
                    callback.getTopRatedMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.getTopRatedMovieFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getUpcomingMoviesRepo(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        mServiceApi.getContentDataService(CONTENT_TYPE_UPCOMING, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.getUpcomingMovieSuccess(item);
                        insertMovie(item, CONTENT_TYPE_UPCOMING);
                    } else {
                        callback.getUpcomingMovieFailure("FAILURE");
                    }
                } else {
                    callback.getUpcomingMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.getUpcomingMovieFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getMovieGenresRepo(final OnTaskCompletion.OnGetGenresCompletion callback) {
        mServiceApi.getGenreDataService(CONTENT_MOVIE, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                if (response.isSuccessful()) {
                }
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {
                callback.getMovieGenreItemFailure(t.getMessage());

            }
        });
    }

    @Override
    public void getTrailersRepo(final int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback) {
        mServiceApi.GetTrailerDataService(movieId, TMDB_API_KEY).enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                if (response.isSuccessful()) {
                    final Trailer trailerItem = response.body();
                    if (trailerItem != null && trailerItem.getResults() != null) {
                        callback.getTrailerItemSuccess(trailerItem);
                        Runnable trailerRunnable = new Runnable() {
                            @Override
                            public void run() {
                                List<TrailerResponse> trailerList = trailerItem.getResults();
                                List<TrailerResponse> trailerData = mTrailerDao.getTrailers(movieId);
                                if (trailerData.isEmpty()) {
                                    for (TrailerResponse list : trailerList) {
                                        list.setMovieId(movieId);
                                        mTrailerDao.insertTrailer(list);
                                    }
                                }
                            }
                        };
                        mAppExecutors.getDiskIO().execute(trailerRunnable);
                    } else {
                        callback.getTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                    }
                } else {
                    callback.getTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                }
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                callback.getTrailerItemFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getReviewsRepo(final int movieId, final OnTaskCompletion.OnGetReviewCompletion callback) {
        mServiceApi.getReviewDataService(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if (response.isSuccessful()) {
                    final Reviews reviews = response.body();
                    if (reviews != null && reviews.getResults() != null) {
                        callback.getReviewResponseSuccess(reviews);
                        Runnable reviewRunnable = new Runnable() {
                            @Override
                            public void run() {
                                List<ReviewResponse> reviewList = reviews.getResults();
                                List<ReviewResponse> reviewData = mReviewDao.getReviews(movieId);
                                if (reviewData.isEmpty()) {
                                    for (ReviewResponse list : reviewList) {
                                        list.setMovieId(movieId);
                                        mReviewDao.insertReviews(list);
                                    }
                                }
                            }
                        };
                        mAppExecutors.getDiskIO().execute(reviewRunnable);
                    } else {
                        callback.getReviewResponseFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                    }
                } else {
                    callback.getReviewResponseFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {
                callback.getReviewResponseFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getSimilarMoviesRepo(int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mServiceApi.getSimilarMovieDataService(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.getSimilarMovieSuccess(item);
                    } else {
                        callback.getSimilarMovieFailure("FAILURE");
                    }
                } else {
                    callback.getSimilarMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.getSimilarMovieFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getPopularTvRepo(final OnTaskCompletion.OnGetPopularTvCompletion callback) {
//        mServiceApi.getContentDataService(CONTENT_TV, CONTENT_TYPE_POPULAR, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                if (response.isSuccessful()) {
//                    Result item = response.body();
//                    if (item != null && item.getResults() != null) {
//                        callback.getPopularTvSuccess(item);
//                    } else {
//                        callback.getPopularTvFailure("FAILURE");
//                    }
//                } else {
//                    callback.getPopularTvFailure("FAILURE");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                callback.getPopularTvFailure(t.getMessage());
//            }
//        });
    }

    @Override
    public void getTvGenresRepo(final OnTaskCompletion.OnGetTVGenreCompletion callback) {
//        mServiceApi.getGenreDataService(CONTENT_TV, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Genre>() {
//            @Override
//            public void onResponse(Call<Genre> call, Response<Genre> response) {
//                if (response.isSuccessful()) {
//                    Genre item = response.body();
//                    if (item != null && item.getGenreResponses() != null) {
//                        callback.getTVGenreSuccess(item);
//                    } else {
//                        callback.getTVGenreFailure("FAILURE");
//                    }
//                } else {
//                    callback.getTVGenreFailure("FAILURE");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Genre> call, Throwable t) {
//                callback.getTVGenreFailure(t.getMessage());
//            }
//        });
    }

    @Override
    public void getTopRatedTvRepo(final OnTaskCompletion.GetTopRatedTvCompletion callback) {
//        mServiceApi.getContentDataService(CONTENT_TV, CONTENT_TYPE_TOP_RATED, TMDB_API_KEY, LANGUAGE)
//                .enqueue(new Callback<Result>() {
//                    @Override
//                    public void onResponse(Call<Result> call, Response<Result> response) {
//                        if (response.isSuccessful()) {
//                            Result tvItem = response.body();
//                            if (tvItem != null && tvItem.getResults() != null) {
//                                callback.getTvTopRatedContentSuccess(tvItem);
//                            } else {
//                                callback.getTvTopRatedContentFailure("FAILURE");
//                            }
//                        } else {
//                            callback.getTvTopRatedContentFailure("FAILURE");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Result> call, Throwable t) {
//                        callback.getTvTopRatedContentFailure(t.getMessage());
//                    }
//                });
    }

    @Override
    public void getSeasonTvListRepo(int tvId, final OnTaskCompletion.GetTvSeasonCompletion callback) {
//        mServiceApi.getTvSeasonDataService(tvId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<ResultResponse>() {
//            @Override
//            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
//                if (response.isSuccessful()) {
//                    ResultResponse tvSeason = response.body();
//                    if (tvSeason != null && tvSeason.getSeasons() != null) {
//                        callback.getTvSeasonContentSuccess(tvSeason);
//                    } else {
//                        callback.getTvSeasonContentFailure("FAILURE");
//                    }
//                } else {
//                    callback.getTvSeasonContentFailure("FAILURE");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResultResponse> call, Throwable t) {
//                callback.getTvSeasonContentFailure(t.getMessage());
//            }
//        });
    }

    @Override
    public void getLatestTvRepo(final OnTaskCompletion.GetLatestTvCompletion callback) {
//        mServiceApi.getContentDataService(CONTENT_TV, CONTENT_TYPE_LATEST, TMDB_API_KEY, LANGUAGE)
//                .enqueue(new Callback<Result>() {
//                    @Override
//                    public void onResponse(Call<Result> call, Response<Result> response) {
//                        if (response.isSuccessful()) {
//                            Result latestTv = response.body();
//                            if (latestTv != null && latestTv.getResults() != null) {
//                                callback.getLatestTvSuccess(latestTv);
//                            } else {
//                                callback.getLatestTvFailure("FAILURE");
//                            }
//                        } else {
//                            callback.getLatestTvFailure("FAILURE");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Result> call, Throwable t) {
//                        callback.getLatestTvFailure(t.getMessage());
//                    }
//                });
    }

    private void insertMovie(final Result item, final String movieType) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (!mMovieDao.getMovie(movieType).isEmpty()) {
//                    mMovieDao.deleteMovies(movieType);
//                }
//                for (final ResultResponse info : item.getResults()) {
//                    info.setType(movieType);
//                    info.setGenre(info.getMovieGenre());
//                    mMovieDao.insertMovie(info);
//                }
//            }
//        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

}
