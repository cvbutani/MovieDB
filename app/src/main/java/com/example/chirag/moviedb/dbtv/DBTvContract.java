package com.example.chirag.moviedb.dbtv;

import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 23/09/18.
 */
public interface DBTvContract {

    interface View {

        void getPopularTvHome(List<ResultResponse> data);

        void getResultFailure(String errorMessage);

        void getTopRatedTvHome(List<ResultResponse> data);

    }

    interface Presenter {

        void getPopularTv();

        void getTopRatedTv();

        void attachView(DBTvContract.View view);
    }
}
