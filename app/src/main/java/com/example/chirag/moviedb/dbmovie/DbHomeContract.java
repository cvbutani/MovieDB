package com.example.chirag.moviedb.dbmovie;


import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public interface DbHomeContract {

    interface View {
        void getPopularMovieHome(List<ResultResponse> data);

        void getResultFailure(String errorMessage);

        void getNowPlayingMovieHome(List<ResultResponse> data);

        void getTopRatedMovieHome(List<ResultResponse> data);

        void getUpcomingMovieHome(List<ResultResponse> data);

    }

    interface Presenter {
        void getNowPlayingMovies();

        void getPopularMovies();

        void getTopRatedMovies();

        void getUpcomingMovies();

        void attachView(DbHomeContract.View view);
    }
}
