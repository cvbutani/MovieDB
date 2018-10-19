package com.example.chirag.moviedb.moviedetail;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TvInfo;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public interface MovieDetailContract {

    interface View {
        void getMovieInfoHome(int movieId, MovieInfo data);

        void getTvInfoHome(int tvId, TvInfo data);

        void getTrailerDetail(Trailer data);

        void getResultFailure(String errorMessage);

        void getReviewDetail(Reviews data);

        void getSimilarMovieDetail(Result data, int movieId);
    }

    interface Presenter {
        void getMovieInfo(int movieId);

        void getTvInfo(int tvId);

        void getTrailer(int movieId);

        void getReviews(int movieId);

        void getSimilarMovie(int movieId);

        void attachView(MovieDetailContract.View view, int movieId);
    }

}
