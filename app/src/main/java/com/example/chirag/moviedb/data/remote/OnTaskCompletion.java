package com.example.chirag.moviedb.data.remote;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TvInfo;

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
        void getPopularMovieSuccess(Result data);
        void getPopularMovieFailure(String errorMessage);
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
        void getNowPlayingMovieSuccess(Result data);
        void getNowPlayingMovieFailure(String errorMessage);
    }

    interface OnGetTopRatedMovieCompletion {
        void getTopRatedMovieSuccess(Result data);
        void getTopRatedMovieFailure(String errorMessage);
    }

    interface OnGetUpcomingMovieCompletion {
        void getUpcomingMovieSuccess(Result data);
        void getUpcomingMovieFailure(String errorMessage);
    }

    interface OnGetSimilarMovieCompletion {
        void getSimilarMovieSuccess(Result data);
        void getSimilarMovieFailure(String errorMessage);
    }

    interface OnGetPopularTvCompletion {
        void getPopularTvSuccess(Result data);
        void getPopularTvFailure(String errorMessage);
    }

    interface GetTopRatedTvCompletion {
        void getTvTopRatedContentSuccess(Result data);
        void getTvTopRatedContentFailure(String errorMessage);
    }

    interface GetLatestTvCompletion {
        void getLatestTvSuccess(Result data);
        void getLatestTvFailure(String errorMessage);
    }

    interface OnGetTvInfoCompletion {
        void getTvInfoSuccess(TvInfo data);
        void getTvInfoFailure(String errorMessage);
    }

}
