package com.example.chirag.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
public class Genre implements Serializable {

    @SerializedName("genres")
    @Expose
    private List<GenreResponse> genreResponses = null;

    /**
     * No args constructor for use in serialization
     */
    public Genre() {
    }

    /**
     * @param genreResponses
     */
    public Genre(List<GenreResponse> genreResponses) {
        super();
        this.genreResponses = genreResponses;
    }

    public List<GenreResponse> getGenreResponses() {
        return genreResponses;
    }

    public void setGenreResponses(List<GenreResponse> genreResponses) {
        this.genreResponses = genreResponses;
    }

}