package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface DataContract {
    void getMovieInfoData(int movieId, final OnTaskCompletion.OnGetMovieInfoCompletion callback);

    void getTvInfoData(int tvId, final OnTaskCompletion.OnGetTvInfoCompletion callback);

    void getPopularMoviesData(final OnTaskCompletion.OnGetMovieCompletion callback);

    void getNowPlayingMoviesData(final OnTaskCompletion.OnGetNowPlayingCompletion callback);

    void getTopRatedMoviesData(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback);

    void getUpcomingMoviesData(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback);

    void getSimilarMoviesData(int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback);

    void getTrailerListData(int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback);

    void getReviewsListData(int movieId, final OnTaskCompletion.OnGetReviewCompletion callback);

    void getPopularTvData(final OnTaskCompletion.OnGetPopularTvCompletion callback);

    void getTopRatedTvData(final OnTaskCompletion.GetTopRatedTvCompletion callback);

    void getUserSignInInfo(final OnTaskCompletion.GetUserSignInCompletion callback);

    void insertUserSignInInfo(User user);

    void getUserAccountInfo(String emailId, final OnTaskCompletion.GetUserAccountCompletion callback);

    void updateUserAccount(User user);

    void updateTMDBData(Favourite info);

    void getFavouriteTMDBData(String emailId, final OnTaskCompletion.GetFavouriteTMDBCompletion callback);
}
