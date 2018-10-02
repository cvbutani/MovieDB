package com.example.chirag.moviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
public class TrailerResponse implements Serializable {

    @Nullable
    @ColumnInfo(name = "movieId")
    private String movieId;

    @SerializedName("key")
    @Expose
    @Nullable
    @ColumnInfo(name = "key")
    private String key;


    /**
     * No args constructor for use in serialization
     */
    public TrailerResponse() {
    }

    public TrailerResponse(@Nullable String key, @Nullable String movieId) {
        this.key = key;
        this.movieId = movieId;
    }
    @Nullable
    public String getKey() {
        return key;
    }

    public void setKey(@Nullable String key) {
        this.key = key;
    }

    @Nullable
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(@Nullable String movieId) {
        this.movieId = movieId;
    }
}