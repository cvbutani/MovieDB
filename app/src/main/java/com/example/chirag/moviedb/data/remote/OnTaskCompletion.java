package com.example.chirag.moviedb.data.remote;

import android.graphics.Movie;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    interface OnGetMovieInfoCompletion {
        void getMovieInfoSuccess(MovieInfo data);
        void getMovieInfoFailure(String errorMessage);
    }

    interface OnGetMovieCompletion {
        void getPopularMovieSuccess(Movies data);
        void getPopularMovieFailure(String errorMessage);
    }

    interface OnGetGenresCompletion {
        void getMovieGenreItemSuccess(Genre data);
        void getMovieGenreItemFailure(String errorMessage);
    }

    interface OnGetTrailerCompletion {
        void getTrailerItemSuccess(Trailer data);
        void getTrailerItemFailure(String errorMessage);
    }

    interface OnGetReviewCompletion {
        void getReviewResponseSuccess(Reviews data);
        void getReviewResponseFailure(String errorMessage);
    }

    interface OnGetNowPlayingCompletion {
        void getNowPlayingMovieSuccess(Movies data);
        void getNowPlayingMovieFailure(String errorMessage);
    }

    interface OnGetTopRatedMovieCompletion {
        void getTopRatedMovieSuccess(Movies data);
        void getTopRatedMovieFailure(String errorMessage);
    }

    interface OnGetUpcomingMovieCompletion {
        void getUpcomingMovieSuccess(Movies data);
        void getUpcomingMovieFailure(String errorMessage);
    }

    interface OnGetSimilarMovieCompletion {
        void getSimilarMovieSuccess(Movies data);
        void getSimilarMovieFailure(String errorMessage);
    }

    interface OnGetPopularTvCompletion {
        void getPopularTvSuccess(Movies data);
        void getPopularTvFailure(String errorMessage);
    }

    interface OnGetTVGenreCompletion {
        void getTVGenreSuccess(Genre data);
        void getTVGenreFailure(String errorMessage);
    }

    interface GetTopRatedTvCompletion {
        void getTvTopRatedContentSuccess(Movies data);
        void getTvTopRatedContentFailure(String errorMessage);
    }

    interface GetTvSeasonCompletion {
        void getTvSeasonContentSuccess(MovieResponse data);
        void getTvSeasonContentFailure(String errorMessage);
    }

    interface GetLatestTvCompletion {
        void getLatestTvSuccess(Movies data);
        void getLatestTvFailure(String errorMessage);
    }
}
