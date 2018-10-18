package com.example.chirag.moviedb.dbtv;

import com.example.chirag.moviedb.data.model.Movies;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public interface DBTvContract {

    interface View {

        void onPopularTvSuccess(Movies data);

        void onPopularTvFailure(String errorMessage);

        void getTVTopRatedContentSuccess(Movies data);

        void getTVTopRatedContentFailure(String errorMessage);
    }

    interface Presenter {

        void getPopularTv();

        void getTvTopRated();

        void attachView(DBTvContract.View view);
    }
}
