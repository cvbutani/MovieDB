package com.example.chirag.moviedb.dbmovie;


import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public interface DbHomeContract {

    interface View {
        void onHeaderResultSuccess(Movies data);

        void onHeaderResultFailure(String errorMessage);

        void onGenreListSuccess(Genre data);

        void onGenreListFailure(String errorMessage);

        void onNowPlayingMovieSuccess(Movies data);

        void onNowPlayingMovieFailure(String errorMessage);

        void onTopRatedMovieSuccess(Movies data);

        void onTopRatedMovieFailure(String errorMessage);

        void onUpcomingMovieSuccess(Movies data);

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
