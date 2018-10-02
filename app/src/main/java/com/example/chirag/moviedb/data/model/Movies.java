package com.example.chirag.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class Movies {

    @SerializedName("results")
    @Expose
    private List<MovieResponse> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Movies() {
    }

    public Movies(List<MovieResponse> results) {
        this.results = results;
    }

    public List<MovieResponse> getResults() {
        return results;
    }

    public void setResults(List<MovieResponse> results) {
        this.results = results;
    }
}