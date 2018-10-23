package com.example.chirag.moviedb.favourite;

import android.content.Context;

import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 22/10/18.
 */
public class FavouritePresenter implements FavouriteContract.Presenter{

    private FavouriteContract.View mCallback;

    private Repository mRepository;

    FavouritePresenter(Context context, boolean isConnected) {
        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).userDao());
        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getFavouriteTMDB(String emailId) {
        mRepository.getFavouriteTMDBData(emailId, new OnTaskCompletion.GetFavouriteTMDBCompletion() {
            @Override
            public void getFavouriteTMDBSuccess(List<Favourite> data) {
                mCallback.getFavouriteTMDBInfo(data);
            }

            @Override
            public void getFavouriteTMDBFailure(String errorMessage) {
                mCallback.getFavouriteTMDBFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(FavouriteContract.View view, String emailId) {
        mCallback = view;
        getFavouriteTMDB(emailId);
    }
}
