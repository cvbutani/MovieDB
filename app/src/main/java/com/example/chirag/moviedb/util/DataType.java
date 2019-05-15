package com.example.chirag.moviedb.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Chirag on 5/13/2019 at 22:16.
 * Project - MovieDB
 */
public class DataType implements Serializable {

    @TypeConverter
    public String stringFromArray(List<String> values) {
        if (values == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.toJson(values, type);
    }

    @TypeConverter
    public List<String> arrayFromString(String values) {
        if (values == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(values, type);
    }
}
