package com.example.chirag.moviedb.dbtv;

import com.example.chirag.moviedb.model.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public interface DBTvContract {

    interface View {

        void onPopularTvSuccess(HeaderItem data);

        void onPopularTvFailure(String errorMessage);
    }

    interface Presenter {

        void getPopularTv();

        void attachView(DBTvContract.View view);
    }
}
