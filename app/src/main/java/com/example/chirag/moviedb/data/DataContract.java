package com.example.chirag.moviedb.data;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface DataContract {
    void getNewBatchOfData(final OnTaskCompletion callback);
}
