package com.example.chirag.moviedb.data.remote;

import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    interface OnGetMovieInfoCompletion {
        void getMovieInfoSuccess(ResultResponse data);

        void getMovieInfoFailure(String errorMessage);
    }

    interface OnGetMovieHomeDataCompletion {
        Flowable<List<ResultResponse>> getMovieHomeDataSuccess(String movieType);
    }

    interface OnGetMovieCompletion {
        void getPopularMovieSuccess(List<ResultResponse> data);

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
        void getNowPlayingMovieSuccess(List<ResultResponse> data);

        void getNowPlayingMovieFailure(String errorMessage);
    }

    interface OnGetTopRatedMovieCompletion {
        void getTopRatedMovieSuccess(List<ResultResponse> data);

        void getTopRatedMovieFailure(String errorMessage);
    }

    interface OnGetUpcomingMovieCompletion {
        void getUpcomingMovieSuccess(List<ResultResponse> data);

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

    interface GetUserAccountCompletion {
        void getUserAccountSuccess(User user);

        void getUserAccountFailure(String errorMessage);
    }

    interface  GetFavouriteTMDBCompletion {
        void getFavouriteTMDBSuccess(List<Favourite> data);

        void getFavouriteTMDBFailure(String errorMessage);
    }
}
