package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    void onHeaderItemSuccess(HeaderItem data);

    void onHeaderItemFailure(String errorMessage);

}
