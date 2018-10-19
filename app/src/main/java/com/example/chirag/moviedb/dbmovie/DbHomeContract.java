package com.example.chirag.moviedb.dbmovie;


import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Result;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public interface DbHomeContract {

    interface View {
        void getPopularMovieHome(Result data);

        void getResultFailure(String errorMessage);

        void getMovieGenreHome(Genre data);

        void getNowPlayingMovieHome(Result data);

        void getTopRatedMovieHome(Result data);

        void getUpcomingMovieHome(Result data);

    }

    interface Presenter {
        void getNowPlayingMovies();

        void getPopularMovies();

        void getTopRatedMovies();

        void getUpcomingMovies();

        void getGenreMovies();

        void attachView(DbHomeContract.View view);
    }
}
