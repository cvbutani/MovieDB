package com.example.chirag.moviedb.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
public class Trailer implements Serializable {

    @SerializedName("results")
    @Expose
    private List<TrailerResponse> results = null;

    /**
     * No args constructor for use in serialization
     */
    public Trailer() {
    }

    public Trailer(List<TrailerResponse> results) {
        this.results = results;
    }

    public List<TrailerResponse> getResults() {
        return results;
    }

    public void setResults(List<TrailerResponse> results) {
        this.results = results;
    }
}