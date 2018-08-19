package com.example.chirag.moviedb;

/**
 * MovieDB
 * Created by Chirag on 18/08/18.
 */
public class HeaderItems {

    private String title;
    private float rating;

    public HeaderItems(String title, float rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
