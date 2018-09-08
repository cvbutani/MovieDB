package com.example.chirag.moviedb.model.childitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * MovieDB
 * Created by Chirag on 07/09/18.
 */
public class Cast {

    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Cast() {
    }

    public Cast(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
