package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.GenreItem;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.model.Reviews;
import com.example.chirag.moviedb.model.TrailerItem;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    interface OnGetMovieCompletion {

        void onHeaderItemSuccess(HeaderItem data);

        void onHeaderItemFailure(String errorMessage);

    }

    interface OnGetGenresCompletion {

        void onGenreListSuccess(GenreItem data);

        void onGenreListFailure(String errorMessage);
    }

    interface OnGetTrailerCompletion {

        void onTrailerItemSuccess(TrailerItem data);

        void onTrailerItemFailure(String errorMessage);
    }

    interface OnGetReviewCompletion {

        void onReviewResponseSuccess(Reviews data);

        void onReviewResponseFailure(String errorMessage);
    }

    interface OnGetNowPlayingCompletion {

        void onNowPlayingMovieSuccess(HeaderItem data);

        void onNowPlayingMovieFailure(String errorMessage);
    }
}
