package com.example.chirag.moviedb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
public class ResultTrailerItem implements Serializable {

    @SerializedName("key")
    @Expose
    private String key;

    /**
     * No args constructor for use in serialization
     */
    public ResultTrailerItem() {
    }

    public ResultTrailerItem(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}