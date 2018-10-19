package com.example.chirag.moviedb.moviedetail;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public interface MovieDetailContract {

    interface View {
        void getMovieInfoHome(int movieId, MovieInfo data);

        void getTrailerDetail(Trailer data);

        void getResultFailure(String errorMessage);

        void getPopularMovieDetail(Movies data, int movieId);

        void getReviewDetail(Reviews data);

        void getGenreMovieDetail(Genre data);

        void getNowPlayingMovieDetail(Movies data, int movieId);

        void getTopRatedMovieDetail(Movies data, int movieId);

        void getUpcomingMovieDetail(Movies data, int movieId);

        void getSimilarMovieDetail(Movies data, int movieId);

        void getPopularTvDetail(Movies data, int tvId);

        void getGenreTvDetail(Genre data);

        void getTopRatedTvDetail(Movies data);

        void getSeasonTvDetail(MovieResponse data, int tvId);
    }

    interface Presenter {
        void getMovieInfo(int movieId);

        void getTrailer(int movieId);

        void getPopularMovie(int movieId);

        void getNowPlayingMovie(int movieId);

        void getTopRatedMovie(int movieId);

        void getUpcomingMovie(int movieId);

        void getGenreMovie();

        void getReviews(int movieId);

        void getSimilarMovie(int movieId);

        void getPopularTV(int tvId);

        void getGenreTv();

        void getTopRatedTv();

        void getSeasonTv(int tvId);

        void attachView(MovieDetailContract.View view, int movieId);
    }

}
