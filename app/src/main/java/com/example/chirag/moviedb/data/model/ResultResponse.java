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
import java.util.List;


/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
//@Entity(tableName = "movie_id")
public class ResultResponse implements IResultResponse {

    public Integer mId;
    public String mPoster;
    public String mTitle;
    public List<String> mName;
    public String mType;
    public String mContent;
    public String mBackdropPath;
    public List<Genre> mGenre;
    public String mGenreInfo;
    public String mImdbId;
    public String mOriginalLanguange;
    public String mOriginalTitle;
    public String mOverView;
    public String mReleaseDate;
    public Integer mRunTime;
    public Double mVoteAvg;
    public String mFirstAirDate;
    public String mOriginalName;
    public List<Season> mSeasons;

    //  Review
    public List<String> mReviewAuthor;
    public List<String> mReviewText;

    //  Trailer
    public List<String> mKey;

    public ResultResponse() {
    }

    @Override
    public Integer getId() {
        return mId;
    }

    @Override
    public String getPoster() {
        return mPoster;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public List<String> getName() {
        return mName;
    }

    @Override
    public String getType() {
        return mType;
    }

    @Override
    public String getContent() {
        return mContent;
    }

    @Override
    public String getBackDropPath() {
        return mBackdropPath;
    }

    @Override
    public List<Genre> getGenre() {
        return mGenre;
    }

    @Override
    public String getGenreInfo() {
        return mGenreInfo;
    }

    @Override
    public String getImdbId() {
        return mImdbId;
    }

    @Override
    public String getOriginalLanguange() {
        return mOriginalLanguange;
    }

    @Override
    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    @Override
    public String getOverView() {
        return mOverView;
    }

    @Override
    public String getReleaseDate() {
        return mReleaseDate;
    }

    @Override
    public Integer getRunTime() {
        return mRunTime;
    }

    @Override
    public Double getVoteAvg() {
        return mVoteAvg;
    }

    @Override
    public String getFirstAirDate() {
        return mFirstAirDate;
    }

    @Override
    public String getOriginalName() {
        return mOriginalName;
    }

    @Override
    public List<Season> getSeasons() {
        return mSeasons;
    }

    @Override
    public List<String> getReviewAuthor() {
        return mReviewAuthor;
    }

    @Override
    public List<String> getReviewText() {
        return mReviewText;
    }

    @Override
    public List<String> getTrailerKey() {
        return mKey;
    }
}