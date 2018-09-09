package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.GenreResponse;
import com.example.chirag.moviedb.model.HeaderItem;

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

        void onGenreListSuccess(GenreResponse data);

        void onGenreListFailure(String errorMessage);
    }

}
