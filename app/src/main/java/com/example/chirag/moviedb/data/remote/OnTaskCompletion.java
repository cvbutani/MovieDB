package com.example.chirag.moviedb.data.remote;

import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.User;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    interface OnGetMovieInfoCompletion {
        void getMovieInfoSuccess(TMDB data);

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

    interface OnGetTvInfoCompletion {
        void getTvInfoSuccess(TMDB data);

        void getTvInfoFailure(String errorMessage);
    }

    interface GetUserSignInCompletion {
        void getUserInfoSuccess(List<User> user);

        void getUserInfoFailure(String errorMessage);
    }

    interface InsertUserSignInCompletion {
        void insertUserInfoSuccess(User user);

        void insertUserInfoFailure(String errorMessage);
    }

}
