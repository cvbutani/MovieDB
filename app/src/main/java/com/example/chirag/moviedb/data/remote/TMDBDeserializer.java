package com.example.chirag.moviedb.data.remote;

import com.example.chirag.moviedb.data.model.TMDB;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Chirag on 5/2/2019 at 21:36.
 * Project - MovieDB
 */
public class TMDBDeserializer implements JsonDeserializer<TMDB> {
    @Override
    public TMDB deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return parseObject(json.getAsJsonObject());
    }

    private TMDB parseObject(JsonObject object) {
        TMDB tmdb = new TMDB();

    }
}
