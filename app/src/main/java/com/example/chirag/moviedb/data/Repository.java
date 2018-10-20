package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.remote.RemoteService;


/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class Repository implements DataContract {

    private static Repository sRepository;

    private static RemoteService mRemoteService;

    private static LocalService mLocalService;

    private static Boolean isConnected;

    private Repository() {
    }

    public static Repository getInstance(Boolean connection, LocalService localService, RemoteService remoteService) {
        if (sRepository == null) {
            sRepository = new Repository();
            mRemoteService = remoteService;
            mLocalService = localService;
        }
        isConnected = connection;
        return sRepository;
    }

    @Override
    public void getMovieInfoData(int movieId, OnTaskCompletion.OnGetMovieInfoCompletion callback) {
        if (isConnected) {
            mRemoteService.getMovieInfoRepo(movieId, callback);
        } else {
            mLocalService.getMovieInfoRepo(movieId, callback);
        }
    }

    @Override
    public void getTvInfoData(int tvId, OnTaskCompletion.OnGetTvInfoCompletion callback) {
        if (isConnected) {
            mRemoteService.getTvInfoRepo(tvId, callback);
        } else {
            mLocalService.getTvInfoRepo(tvId, callback);
        }
    }

    @Override
    public void getPopularMoviesData(OnTaskCompletion.OnGetMovieCompletion callback) {
        if (isConnected) {
            mRemoteService.getPopularMoviesRepo(callback);
        } else {
            mLocalService.getPopularMoviesRepo(callback);
        }
    }

    @Override
    public void getNowPlayingMoviesData(OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        if (isConnected) {
            mRemoteService.getNowPlayingMoviesRepo(callback);
        } else {
            mLocalService.getNowPlayingMoviesRepo(callback);
        }
    }

    @Override
    public void getTopRatedMoviesData(OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        if (isConnected) {
            mRemoteService.getTopRatedMoviesRepo(callback);
        } else {
            mLocalService.getTopRatedMoviesRepo(callback);
        }
    }

    @Override
    public void getUpcomingMoviesData(OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        if (isConnected) {
            mRemoteService.getUpcomingMoviesRepo(callback);
        } else {
            mLocalService.getUpcomingMoviesRepo(callback);
        }
    }

    @Override
    public void getSimilarMoviesData(int movieId, OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mRemoteService.getSimilarMoviesRepo(movieId, callback);
    }

    @Override
    public void getTrailerListData(int movieId, OnTaskCompletion.OnGetTrailerCompletion callback) {
        if (isConnected) {
            mRemoteService.getTrailersRepo(movieId, callback);
        } else {
            mLocalService.getTrailersRepo(movieId, callback);
        }
    }

    @Override
    public void getReviewsListData(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {
        if (isConnected) {
            mRemoteService.getReviewsRepo(movieId, callback);
        } else {
            mLocalService.getReviewsRepo(movieId, callback);
        }
    }

    @Override
    public void getPopularTvData(OnTaskCompletion.OnGetPopularTvCompletion callback) {
        if (isConnected) {
            mRemoteService.getPopularTvRepo(callback);
        } else {
            mLocalService.getPopularTvRepo(callback);
        }

    }

    @Override
    public void getTopRatedTvData(OnTaskCompletion.GetTopRatedTvCompletion callback) {
        if (isConnected) {
            mRemoteService.getTopRatedTvRepo(callback);
        } else {
            mLocalService.getTopRatedTvRepo(callback);
        }
    }

    @Override
    public void getLatestTvData(OnTaskCompletion.GetLatestTvCompletion callback) {
        if (isConnected) {
            mRemoteService.getLatestTvRepo(callback);
        } else {
            mLocalService.getLatestTvRepo(callback);
        }
    }
}
