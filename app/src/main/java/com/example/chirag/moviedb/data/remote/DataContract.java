package com.example.chirag.moviedb.data.remote;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface DataContract {
    void getPopularMoviesData(final OnTaskCompletion.OnGetMovieCompletion callback);

    void getNowPlayingMoviesData(final OnTaskCompletion.OnGetNowPlayingCompletion callback);

    void getTopRatedMoviesData(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback);

    void getUpcomingMoviesData(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback);

    void getSimilarMoviesData(int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback);

    void getGenreList(final OnTaskCompletion.OnGetGenresCompletion callback);

    void getTrailerList(int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback);

    void getReviews(int movieId, final OnTaskCompletion.OnGetReviewCompletion callback);

    void getPopularTvData(final OnTaskCompletion.OnGetPopularTvCompletion callback);
}
