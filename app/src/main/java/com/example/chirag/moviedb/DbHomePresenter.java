package com.example.chirag.moviedb;

import com.example.chirag.moviedb.data.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;
import com.example.chirag.moviedb.model.childitem.ChildItem;
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
            public void onHeaderItemSuccess(HeaderItem data) {
                mCallback.onHeaderResultSuccess(data);
            }

            @Override
            public void onHeaderItemFailure(String errorMessage) {
                mCallback.onHeaderResultFailure(errorMessage);
            }

            @Override
            public void onChildItemSuccess(ChildItem data) {
                mCallback.onChildResultSuccess(data);
            }

            @Override
            public void onChildItemFailure(String errorMessage) {
                mCallback.onChildResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DbHomeContract.View view) {
        mCallback = view;
        getData();
    }
}
