package com.example.chirag.moviedb;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.ResultHeaderItem;
import com.example.chirag.moviedb.model.Reviews;
import com.example.chirag.moviedb.model.TrailerItem;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public interface MovieDetailContract {

    interface View {
        void onTrailerListSuccess(TrailerItem data);

        void onTrailerListFailure(String errorMessage);

        void onMovieDetail(HeaderItem data, int movieId);

        void onReviewDetail(Reviews data);

        void onGenreDetail(GenreItem data, ResultHeaderItem item);

        void onNowPlayingMovie(HeaderItem data, int movieId);

        void onTopRatedMovie(HeaderItem data, int movieId);

        void onUpcomingMovie(HeaderItem data, int movieId);

        void onSimilarMovieSuccess(HeaderItem data, int movieId);

        void onSimilarMovieFailure(String errorMessage);
    }

    interface Presenter {

        void getTrailerList(int movieId);

        void getMovieData(int movieId);

        void getNowPlayingData(int movieId);

        void getTopRatedData(int movieId);

        void getUpcomingData(int movieId);

        void getGenreItem(ResultHeaderItem item);

        void getReviews(int movieId);

        void getSimilarData(int movieId);

        void attachView(MovieDetailContract.View view, int movieId);
    }

}
