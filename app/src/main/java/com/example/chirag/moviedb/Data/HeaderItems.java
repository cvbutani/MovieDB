package com.example.chirag.moviedb.Data;

/**
 * MovieDB
 * Created by Chirag on 18/08/18.
 */
public class HeaderItems {

    private String title;
    private int id;
    private float rating;

    public HeaderItems(int id, String title, float rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
