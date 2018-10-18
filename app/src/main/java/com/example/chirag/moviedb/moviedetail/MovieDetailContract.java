package com.example.chirag.moviedb.moviedetail;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public interface MovieDetailContract {

    interface View {
        void onTrailerListSuccess(Trailer data);

        void onTrailerListFailure(String errorMessage);

        void onMovieDetail(Movies data, int movieId);

        void onReviewDetail(Reviews data);

        void onGenreDetail(Genre data);

        void onNowPlayingMovie(Movies data, int movieId);

        void onTopRatedMovie(Movies data, int movieId);

        void onUpcomingMovie(Movies data, int movieId);

        void onSimilarMovieSuccess(Movies data, int movieId);

        void onSimilarMovieFailure(String errorMessage);

        void onPopularTV(Movies data, int tvId);

        void onTVGenreDetail(Genre data);

        void getTvTopRatedDetail(Movies data);
    }

    interface Presenter {

        void getTrailerList(int movieId);

        void getMovieData(int movieId);

        void getNowPlayingData(int movieId);

        void getTopRatedData(int movieId);

        void getUpcomingData(int movieId);

        void getGenreItem();

        void getReviews(int movieId);

        void getSimilarData(int movieId);

        void getPopularTV(int tvId);

        void getTVGenreDetail();

        void getTVTopRatedDetail();

        void attachView(MovieDetailContract.View view, int movieId);
    }

}
