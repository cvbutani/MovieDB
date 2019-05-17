package com.example.chirag.moviedb.moviedetail;

import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public interface MovieDetailContract {

    interface View {
        void getMovieInfoHome(int movieId, ResultResponse data);

        void getTvInfoHome(int tvId, TMDB data);

        void getResultFailure(String errorMessage);

        void getSimilarMovieDetail(Result data, int movieId);

        void getFavouriteTMDBInfo(List<Favourite> data);

        void insertTMDBInfo();
    }

    interface Presenter {
        void getMovieInfo(int movieId, String movieType,
                          String contentType);

        void getTvInfo(int tvId);

        void getSimilarMovie(int movieId);

        void insertTMDB(Favourite data);

        void getFavouriteTMDB(String emailId);

        void attachView(MovieDetailContract.View view, int movieId, String emailId, String movieType, String contentType);
    }

}
