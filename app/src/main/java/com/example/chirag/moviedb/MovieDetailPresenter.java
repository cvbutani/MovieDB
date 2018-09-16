package com.example.chirag.moviedb;

import com.example.chirag.moviedb.data.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;
import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.example.chirag.moviedb.model.Reviews;
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
    public void getMovieData(final int movieId) {
        mRemoteRepository.getPopularMoviesData(new OnTaskCompletion.OnGetMovieCompletion() {
            @Override
            public void onHeaderItemSuccess(HeaderItem data) {
                mCallback.onMovieDetail(data, movieId);
            }

            @Override
            public void onHeaderItemFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getGenreItem(final ResultHeaderItem item) {
        mRemoteRepository.getGenreList(new OnTaskCompletion.OnGetGenresCompletion() {
            @Override
            public void onGenreListSuccess(GenreItem data) {
                mCallback.onGenreDetail(data, item);
            }

            @Override
            public void onGenreListFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getReviews(int movieId) {
        mRemoteRepository.getReviews(movieId, new OnTaskCompletion.OnGetReviewCompletion() {
            @Override
            public void onReviewResponseSuccess(Reviews data) {
                mCallback.onReviewDetail(data);
            }

            @Override
            public void onReviewResponseFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void attachView(MovieDetailContract.View view, int movieId) {
        mCallback = view;
        getTrailerList(movieId);
        getMovieData(movieId);
        getReviews(movieId);
    }
}
