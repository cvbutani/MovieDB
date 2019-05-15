package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 29/09/18.
 */
public interface RepositoryContract {

    void getMovieInfoRepo(int movieId, final OnTaskCompletion.OnGetMovieInfoCompletion callback);

    void getTvInfoRepo(int tvId, final OnTaskCompletion.OnGetTvInfoCompletion callback);

    void getSimilarMoviesRepo(int movieId,
                              final OnTaskCompletion.OnGetSimilarMovieCompletion callback);

    void getPopularTvRepo(final OnTaskCompletion.OnGetPopularTvCompletion callback);

    void getTopRatedTvRepo(final OnTaskCompletion.GetTopRatedTvCompletion callback);

    void getUserSignInInfo(final OnTaskCompletion.GetUserSignInCompletion callback);

    void insertUserSignInInfo(User user);

    void getUserAccountInfo(String emailId,
                            final OnTaskCompletion.GetUserAccountCompletion callback);

    void updateUserAccount(User user);

    void updateTMDBRepo(Favourite info);

    void getFavouriteTMDBRepo(String emailId,
                              final OnTaskCompletion.GetFavouriteTMDBCompletion callback);
}
