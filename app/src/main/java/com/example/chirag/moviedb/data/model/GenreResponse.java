package com.example.chirag.moviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * MovieDB
 * Created by Chirag on 09/09/18.
 */
@Entity(tableName = "genre")
public class GenreResponse implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "primaryKey")
    private int primaryKey;

    @SerializedName("id")
    @Expose
    @Nullable
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("name")
    @Expose
    @Nullable
    @ColumnInfo(name = "name")
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public GenreResponse() {
    }

    public GenreResponse(@Nullable Integer id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    @NonNull
    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(@NonNull int primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }
}