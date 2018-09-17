package com.example.chirag.moviedb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class HeaderItem {

    @SerializedName("results")
    @Expose
    private List<ResultHeaderItem> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public HeaderItem() {
    }

    public HeaderItem(List<ResultHeaderItem> results) {
        this.results = results;
    }

    public List<ResultHeaderItem> getResults() {
        return results;
    }
}