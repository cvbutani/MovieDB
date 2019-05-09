package com.example.chirag.moviedb.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * MovieDB
 * Created by Chirag on 16/09/18.
 */
@Entity(tableName = "review")
public class ReviewResponse implements Serializable {
//    @PrimaryKey(autoGenerate = true)
//    @NonNull
//    @ColumnInfo(name = "primaryKey")
//    private int primaryKey;
//
//    @NonNull
//    @ColumnInfo(name = "movieId")
//    private Integer movieId;
//
//    @SerializedName("author")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "author")
//    private String author;
//    @SerializedName("content")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "content")
//    private String content;
//
//    public ReviewResponse(@NonNull int primaryKey, @NonNull Integer movieId, @Nullable String author, @Nullable String content) {
//        this.primaryKey = primaryKey;
//        this.movieId = movieId;
//        this.author = author;
//        this.content = content;
//    }
//
//    @NonNull
//    public int getPrimaryKey() {
//        return primaryKey;
//    }
//
//    public void setPrimaryKey(@NonNull int primaryKey) {
//        this.primaryKey = primaryKey;
//    }
//
//    @NonNull
//    public Integer getMovieId() {
//        return movieId;
//    }
//
//    public void setMovieId(@NonNull Integer movieId) {
//        this.movieId = movieId;
//    }
//
//    @Nullable
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(@Nullable String author) {
//        this.author = author;
//    }
//
//    @Nullable
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(@Nullable String content) {
//        this.content = content;
//    }
}