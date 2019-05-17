package com.example.chirag.moviedb.data.remote;

import android.util.Log;

import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chirag on 5/2/2019 at 22:41.
 * Project - MovieDB
 */
public class ResultDeserializer implements JsonDeserializer<Result> {


    @Override
    public Result deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        return parseResultObject(json.getAsJsonObject());
    }


    private Result parseResultObject(JsonObject object) {

        Result result = new Result();
        List<ResultResponse> responseList = new ArrayList<>();

        if (!object.isJsonNull() && object.getAsJsonArray("results") != null) {
            JsonArray jsonMainArray = object.getAsJsonArray("results");
            if (jsonMainArray.size() == 0) return null;

            for (int i = 0; i < jsonMainArray.size(); i++) {

                JsonElement element = jsonMainArray.get(i);
                ResultResponse response = new ResultResponse();

                if (jsonMainArray.get(i) != null) {
                    response.mId = (int) getDoubleOrZero(element, "id");
                    response.mPoster = getStringOrEmpty(element, "poster_path");
                    response.mTitle = getStringOrEmpty(element, "title");
                    responseList.add(response);
                }
            }
        }
        result.setResults(responseList);
        return result;
    }

    private int getDoubleOrZero(JsonElement element, String member) {
        return element.getAsJsonObject().get(member) != null ?
                element.getAsJsonObject().get(member).getAsInt() : 0;
    }

    private String getStringOrEmpty(JsonElement element, String member) {
        return element.getAsJsonObject().get(member) != null ?
                element.getAsJsonObject().get(member).getAsString() : "";
    }

}
