package com.example.chirag.moviedb.data.remote;

import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    interface OnGetMovieCompletion {

        void onHeaderItemSuccess(Movies data);

        void onHeaderItemFailure(String errorMessage);

    }

    interface OnGetGenresCompletion {

        void onGenreListSuccess(Genre data);

        void onGenreListFailure(String errorMessage);
    }

    interface OnGetTrailerCompletion {

        void onTrailerItemSuccess(Trailer data);

        void onTrailerItemFailure(String errorMessage);
    }

    interface OnGetReviewCompletion {

        void onReviewResponseSuccess(Reviews data);

        void onReviewResponseFailure(String errorMessage);
    }

    interface OnGetNowPlayingCompletion {

        void onNowPlayingMovieSuccess(Movies data);

        void onNowPlayingMovieFailure(String errorMessage);
    }

    interface OnGetTopRatedMovieCompletion {

        void onTopRatedMovieSuccess(Movies data);

        void onTopRatedMovieFailure(String errorMessage);
    }

    interface OnGetUpcomingMovieCompletion {

        void onUpcomingMovieSuccess(Movies data);

        void onUpcomingMovieFailure(String errorMessage);
    }

    interface OnGetSimilarMovieCompletion {

        void onSimilarMovieSuccess(Movies data);

        void onSimilarMovieFailure(String errorMessage);
    }

    interface OnGetPopularTvCompletion {

        void onPopularTvSuccess(Movies data);

        void onPopularTvFailure(String errorMessage);
    }
}
