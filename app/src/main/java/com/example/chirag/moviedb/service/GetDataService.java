package com.example.chirag.moviedb.service;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.Reviews;
import com.example.chirag.moviedb.model.TrailerItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("movie/now_playing")
    Call<HeaderItem> getNowPlayingInfo(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("genre/movie/list")
    Call<GenreItem> getGenreList(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Call<TrailerItem> getTrailerList(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/reviews")
    Call<Reviews> getReviewList(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
