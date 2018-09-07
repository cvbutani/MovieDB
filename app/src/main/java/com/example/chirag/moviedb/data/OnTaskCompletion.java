package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.headeritem.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    void onSuccess(HeaderItem data);

    void onFailure(String errorMessage);
}
