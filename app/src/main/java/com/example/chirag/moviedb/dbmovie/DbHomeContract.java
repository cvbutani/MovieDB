package com.example.chirag.moviedb.dbmovie;


import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.Movies;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public interface DbHomeContract {

    interface View {
        void getPopularMovieHome(Movies data);

        void getResultFailure(String errorMessage);

        void getMovieGenreHome(Genre data);

        void getNowPlayingMovieHome(Movies data);

        void getTopRatedMovieHome(Movies data);

        void getUpcomingMovieHome(Movies data);

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
