package com.example.chirag.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.chirag.moviedb.util.DataType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
@Entity(tableName = "movie_id")
public class ResultResponse implements IResultResponse, Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primary_key")
    private int primaryKey;

    @ColumnInfo(name = "id")
    public Integer mId;
    @ColumnInfo(name = "poster")
    public String mPoster;
    @ColumnInfo(name = "title")
    public String mTitle;

    @ColumnInfo(name = "name")
    public List<String> mName;
    @ColumnInfo(name = "type")
    public String mType;
    @ColumnInfo(name = "content")
    public String mContent;
    @ColumnInfo(name = "backdrop_path")
    public String mBackdropPath;

    @ColumnInfo(name = "genre")
    public List<String> mGenre;
    @ColumnInfo(name = "genre_info")
    public String mGenreInfo;
    @ColumnInfo(name = "imdb_id")
    public String mImdbId;
    @ColumnInfo(name = "language")
    public String mOriginalLanguange;
    @ColumnInfo(name = "original_title")
    public String mOriginalTitle;
    @ColumnInfo(name = "overview")
    public String mOverView;
    @ColumnInfo(name = "release_date")
    public String mReleaseDate;
    @ColumnInfo(name = "runtime")
    public Integer mRunTime;
    @ColumnInfo(name = "vote_avg")
    public Integer mVoteAvg;
    @ColumnInfo(name = "airdate")
    public String mFirstAirDate;
    @ColumnInfo(name = "original_name")
    public String mOriginalName;

    @ColumnInfo(name = "seasons")
    public List<String> mSeasons;

    //  Review
    @ColumnInfo(name = "review_author")
    public List<String> mReviewAuthor;
    @ColumnInfo(name = "review_text")
    public List<String> mReviewText;

    //  Trailer
    @ColumnInfo(name = "trailer_key")
    public List<String> mKey;

    public ResultResponse() {
    }

    protected ResultResponse(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mPoster = in.readString();
        mTitle = in.readString();
        mName = in.createStringArrayList();
        mType = in.readString();
        mContent = in.readString();
        mBackdropPath = in.readString();
        mGenreInfo = in.readString();
        mImdbId = in.readString();
        mOriginalLanguange = in.readString();
        mOriginalTitle = in.readString();
        mOverView = in.readString();
        mReleaseDate = in.readString();
        if (in.readByte() == 0) {
            mRunTime = null;
        } else {
            mRunTime = in.readInt();
        }
        if (in.readByte() == 0) {
            mVoteAvg = null;
        } else {
            mVoteAvg = in.readInt();
        }
        mFirstAirDate = in.readString();
        mOriginalName = in.readString();
        mReviewAuthor = in.createStringArrayList();
        mReviewText = in.createStringArrayList();
        mKey = in.createStringArrayList();
        mGenre = in.createStringArrayList();
        mSeasons = in.createStringArrayList();
    }

    public static final Creator<ResultResponse> CREATOR = new Creator<ResultResponse>() {
        @Override
        public ResultResponse createFromParcel(Parcel in) {
            return new ResultResponse(in);
        }

        @Override
        public ResultResponse[] newArray(int size) {
            return new ResultResponse[size];
        }
    };

    @Override
    public Integer getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
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
    public List<String> getGenre() {
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
    public Integer getVoteAvg() {
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
    public List<String> getSeasons() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mId);
        }
        dest.writeString(mPoster);
        dest.writeString(mTitle);
        dest.writeStringList(mName);
        dest.writeString(mType);
        dest.writeString(mContent);
        dest.writeString(mBackdropPath);
        dest.writeString(mGenreInfo);
        dest.writeString(mImdbId);
        dest.writeString(mOriginalLanguange);
        dest.writeString(mOriginalTitle);
        dest.writeString(mOverView);
        dest.writeString(mReleaseDate);

        if (mRunTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mRunTime);
        }
        if (mVoteAvg == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mVoteAvg);
        }
        dest.writeString(mFirstAirDate);
        dest.writeString(mOriginalName);
        dest.writeStringList(mReviewAuthor);
        dest.writeStringList(mReviewText);
        dest.writeStringList(mKey);
        dest.writeStringList(mGenre);
        dest.writeStringList(mSeasons);
    }
}