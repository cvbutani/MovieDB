package com.example.chirag.moviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
@Entity(tableName = "trailer",
        foreignKeys = {
                @ForeignKey(entity = MovieResponse.class,
                        parentColumns = "entryid",
                        childColumns = "id")},
        indices = {@Index(value = "id")})
public class Trailer implements Serializable {
    @SerializedName("id")
    @Expose
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("results")
    @Expose
    @Nullable
    @ColumnInfo(name = "trailers")
    private List<TrailerResponse> results = null;

    /**
     * No args constructor for use in serialization
     */
    public Trailer() {
    }

    public Trailer(Integer id, List<TrailerResponse> results) {
        this.id = id;
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerResponse> getResults() {
        return results;
    }

    public void setResults(List<TrailerResponse> results) {
        this.results = results;
    }
}