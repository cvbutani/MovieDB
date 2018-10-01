package com.example.chirag.moviedb.data.remote;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.util.AppExecutors;

;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteRepository implements DataContract {

    private static RemoteRepository sRemoteRepository;

    private static RemoteService mRemoteService;

    private static LocalService mLocalService;

    private RemoteRepository() {
    }

    public static RemoteRepository getInstance(LocalService localService, RemoteService remoteService) {
        if (sRemoteRepository == null) {
            sRemoteRepository = new RemoteRepository();
            mRemoteService = remoteService;
            mLocalService = localService;
        }
        return sRemoteRepository;
    }

    @Override
    public void getPopularMoviesData(OnTaskCompletion.OnGetMovieCompletion callback) {
        //       mRemoteService.getPopularMovies(callback);
        mLocalService.getPopularMovies(callback);

    }

    @Override
    public void getNowPlayingMoviesData(OnTaskCompletion.OnGetNowPlayingCompletion callback) {
//        mRemoteService.getNowPlayingMovies(callback);
        mLocalService.getNowPlayingMovies(callback);
    }

    @Override
    public void getTopRatedMoviesData(OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
//        mRemoteService.getTopRatedMovies(callback);
        mLocalService.getTopRatedMovies(callback);
    }

    @Override
    public void getUpcomingMoviesData(OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
//        mRemoteService.getUpcomingMovies(callback);
        mLocalService.getUpcomingMovies(callback);
    }

    @Override
    public void getSimilarMoviesData(int movieId, OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mRemoteService.getSimilarMovies(movieId, callback);
    }

    @Override
    public void getGenreList(OnTaskCompletion.OnGetGenresCompletion callback) {
        mRemoteService.getGenres(callback);
    }

    @Override
    public void getTrailerList(int movieId, OnTaskCompletion.OnGetTrailerCompletion callback) {
        mRemoteService.getTrailers(movieId, callback);
    }

    @Override
    public void getReviews(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {
        mRemoteService.getReviews(movieId, callback);
    }

    @Override
    public void getPopularTvData(OnTaskCompletion.OnGetPopularTvCompletion callback) {
        mRemoteService.getPopularTv(callback);
    }


}
