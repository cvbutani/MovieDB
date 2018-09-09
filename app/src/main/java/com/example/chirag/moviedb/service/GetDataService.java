package com.example.chirag.moviedb.service;

import com.example.chirag.moviedb.model.GenreResponse;
import com.example.chirag.moviedb.model.HeaderItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface GetDataService {

    @GET("movie/popular")
    Call<HeaderItem> getPopularMoviesInfo(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("genre/movie/list")
    Call<GenreResponse> getGenreList(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
