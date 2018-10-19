package com.example.chirag.moviedb.service;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TvInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface GetDataService {

    @GET("{result}/{movie_type}")
    Call<Result> getContentDataService(
            @Path("result") String result,
            @Path("movie_type") String movieType,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}")
    Call<MovieInfo> getMovieInfoDataService(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("tv/{tv_id}")
    Call<TvInfo> getTvInfoDataService(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("genre/{content_type}/list")
    Call<Genre> getGenreDataService(
            @Path("content_type") String contentType,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Call<Trailer> GetTrailerDataService(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/reviews")
    Call<Reviews> getReviewDataService(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/similar")
    Call<Result> getSimilarMovieDataService(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("tv/{tv_id}")
    Call<ResultResponse> getTvSeasonDataService(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
