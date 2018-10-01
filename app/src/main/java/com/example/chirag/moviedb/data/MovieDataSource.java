package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 29/09/18.
 */
public interface MovieDataSource {

    void getPopularMovies(final OnTaskCompletion.OnGetMovieCompletion callback);

    void getNowPlayingMovies(final OnTaskCompletion.OnGetNowPlayingCompletion callback);

    void getTopRatedMovies(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback);

    void getUpcomingMovies(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback);

    void getTrailers(int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback);

    void getReviews(int movieId, final OnTaskCompletion.OnGetReviewCompletion callback);
}
