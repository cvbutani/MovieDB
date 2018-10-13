package com.example.chirag.moviedb.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.remote.RemoteService;


/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteRepository implements DataContract {

    private static RemoteRepository sRemoteRepository;

    private static RemoteService mRemoteService;

    private static LocalService mLocalService;

    private static Boolean isConnected;

    private RemoteRepository() {
    }

    public static RemoteRepository getInstance(Boolean connection, LocalService localService, RemoteService remoteService) {
        if (sRemoteRepository == null) {
            sRemoteRepository = new RemoteRepository();
            mRemoteService = remoteService;
            mLocalService = localService;
        }
        isConnected = connection;
        return sRemoteRepository;
    }

    @Override
    public void getPopularMoviesData(OnTaskCompletion.OnGetMovieCompletion callback) {
        if (isConnected) {
            mRemoteService.getPopularMovies(callback);
        } else {
            mLocalService.getPopularMovies(callback);
        }
    }

    @Override
    public void getNowPlayingMoviesData(OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        if (isConnected) {
            mRemoteService.getNowPlayingMovies(callback);
        } else {
            mLocalService.getNowPlayingMovies(callback);
        }
    }

    @Override
    public void getTopRatedMoviesData(OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        if (isConnected) {
            mRemoteService.getTopRatedMovies(callback);
        } else {
            mLocalService.getTopRatedMovies(callback);
        }
    }

    @Override
    public void getUpcomingMoviesData(OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        if (isConnected) {
            mRemoteService.getUpcomingMovies(callback);
        } else {
            mLocalService.getUpcomingMovies(callback);
        }
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
        if (isConnected) {
            mRemoteService.getTrailers(movieId, callback);
        } else {
            mLocalService.getTrailers(movieId, callback);
        }
    }

    @Override
    public void getReviews(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {
        if (isConnected) {
            mRemoteService.getReviews(movieId, callback);
        } else {
            mLocalService.getReviews(movieId, callback);
        }
    }

    @Override
    public void getPopularTvData(OnTaskCompletion.OnGetPopularTvCompletion callback) {
        mRemoteService.getPopularTv(callback);
    }
}
