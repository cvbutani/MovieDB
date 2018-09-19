package com.example.chirag.moviedb.data;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface DataContract {
    void getPopularMoviesData(final OnTaskCompletion.OnGetMovieCompletion callback);

    void getNowPlayingMoviesData(final OnTaskCompletion.OnGetNowPlayingCompletion callback);

    void getTopRatedMoviesData(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback);

    void getGenreList(final OnTaskCompletion.OnGetGenresCompletion callback);

    void getTrailerList(int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback);

    void getReviews(int movieId, final OnTaskCompletion.OnGetReviewCompletion callback);
}
