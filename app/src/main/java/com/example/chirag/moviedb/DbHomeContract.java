package com.example.chirag.moviedb;


import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public interface DbHomeContract {

    interface View {
        void onHeaderResultSuccess(HeaderItem data);

        void onHeaderResultFailure(String errorMessage);

        void onGenreListSuccess(GenreItem data);

        void onGenreListFailure(String errorMessage);

    }

    interface Presenter {
        void getPopularMovies();

        void getGenreList();

        void attachView(DbHomeContract.View view);
    }
}
