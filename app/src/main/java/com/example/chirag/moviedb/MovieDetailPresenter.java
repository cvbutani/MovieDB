package com.example.chirag.moviedb;

import com.example.chirag.moviedb.data.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;
import com.example.chirag.moviedb.model.TrailerItem;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private RemoteRepository mRemoteRepository;

    private MovieDetailContract.View mCallback;

    MovieDetailPresenter() {
        mRemoteRepository = RemoteRepository.getInstance();
    }

    @Override
    public void getTrailerList(int movieId) {

        mRemoteRepository.getTrailerList(movieId, new OnTaskCompletion.OnGetTrailerCompletion() {
            @Override
            public void onTrailerItemSuccess(TrailerItem data) {
                mCallback.onTrailerListSuccess(data);
            }

            @Override
            public void onTrailerItemFailure(String errorMessage) {
                mCallback.onTrailerListFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(MovieDetailContract.View view, int movieId) {
        mCallback = view;
        getTrailerList(movieId);
    }
}
