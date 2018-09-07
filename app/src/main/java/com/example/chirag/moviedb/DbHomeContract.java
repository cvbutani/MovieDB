package com.example.chirag.moviedb;

import com.example.chirag.moviedb.model.HeaderItem;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public interface DbHomeContract {

    interface View {
        void onResult(HeaderItem data);

        void onError(String errorMessage);
    }

    interface Presenter {
        void getData();

        void attachView(DbHomeContract.View view);
    }
}
