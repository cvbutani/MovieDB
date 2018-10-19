package com.example.chirag.moviedb.dbtv;

import com.example.chirag.moviedb.data.model.Result;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public interface DBTvContract {

    interface View {

        void getPopularTvHome(Result data);

        void getResultFailure(String errorMessage);

        void getTopRatedTvHome(Result data);

        void getLatestTvHome(Result data);
    }

    interface Presenter {

        void getPopularTv();

        void getTopRatedTv();

        void getLatestTv();

        void attachView(DBTvContract.View view);
    }
}
