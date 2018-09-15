package com.example.chirag.moviedb;


import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultTrailerItem;

import java.util.List;

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

        void onTrailerListSuccess(List<ResultTrailerItem> data);

        void onTrailerListFailure(String errorMessage);

    }

    interface Presenter {
        void getPopularMovies();

        void getGenreList();

        void getTrailerList(int movieId);

        void attachView(DbHomeContract.View view);
    }
}
