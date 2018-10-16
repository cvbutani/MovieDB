package com.example.chirag.moviedb.data.remote;

import android.support.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.MovieDao;
import com.example.chirag.moviedb.data.local.dao.ReviewDao;
import com.example.chirag.moviedb.data.local.dao.TrailerDao;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.network.ServiceInstance;
import com.example.chirag.moviedb.service.GetDataService;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteService implements RepositoryContract {

    private static final String TMDB_API_KEY = "51b4547daeeca9a0a1dec36a7013b1ad";
    private static final String LANGUAGE = "en-US";

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
    public void getPopularMovies(final OnTaskCompletion.OnGetMovieCompletion callback) {

        Call<Movies> call = mServiceApi.getPopularMoviesInfo(TMDB_API_KEY, LANGUAGE);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    Movies item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onHeaderItemSuccess(item);
                        insertMovie(item, "POPULAR");
                    } else {
                        callback.onHeaderItemFailure("FAILURE");
                    }
                } else {
                    callback.onHeaderItemFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                callback.onHeaderItemFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getNowPlayingMovies(final OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        mServiceApi.getNowPlayingInfo(TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        if (response.isSuccessful()) {
                            Movies item = response.body();
                            if (item != null && item.getResults() != null) {
                                callback.onNowPlayingMovieSuccess(item);
                                insertMovie(item, "NOWPLAYING");
                            } else {
                                callback.onNowPlayingMovieFailure("FAILURE");
                            }
                        } else {
                            callback.onNowPlayingMovieFailure("FAILURE");
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        callback.onNowPlayingMovieFailure(t.getMessage());
                    }
                });
    }

    @Override
    public void getTopRatedMovies(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        mServiceApi.getTopRatedInfo(TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    Movies item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onTopRatedMovieSuccess(item);
                        insertMovie(item, "TopRated");
                    } else {
                        callback.onTopRatedMovieFailure("FAILURE");
                    }
                } else {
                    callback.onTopRatedMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                callback.onTopRatedMovieFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getUpcomingMovies(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        mServiceApi.getUpcomingInfo(TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    Movies item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onUpcomingMovieSuccess(item);
                        insertMovie(item, "Upcoming");
                    } else {
                        callback.onUpcomingMovieFailure("FAILURE");
                    }
                } else {
                    callback.onUpcomingMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                callback.onUpcomingMovieFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getGenres(final OnTaskCompletion.OnGetGenresCompletion callback) {
        mServiceApi.getGenreList(TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<Genre>() {
                    @Override
                    public void onResponse(Call<Genre> call, Response<Genre> response) {
                        if (response.isSuccessful()) {
                            Genre genreItem = response.body();
                            if (genreItem != null && genreItem.getGenreResponses() != null) {
                                callback.onGenreListSuccess(genreItem);
                            } else {
                                callback.onGenreListFailure("SOMETHING WENT WRONG WHILE GETTING GENRE");
                            }
                        } else {
                            callback.onGenreListFailure("SOMETHING WENT WRONG WHILE GETTING GENRE");
                        }
                    }

                    @Override
                    public void onFailure(Call<Genre> call, Throwable t) {
                        callback.onGenreListFailure(t.getMessage());

                    }
                });
    }

    @Override
    public void getTrailers(final int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback) {
        mServiceApi.getTrailerList(movieId, TMDB_API_KEY).enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                if (response.isSuccessful()) {
                    final Trailer trailerItem = response.body();
                    if (trailerItem != null && trailerItem.getResults() != null) {
                        callback.onTrailerItemSuccess(trailerItem);
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
                        callback.onTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                    }
                } else {
                    callback.onTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                }
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                callback.onTrailerItemFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getReviews(final int movieId, final OnTaskCompletion.OnGetReviewCompletion callback) {
        mServiceApi.getReviewList(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if (response.isSuccessful()) {
                    final Reviews reviews = response.body();
                    if (reviews != null && reviews.getResults() != null) {
                        callback.onReviewResponseSuccess(reviews);
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
                        callback.onReviewResponseFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                    }
                } else {
                    callback.onReviewResponseFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {
                callback.onReviewResponseFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getSimilarMovies(int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mServiceApi.getSimilarMovieList(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    Movies item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onSimilarMovieSuccess(item);
                    } else {
                        callback.onSimilarMovieFailure("FAILURE");
                    }
                } else {
                    callback.onSimilarMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                callback.onSimilarMovieFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getPopularTv(final OnTaskCompletion.OnGetPopularTvCompletion callback) {
        mServiceApi.getPopularTvInfo(TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    Movies item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onPopularTvSuccess(item);
                    } else {
                        callback.onPopularTvFailure("FAILURE");
                    }
                } else {
                    callback.onPopularTvFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                callback.onPopularTvFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getTVGenreList(final OnTaskCompletion.OnGetTVGenreCompletion callback) {
        mServiceApi.getTVGenreList(TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                if (response.isSuccessful()) {
                    Genre item = response.body();
                    if (item != null && item.getGenreResponses() != null) {
                        callback.onTVGenreSuccess(item);
                    } else {
                        callback.onTVGenreFailure("FAILURE");
                    }
                } else {
                    callback.onTVGenreFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {
                callback.onTVGenreFailure(t.getMessage());
            }
        });
    }

    private void insertMovie(final Movies item, final String movieType) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mMovieDao.getMovie(movieType).isEmpty()) {
                    mMovieDao.deleteMovies(movieType);
                }
                for (final MovieResponse info : item.getResults()) {
                    info.setType(movieType);
                    info.setGenre(info.getMovieGenre());
                    mMovieDao.insertMovie(info);
                }
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

}
