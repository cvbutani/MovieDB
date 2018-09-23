package com.example.chirag.moviedb.dbtv;

import com.example.chirag.moviedb.data.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;
import com.example.chirag.moviedb.model.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public class DBTvPresenter implements DBTvContract.Presenter {

    private DBTvContract.View mCallback;

    private RemoteRepository mRemoteRepo;

    DBTvPresenter() {
        this.mRemoteRepo = RemoteRepository.getInstance();
    }

    @Override
    public void getPopularTv() {
        mRemoteRepo.getPopularTvData(new OnTaskCompletion.OnGetPopularTvCompletion() {
            @Override
            public void onPopularTvSuccess(HeaderItem data) {
                mCallback.onPopularTvSuccess(data);
            }

            @Override
            public void onPopularTvFailure(String errorMessage) {
                mCallback.onPopularTvFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DBTvContract.View view) {
        this.mCallback = view;
        getPopularTv();
    }
}
