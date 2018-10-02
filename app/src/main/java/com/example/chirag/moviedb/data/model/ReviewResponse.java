package com.example.chirag.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * MovieDB
 * Created by Chirag on 16/09/18.
 */
public class ReviewResponse implements Serializable
{

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("content")
    @Expose
    private String content;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReviewResponse() {
    }

    /**
     *
     * @param content
     * @param author
     */
    public ReviewResponse(String author, String content) {
        super();
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}