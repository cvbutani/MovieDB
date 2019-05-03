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
 * Created by Chirag on 04/09/18.
 */
@Entity(tableName = "movie_id")
public class ResultResponse implements IResultResponse {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primary_key")
    public Integer primary_key;

    @SerializedName("id")
    @Expose
    @NonNull
    @ColumnInfo(name = "id")
    public Integer id;

    @SerializedName("poster_path")
    @Expose
    @NonNull
    @ColumnInfo(name = "poster")
    public String poster;

    @SerializedName("title")
    @Expose
    @Nullable
    @ColumnInfo(name = "title")
    public String title;

    @SerializedName("name")
    @Expose
    @Nullable
    @ColumnInfo(name = "name")
    public String name;

    @Nullable
    @ColumnInfo(name = "type")
    public String type;

    @Nullable
    @ColumnInfo(name = "content")
    public String content;

    public ResultResponse() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getPoster() {
        return poster;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getContent() {
        return content;
    }
}