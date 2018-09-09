package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.GenreResponse;
import com.example.chirag.moviedb.service.GetDataService;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.network.ServiceInstance;

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

    void getGenres(final OnTaskCompletion.OnGetGenresCompletion callback) {
        mServiceApi.getGenreList(TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<GenreResponse>() {
                    @Override
                    public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                        if (response.isSuccessful()) {
                            GenreResponse genreResponse = response.body();
                            if (genreResponse != null && genreResponse.getGenres() != null) {
                                callback.onGenreListSuccess(genreResponse);
                            } else {
                                callback.onGenreListFailure("SOMETHING WENT WRONG WHILE GETTING GENRE");
                            }
                        } else {
                            callback.onGenreListFailure("SOMETHING WENT WRONG WHILE GETTING GENRE");
                        }
                    }

                    @Override
                    public void onFailure(Call<GenreResponse> call, Throwable t) {
                        callback.onGenreListFailure(t.getMessage());

                    }
                });
    }
}
