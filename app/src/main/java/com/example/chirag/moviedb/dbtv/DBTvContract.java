package com.example.chirag.moviedb.dbtv;

import com.example.chirag.moviedb.data.model.Movies;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public interface DBTvContract {

    interface View {

        void getPopularTvHome(Movies data);

        void getResultFailure(String errorMessage);

        void getTopRatedTvHome(Movies data);

        void getLatestTvHome(Movies data);
    }

    interface Presenter {

        void getPopularTv();

        void getTopRatedTv();

        void getLatestTv();

        void attachView(DBTvContract.View view);
    }
}
