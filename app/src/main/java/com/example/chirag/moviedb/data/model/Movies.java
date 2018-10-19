package com.example.chirag.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 18/10/18.
 */
public class Movies implements Serializable {

    @SerializedName("results")
    @Expose
    private List<MovieResponse> results = null;

    /**
     * No args constructor for use in serialization
     */
    public Movies() {
    }

    /**
     * @param results
     */
    public Movies(List<MovieResponse> results) {
        super();

        this.results = results;
    }

    public List<MovieResponse> getResults() {
        return results;
    }

    public void setResults(List<MovieResponse> results) {
        this.results = results;
    }

}
