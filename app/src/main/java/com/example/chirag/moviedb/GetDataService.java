package com.example.chirag.moviedb;

import com.example.chirag.moviedb.model.HeaderItem;

import java.util.List;

import retrofit2.Call;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface GetDataService {

    Call<List<HeaderItem>> getAllMovieInfo();
}
