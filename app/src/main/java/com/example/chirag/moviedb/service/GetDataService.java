package com.example.chirag.moviedb.service;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface GetDataService {

    @GET("{content_type}/{movie_type}")
    Call<Movies> getContentInfo(
            @Path("content_type") String contentType,
            @Path("movie_type") String movieType,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("genre/{content_type}/list")
    Call<Genre> getGenreList(
            @Path("content_type") String contentType,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Call<Trailer> getTrailerList(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/reviews")
    Call<Reviews> getReviewList(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/similar")
    Call<Movies> getSimilarMovieList(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("tv/{tv_id}")
    Call<MovieResponse> getTvSeason(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
