package com.example.chirag.moviedb.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
@Entity(tableName = "trailer")
public class TrailerResponse implements Serializable {
//    @PrimaryKey(autoGenerate = true)
//    @NonNull
//    @ColumnInfo(name = "primaryKey")
//    private int primaryKey;
//
//    @NonNull
//    @ColumnInfo(name = "movieId")
//    private Integer movieId;
//
//    @SerializedName("key")
//    @Expose
//    @Nullable
//    @ColumnInfo(name = "key")
//    private String key;
//
//    public TrailerResponse(@NonNull int primaryKey, @NonNull Integer movieId, @Nullable String key) {
//        this.primaryKey = primaryKey;
//        this.movieId = movieId;
//        this.key = key;
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
//    @Nullable
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(@Nullable String key) {
//        this.key = key;
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
}