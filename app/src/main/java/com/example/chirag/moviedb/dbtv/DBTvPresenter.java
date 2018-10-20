package com.example.chirag.moviedb.dbtv;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;
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
                LocalDatabase.getInstance(context).localDao());

        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao());

        this.mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getPopularTv() {
        mRepository.getPopularTvData(new OnTaskCompletion.OnGetPopularTvCompletion() {
            @Override
            public void getPopularTvSuccess(Result data) {
                mCallback.getPopularTvHome(data);
            }

            @Override
            public void getPopularTvFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getTopRatedTv() {
        mRepository.getTopRatedTvData(new OnTaskCompletion.GetTopRatedTvCompletion() {
            @Override
            public void getTvTopRatedContentSuccess(Result data) {
                mCallback.getTopRatedTvHome(data);
            }

            @Override
            public void getTvTopRatedContentFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DBTvContract.View view) {
        this.mCallback = view;
        getPopularTv();
        getTopRatedTv();
    }
}
