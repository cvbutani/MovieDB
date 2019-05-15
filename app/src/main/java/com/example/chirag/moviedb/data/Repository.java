package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.remote.RemoteService;

import java.util.List;

import io.reactivex.Flowable;


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

    public static Repository getInstance(Boolean connection, LocalService localService,
                                         RemoteService remoteService) {
        if (sRepository == null) {
            sRepository = new Repository();
            mRemoteService = remoteService;
            mLocalService = localService;
        }
        isConnected = true;
        return sRepository;
    }

    @Override
    public void getTvInfoData(int tvId, OnTaskCompletion.OnGetTvInfoCompletion callback) {
        if (isConnected) {
            mRemoteService.getTvInfoRepo(tvId, callback);
        } else {
            mLocalService.getTvInfoRepo(tvId, callback);
        }
    }

    public Flowable<List<ResultResponse>> getHomeScreenData(String movieType, String contentType) {
        return mRemoteService.getMovieHomeScreenData(movieType, contentType);
    }

    public Flowable<ResultResponse> getMovieDetailData(int movieId) {
        return mRemoteService.getMovieDetailData(movieId);
    }

    @Override
    public void getSimilarMoviesData(int movieId,
                                     OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mRemoteService.getSimilarMoviesRepo(movieId, callback);
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
    public void getUserSignInInfo(OnTaskCompletion.GetUserSignInCompletion callback) {
        mLocalService.getUserSignInInfo(callback);
    }

    @Override
    public void insertUserSignInInfo(User user) {
        mLocalService.insertUserSignInInfo(user);
    }

    @Override
    public void getUserAccountInfo(String emailId,
                                   final OnTaskCompletion.GetUserAccountCompletion callback) {
        mLocalService.getUserAccountInfo(emailId, callback);
    }

    @Override
    public void updateUserAccount(User user) {
        mLocalService.updateUserAccount(user);
    }

    @Override
    public void updateTMDBData(Favourite info) {
        mLocalService.updateTMDBRepo(info);
    }

    @Override
    public void getFavouriteTMDBData(String emailId,
                                     OnTaskCompletion.GetFavouriteTMDBCompletion callback) {
        mLocalService.getFavouriteTMDBRepo(emailId, callback);
    }


}
