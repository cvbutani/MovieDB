package com.example.chirag.moviedb.dbmovie;


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

        void onNowPlayingMovieSuccess(HeaderItem data);

        void onNowPlayingMovieFailure(String errorMessage);

        void onTopRatedMovieSuccess(HeaderItem data);

        void onTopRatedMovieFailure(String errorMessage);

        void onUpcomingMovieSuccess(HeaderItem data);

        void onUpcomingMovieFailure(String errorMessage);

    }

    interface Presenter {

        void getNowPlayingMovies();

        void getPopularMovies();

        void getTopRatedMovies();

        void getUpcomingMovies();

        void getGenreList();

        void attachView(DbHomeContract.View view);
    }
}
