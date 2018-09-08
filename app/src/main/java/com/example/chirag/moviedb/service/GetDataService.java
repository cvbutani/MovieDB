package com.example.chirag.moviedb.service;

import com.example.chirag.moviedb.model.childitem.ChildItem;
import com.example.chirag.moviedb.model.headeritem.HeaderItem;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface GetDataService {

    @GET("popular?api_key=51b4547daeeca9a0a1dec36a7013b1ad")
    Call<HeaderItem> getAllMovieInfo();

    @GET("299536/credits?api_key=51b4547daeeca9a0a1dec36a7013b1ad")
    Call<ChildItem> getMovieCredits();
}
