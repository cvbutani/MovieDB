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

    }

    interface Presenter {

        void getPopularTv();

        void getTopRatedTv();

        void attachView(DBTvContract.View view);
    }
}
