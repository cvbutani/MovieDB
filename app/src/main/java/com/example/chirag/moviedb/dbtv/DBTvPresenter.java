package com.example.chirag.moviedb.dbtv;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.util.AppExecutors;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DBTvPresenter implements DBTvContract.Presenter {

    private DBTvContract.View mCallback;

    private Repository mRepository;

    public DBTvPresenter(Context context, boolean isConnected) {

        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        this.mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getPopularTv() {
        mRepository.getPopularTvData(new OnTaskCompletion.OnGetPopularTvCompletion() {
            @Override
            public void onPopularTvSuccess(Movies data) {
                mCallback.onPopularTvSuccess(data);
            }

            @Override
            public void onPopularTvFailure(String errorMessage) {
                mCallback.onPopularTvFailure(errorMessage);
            }
        });
    }

    @Override
    public void getTvTopRated() {
        mRepository.getTVTopRated(new OnTaskCompletion.GetTopRatedTvCompletion() {
            @Override
            public void getTvTopRatedContentSuccess(Movies data) {
                mCallback.getTVTopRatedContentSuccess(data);
            }

            @Override
            public void getTvTopRatedContentFailure(String errorMessage) {
                mCallback.getTVTopRatedContentFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DBTvContract.View view) {
        this.mCallback = view;
        getPopularTv();
        getTvTopRated();
    }
}
