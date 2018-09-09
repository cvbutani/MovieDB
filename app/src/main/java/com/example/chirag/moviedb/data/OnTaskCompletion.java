package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.childitem.ChildItem;
import com.example.chirag.moviedb.model.HeaderItem;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    void onHeaderItemSuccess(HeaderItem data);

    void onHeaderItemFailure(String errorMessage);

    void onChildItemSuccess(ChildItem data);

    void onChildItemFailure(String errorMessage);

}
