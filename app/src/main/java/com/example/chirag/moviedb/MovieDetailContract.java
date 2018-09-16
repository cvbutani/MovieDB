package com.example.chirag.moviedb;

import com.example.chirag.moviedb.model.TrailerItem;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public interface MovieDetailContract {

    interface View {
        void onTrailerListSuccess(TrailerItem data);

        void onTrailerListFailure(String errorMessage);
    }

    interface Presenter {

        void getTrailerList(int movieId);

        void attachView(MovieDetailContract.View view, int movieId);
    }

}
