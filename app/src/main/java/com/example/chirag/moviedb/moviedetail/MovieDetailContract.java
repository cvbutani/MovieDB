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

        void getTrailerDetail(Trailer data);

        void getResultFailure(String errorMessage);

        void getReviewDetail(Reviews data);

        void getSimilarMovieDetail(Result data, int movieId);

        void getFavouriteTMDBInfo(List<Favourite> data);

        void insertTMDBInfo();
    }

    interface Presenter {
        void getMovieInfo(int movieId);

        void getTvInfo(int tvId);

        void getTrailer(int movieId);

        void getReviews(int movieId);

        void getSimilarMovie(int movieId);

        void insertTMDB(Favourite data);

        void getFavouriteTMDB(String emailId);

        void attachView(MovieDetailContract.View view, int movieId, String emailId);
    }

}
