package com.example.chirag.moviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
@Entity(tableName = "movie_id")
public class ResultResponse implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primary_key")
    private Integer primary_key;

    @SerializedName("id")
    @Expose
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("poster_path")
    @Expose
    @NonNull
    @ColumnInfo(name = "poster")
    private String poster;

    @SerializedName("title")
    @Expose
    @Nullable
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("name")
    @Expose
    @Nullable
    @ColumnInfo(name = "name")
    private String name;

    @Nullable
    @ColumnInfo(name = "type")
    private String type;

    @Nullable
    @ColumnInfo(name = "content")
    private String content;

    public ResultResponse() {
    }

    public ResultResponse(@NonNull Integer id, @NonNull String poster, @Nullable String title,
                          @Nullable String name, @Nullable String type, @Nullable String key) {
        this.id = id;
        this.poster = poster;
        this.title = title;
        this.name = name;
        this.type = type;
        this.content = key;
    }

    @NonNull
    public Integer getPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(@NonNull Integer primary_key) {
        this.primary_key = primary_key;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getPoster() {
        return poster;
    }

    public void setPoster(@NonNull String poster) {
        this.poster = poster;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }
}