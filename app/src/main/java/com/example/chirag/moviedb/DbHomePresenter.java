package com.example.chirag.moviedb;

import com.example.chirag.moviedb.data.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;
import com.example.chirag.moviedb.model.headeritem.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public class DbHomePresenter implements DbHomeContract.Presenter {

    private DbHomeContract.View mCallback;

    private RemoteRepository mRemoteRepository;

    DbHomePresenter() {
        mRemoteRepository = RemoteRepository.getInstance();
    }

    @Override
    public void getData() {
        mRemoteRepository.getNewBatchOfData(new OnTaskCompletion() {
            @Override
            public void onSuccess(HeaderItem data) {
                mCallback.onResult(data);
            }

            @Override
            public void onFailure(String errorMessage) {
                mCallback.onError(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DbHomeContract.View view) {
        mCallback = view;
        getData();
    }
}
