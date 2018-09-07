package com.example.chirag.moviedb.service;

import android.util.Log;

import com.example.chirag.moviedb.model.HeaderItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface GetDataService {

    @GET("popular?api_key=51b4547daeeca9a0a1dec36a7013b1ad")
    Call<HeaderItem> getAllMovieInfo();
}
