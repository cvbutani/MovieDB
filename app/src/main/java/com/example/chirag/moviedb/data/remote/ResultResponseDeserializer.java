package com.example.chirag.moviedb.data.remote;

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
 * Created by Chirag on 5/12/2019 at 14:00.
 * Project - MovieDB
 */
public class ResultResponseDeserializer implements JsonDeserializer<ResultResponse> {
    @Override
    public ResultResponse deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject() && json.getAsJsonObject().getAsJsonArray("results") != null) {
            return parseResponseResultObject(json.getAsJsonObject().getAsJsonArray("results"));
        } else {
            return resultInfo(json.getAsJsonObject());
        }
    }

    private ResultResponse parseResponseResultObject(JsonArray object) {
        ResultResponse response = new ResultResponse();
        if (object.size() != 0 && object.getAsJsonArray().size() != 0) {
            List<String> trailerKey = new ArrayList<>();
            List<String> reviewText = new ArrayList<>();
            List<String> reviewAuthor = new ArrayList<>();
            for (int i = 0; i < object.size(); i++) {
                JsonElement jsonElement = object.get(i);
                if (jsonElement.getAsJsonObject() != null) {
                    if (jsonElement.getAsJsonObject().get("key") != null) {
                        trailerKey.add(getStringOrEmpty(jsonElement, "key"));
                    }
                    if (jsonElement.getAsJsonObject().get("author") != null) {
                        reviewAuthor.add(getStringOrEmpty(jsonElement, "author"));
                    }
                    if (jsonElement.getAsJsonObject().get("content") != null)
                        reviewText.add(getStringOrEmpty(jsonElement, "content"));
                }
            }

            response.mKey = trailerKey;
            response.mReviewAuthor = reviewAuthor;
            response.mReviewText = reviewText;
        }
        return response;

    }

    private ResultResponse resultInfo(JsonObject object) {
        ResultResponse response = new ResultResponse();
        if (!object.isJsonNull() && object.getAsJsonArray("results") == null) {
            response.mId = (int) getDoubleOrZero(object, "id");
            response.mPoster = getStringOrEmpty(object, "poster_path");
            response.mTitle = getStringOrEmpty(object, "title");
            if (response.mTitle == null) {
                response.mTitle = getStringOrEmpty(object, "name");
            }
            response.mBackdropPath = getStringOrEmpty(object, "backdrop_path");
            response.mImdbId = getStringOrEmpty(object, "imdb_id");
            response.mOriginalLanguange = getStringOrEmpty(object,
                    "original_language");
            response.mOriginalTitle = getStringOrEmpty(object, "original_title");
            response.mOverView = getStringOrEmpty(object, "overview");
            response.mReleaseDate = getStringOrEmpty(object, "release_date");
            response.mRunTime = (int) getDoubleOrZero(object, "runtime");
            response.mVoteAvg = (int) getDoubleOrZero(object, "vote_average");
            response.mOriginalName = getStringOrEmpty(object, "original_name");
            //  Genre
            JsonArray genreArray = object.getAsJsonArray("genres");
            if (genreArray != null) {
                List<String> name = new ArrayList<>();
                for (int j = 0; j < genreArray.size(); j++) {
                    name.add(getStringOrEmpty(genreArray.get(j), "name"));
                }
                response.mName = name;
            }
            //  Season
            JsonArray seasonArray = object.getAsJsonArray("seasons");
            if (seasonArray != null) {
                List<String> seasonPoster = new ArrayList<>();
                List<String> seasonId = new ArrayList<>();
                for (int j = 0; j < seasonArray.size(); j++) {
                    seasonPoster.add(getStringOrEmpty(seasonArray.get(j), "poster_path"));
                    seasonId.add(getStringOrEmpty(seasonArray.get(j), "id"));
                }
                response.mSeasons = seasonPoster;
                response.mSeasonId = seasonId;
            }
        }
        return response;
    }

    private int getDoubleOrZero(JsonElement element, String member) {
        if (element == null) return 0;
        if (element.isJsonObject() && element.getAsJsonObject().has(member)) {
            if (element.getAsJsonObject().get(member) != null && !element.getAsJsonObject().get(member).isJsonNull())
                return element.getAsJsonObject().get(member).getAsInt();
        }
        return 0;
    }

    private String getStringOrEmpty(JsonElement element, String member) {
        return element.getAsJsonObject().get(member) != null ?
                element.getAsJsonObject().get(member).getAsString() : "";
    }

}
