package com.example.chirag.moviedb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
public class TrailerItem implements Serializable
{

    @SerializedName("results")
    @Expose
    private List<ResultTrailerItem> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TrailerItem() {
    }

    public TrailerItem(List<ResultTrailerItem> results) {
        this.results = results;
    }

    public List<ResultTrailerItem> getResults() {
        return results;
    }

    public void setResults(List<ResultTrailerItem> results) {
        this.results = results;
    }
}