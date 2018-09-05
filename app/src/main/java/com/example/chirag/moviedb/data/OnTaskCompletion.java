package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.model.HeaderItem;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    void onSuccess(List<HeaderItem> data);

    void onFailure(String errorMessage);
}
