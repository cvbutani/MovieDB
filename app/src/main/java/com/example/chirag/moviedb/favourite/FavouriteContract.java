package com.example.chirag.moviedb.favourite;

import com.example.chirag.moviedb.data.model.TMDB;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 22/10/18.
 */
public interface FavouriteContract {

    interface View {
        void getFavouriteTMDBInfo(TMDB data);

        void getFavouriteTMDBFailure(String errorMessage);
    }

    interface Presenter {
        void getFavouriteTMDB(String emailId);

        void attachView(FavouriteContract.View view, String emailId);
    }
}
