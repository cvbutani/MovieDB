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

        JsonArray jsonMainArray = object.getAsJsonArray("results");

        List<String> trailerKey = new ArrayList<>();
        List<String> reviewText = new ArrayList<>();
        List<String> reviewAuthor = new ArrayList<>();

        ResultResponse resultResponse = new ResultResponse();

        for (int i = 0; i < jsonMainArray.size(); i++) {

            if (jsonMainArray.get(i) != null &&
                    jsonMainArray.get(i).getAsJsonObject().get("key") == null &&
                    jsonMainArray.get(i).getAsJsonObject().get("author") == null) {
                ResultResponse response = new ResultResponse();
                //  Home Screen Data request
                JsonElement element = jsonMainArray.get(i);
                response.mId = (int) getDoubleOrZero(element, "id");
                response.mPoster = getStringOrEmpty(element, "poster_path");
                result.results.add(response);
            } else if (jsonMainArray.getAsJsonObject().get("key") != null) {
                //  Trailer list
                trailerKey.add(getStringOrEmpty(jsonMainArray.get(i), "key"));

            } else if (jsonMainArray.getAsJsonObject().get("author") != null) {
                //  Review
                reviewText.add(getStringOrEmpty(jsonMainArray.get(i), "content"));
                reviewAuthor.add(getStringOrEmpty(jsonMainArray.get(i), "author"));
            }
        }
        resultResponse.mKey = trailerKey;
        resultResponse.mReviewText = reviewText;
        resultResponse.mReviewAuthor = reviewAuthor;
//        result.results.add(resultResponse);

//        JsonElement jsonElement = object.get("")
//        ResultResponse resultResponse = new ResultResponse();

        resultResponse.mId = (int) getDoubleOrZero(object, "id");
        resultResponse.mPoster = getStringOrEmpty(object, "poster_path");
        resultResponse.mTitle = getStringOrEmpty(object, "title");
        resultResponse.mBackdropPath = getStringOrEmpty(object, "backdrop_path");
        resultResponse.mImdbId = getStringOrEmpty(object, "imdb_id");
        resultResponse.mOriginalLanguange = getStringOrEmpty(object,
                "original_language");
        resultResponse.mOriginalTitle = getStringOrEmpty(object, "original_title");
        resultResponse.mOverView = getStringOrEmpty(object, "overview");
        resultResponse.mReleaseDate = getStringOrEmpty(object, "release_date");
        resultResponse.mRunTime = (int) getDoubleOrZero(object, "runtime");
        resultResponse.mVoteAvg = getDoubleOrZero(object, "vote_average");
        resultResponse.mOriginalName = getStringOrEmpty(object, "original_name");

        //  Genre
        JsonArray genreArray = object.getAsJsonArray("genres");
        if (genreArray != null) {
            List<String> name = new ArrayList<>();
            for (int j = 0; j < genreArray.size(); j++) {
                name.add(getStringOrEmpty(genreArray.get(j), "name"));
            }
            resultResponse.mName = name;
        }
        result.results.add(resultResponse);
        return result;
    }

    private double getDoubleOrZero(JsonElement element, String member) {
        return element != null ? element.getAsJsonObject().get(member).getAsDouble() : 0;
    }

    private String getStringOrEmpty(JsonElement element, String member) {
        return element != null ? element.getAsJsonObject().get(member).getAsString() : "";
    }
}
