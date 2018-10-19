package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 29/09/18.
 */
public interface RepositoryContract {

    void getMovieInfoRepo(int movieId, final OnTaskCompletion.OnGetMovieInfoCompletion callback);

    void getPopularMoviesRepo(final OnTaskCompletion.OnGetMovieCompletion callback);

    void getNowPlayingMoviesRepo(final OnTaskCompletion.OnGetNowPlayingCompletion callback);

    void getTopRatedMoviesRepo(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback);

    void getUpcomingMoviesRepo(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback);

    void getTrailersRepo(int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback);

    void getReviewsRepo(int movieId, final OnTaskCompletion.OnGetReviewCompletion callback);

    void getMovieGenresRepo(final OnTaskCompletion.OnGetGenresCompletion callback);

    void getSimilarMoviesRepo(int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback);

    void getPopularTvRepo(final OnTaskCompletion.OnGetPopularTvCompletion callback);

    void getTvGenresRepo(final OnTaskCompletion.OnGetTVGenreCompletion callback);

    void getTopRatedTvRepo(final OnTaskCompletion.GetTopRatedTvCompletion callback);

    void getSeasonTvListRepo(int tvId, final OnTaskCompletion.GetTvSeasonCompletion callback);

    void getLatestTvRepo(final OnTaskCompletion.GetLatestTvCompletion callback);
}
