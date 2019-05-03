package com.example.chirag.moviedb.data.remote;

import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

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
        JsonArray jsonArray = object.getAsJsonArray("results");

        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.get(i) != null) {
                ResultResponse resultResponse = new ResultResponse();

                JsonElement id = jsonArray.get(i).getAsJsonObject().get("id");
                resultResponse.id = getIntOrZero(id);

                JsonElement posterPath = jsonArray.get(i).getAsJsonObject().get("poster_path");
                resultResponse.poster = getStringOrEmpty(posterPath);

                JsonElement title = jsonArray.get(i).getAsJsonObject().get("title");
                resultResponse.title = getStringOrEmpty(title);

                JsonElement name = jsonArray.get(i).getAsJsonObject().get("name");
                resultResponse.name = getStringOrEmpty(name);

                result.results.add(resultResponse);
            }
        }

        return result;
    }

    private int getIntOrZero(JsonElement element) {
        return element != null ? element.getAsInt() : 0;
    }

    private String getStringOrEmpty(JsonElement element) {
        return element != null ? element.getAsString() : "";
    }
}
