package com.example.chirag.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 18/10/18.
 */
public class Result implements Serializable {

    @SerializedName("results")
    @Expose
    public List<ResultResponse> results = null;

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param results
     */
    public Result(List<ResultResponse> results) {
        super();

        this.results = results;
    }

    public List<ResultResponse> getResults() {
        return results;
    }

    public void setResults(List<ResultResponse> results) {
        this.results = results;
    }

}
