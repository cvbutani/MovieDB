package com.example.chirag.moviedb.data.local;

import com.example.chirag.moviedb.data.MovieDataSource;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
public class LocalService implements MovieDataSource {



    @Override
    public void getPopularMovies(OnTaskCompletion.OnGetMovieCompletion callback) {

    }

    @Override
    public void getNowPlayingMovies(OnTaskCompletion.OnGetNowPlayingCompletion callback) {

    }

    @Override
    public void getTopRatedMovies(OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {

    }

    @Override
    public void getUpcomingMovies(OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {

    }

    @Override
    public void getTrailers(int movieId, OnTaskCompletion.OnGetTrailerCompletion callback) {

    }

    @Override
    public void getReviews(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {

    }
}
