package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface DataContract {
    void getMovieInfoData(int movieId, final OnTaskCompletion.OnGetMovieInfoCompletion callback);

    void getPopularMoviesData(final OnTaskCompletion.OnGetMovieCompletion callback);

    void getNowPlayingMoviesData(final OnTaskCompletion.OnGetNowPlayingCompletion callback);

    void getTopRatedMoviesData(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback);

    void getUpcomingMoviesData(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback);

    void getSimilarMoviesData(int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback);

    void getMovieGenreListData(final OnTaskCompletion.OnGetGenresCompletion callback);

    void getTrailerListData(int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback);

    void getReviewsListData(int movieId, final OnTaskCompletion.OnGetReviewCompletion callback);

    void getPopularTvData(final OnTaskCompletion.OnGetPopularTvCompletion callback);

    void getTVGenreListData(final OnTaskCompletion.OnGetTVGenreCompletion callback);

    void getTopRatedTvData(final OnTaskCompletion.GetTopRatedTvCompletion callback);

    void getSeasonTvListData(int tvId, final OnTaskCompletion.GetTvSeasonCompletion callback);

    void getLatestTvData(final OnTaskCompletion.GetLatestTvCompletion callback);
}
