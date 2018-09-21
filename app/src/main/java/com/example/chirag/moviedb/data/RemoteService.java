package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.Reviews;
import com.example.chirag.moviedb.model.TrailerItem;
import com.example.chirag.moviedb.network.ServiceInstance;
import com.example.chirag.moviedb.service.GetDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteService {

    private static final String TMDB_API_KEY = "51b4547daeeca9a0a1dec36a7013b1ad";
    private static final String LANGUAGE = "en-US";

    private GetDataService mServiceApi;

    RemoteService() {
        mServiceApi = ServiceInstance.getServiceInstance().create(GetDataService.class);
    }

    void getPopularMovies(final OnTaskCompletion.OnGetMovieCompletion callback) {

        Call<HeaderItem> call = mServiceApi.getPopularMoviesInfo(TMDB_API_KEY, LANGUAGE);

        call.enqueue(new Callback<HeaderItem>() {
            @Override
            public void onResponse(Call<HeaderItem> call, Response<HeaderItem> response) {
                if (response.isSuccessful()) {
                    HeaderItem item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onHeaderItemSuccess(item);
                    } else {
                        callback.onHeaderItemFailure("FAILURE");
                    }
                } else {
                    callback.onHeaderItemFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<HeaderItem> call, Throwable t) {
                callback.onHeaderItemFailure(t.getMessage());
            }
        });
    }

    void getNowPlayingMovies(final OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        mServiceApi.getNowPlayingInfo(TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<HeaderItem>() {
                    @Override
                    public void onResponse(Call<HeaderItem> call, Response<HeaderItem> response) {
                        if (response.isSuccessful()) {
                            HeaderItem item = response.body();
                            if (item != null && item.getResults() != null) {
                                callback.onNowPlayingMovieSuccess(item);
                            } else {
                                callback.onNowPlayingMovieFailure("FAILURE");
                            }
                        } else {
                            callback.onNowPlayingMovieFailure("FAILURE");
                        }
                    }

                    @Override
                    public void onFailure(Call<HeaderItem> call, Throwable t) {
                        callback.onNowPlayingMovieFailure(t.getMessage());
                    }
                });
    }

    void getTopRatedMovies(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        mServiceApi.getTopRatedInfo(TMDB_API_KEY, LANGUAGE).enqueue(new Callback<HeaderItem>() {
            @Override
            public void onResponse(Call<HeaderItem> call, Response<HeaderItem> response) {
                if (response.isSuccessful()) {
                    HeaderItem item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onTopRatedMovieSuccess(item);
                    } else {
                        callback.onTopRatedMovieFailure("FAILURE");
                    }
                } else {
                    callback.onTopRatedMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<HeaderItem> call, Throwable t) {
                callback.onTopRatedMovieFailure(t.getMessage());
            }
        });
    }

    void getUpcomingMovies(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        mServiceApi.getUpcomingInfo(TMDB_API_KEY, LANGUAGE).enqueue(new Callback<HeaderItem>() {
            @Override
            public void onResponse(Call<HeaderItem> call, Response<HeaderItem> response) {
                if (response.isSuccessful()) {
                    HeaderItem item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.onUpcomingMovieSuccess(item);
                    } else {
                        callback.onUpcomingMovieFailure("FAILURE");
                    }
                } else {
                    callback.onUpcomingMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<HeaderItem> call, Throwable t) {
                callback.onUpcomingMovieFailure(t.getMessage());
            }
        });
    }

    void getGenres(final OnTaskCompletion.OnGetGenresCompletion callback) {
        mServiceApi.getGenreList(TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<GenreItem>() {
                    @Override
                    public void onResponse(Call<GenreItem> call, Response<GenreItem> response) {
                        if (response.isSuccessful()) {
                            GenreItem genreItem = response.body();
                            if (genreItem != null && genreItem.getResultGenreItems() != null) {
                                callback.onGenreListSuccess(genreItem);
                            } else {
                                callback.onGenreListFailure("SOMETHING WENT WRONG WHILE GETTING GENRE");
                            }
                        } else {
                            callback.onGenreListFailure("SOMETHING WENT WRONG WHILE GETTING GENRE");
                        }
                    }

                    @Override
                    public void onFailure(Call<GenreItem> call, Throwable t) {
                        callback.onGenreListFailure(t.getMessage());

                    }
                });
    }

    void getTrailers(int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback) {
        mServiceApi.getTrailerList(movieId, TMDB_API_KEY).enqueue(new Callback<TrailerItem>() {
            @Override
            public void onResponse(Call<TrailerItem> call, Response<TrailerItem> response) {
                if (response.isSuccessful()) {
                    TrailerItem trailerItem = response.body();
                    if (trailerItem != null && trailerItem.getResults() != null) {
                        callback.onTrailerItemSuccess(trailerItem);
                    } else {
                        callback.onTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                    }
                } else {
                    callback.onTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
                }
            }

            @Override
            public void onFailure(Call<TrailerItem> call, Throwable t) {
                callback.onTrailerItemFailure(t.getMessage());
            }
        });

    }

    void getReviews(int movieId, final OnTaskCompletion.OnGetReviewCompletion callback) {
        mServiceApi.getReviewList(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if (response.isSuccessful()) {
                    Reviews reviews = response.body();
                    if (reviews != null && reviews.getResults() != null) {
                        callback.onReviewResponseSuccess(reviews);
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

    void getSimilarMovies(int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mServiceApi.getSimilarMovieList(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<HeaderItem>() {
            @Override
            public void onResponse(Call<HeaderItem> call, Response<HeaderItem> response) {
                if (response.isSuccessful()) {
                    HeaderItem item = response.body();
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
            public void onFailure(Call<HeaderItem> call, Throwable t) {
                callback.onSimilarMovieFailure(t.getMessage());
            }
        });
    }
}
