package com.example.chirag.moviedb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
public class GenreItem implements Serializable {

    @SerializedName("genres")
    @Expose
    private List<ResultGenreItem> resultGenreItems = null;

    /**
     * No args constructor for use in serialization
     */
    public GenreItem() {
    }

    /**
     * @param resultGenreItems
     */
    public GenreItem(List<ResultGenreItem> resultGenreItems) {
        super();
        this.resultGenreItems = resultGenreItems;
    }

    public List<ResultGenreItem> getResultGenreItems() {
        return resultGenreItems;
    }

    public void setResultGenreItems(List<ResultGenreItem> resultGenreItems) {
        this.resultGenreItems = resultGenreItems;
    }

}