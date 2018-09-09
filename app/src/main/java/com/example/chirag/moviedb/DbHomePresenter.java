package com.example.chirag.moviedb;

import com.example.chirag.moviedb.data.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;

import com.example.chirag.moviedb.model.GenreResponse;
import com.example.chirag.moviedb.model.HeaderItem;

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
    public void getPopularMovies() {
        mRemoteRepository.getPopularMoviesData(new OnTaskCompletion.OnGetMovieCompletion() {
            @Override
            public void onHeaderItemSuccess(HeaderItem data) {
                mCallback.onHeaderResultSuccess(data);
            }

            @Override
            public void onHeaderItemFailure(String errorMessage) {
                mCallback.onHeaderResultFailure(errorMessage);
            }

        });
    }

    @Override
    public void getGenreList() {
        mRemoteRepository.getGenreList(new OnTaskCompletion.OnGetGenresCompletion() {
            @Override
            public void onGenreListSuccess(GenreResponse data) {
                mCallback.onGenreListSuccess(data);
            }

            @Override
            public void onGenreListFailure(String errorMessage) {
                mCallback.onGenreListFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(DbHomeContract.View view) {
        mCallback = view;
        getPopularMovies();
        getGenreList();
    }
}
