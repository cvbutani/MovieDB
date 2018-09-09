package com.example.chirag.moviedb;

import com.example.chirag.moviedb.model.childitem.ChildItem;
import com.example.chirag.moviedb.model.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 05/09/18.
 */
public interface DbHomeContract {

    interface View {
        void onHeaderResultSuccess(HeaderItem data);

        void onHeaderResultFailure(String errorMessage);

        void onChildResultSuccess(ChildItem data);

        void onChildResultFailure(String errorMessage);
    }

    interface Presenter {
        void getData();

        void attachView(DbHomeContract.View view);
    }
}
