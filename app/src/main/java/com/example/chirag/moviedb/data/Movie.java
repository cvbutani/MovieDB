//package com.example.chirag.moviedb.data;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
//import android.arch.persistence.room.PrimaryKey;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//
///**
// * MovieDB
// * Created by Chirag on 29/09/18.
// */
//@Entity(tableName = "movie")
//public final class Movie {
//
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "entryid")
//    private final Integer mId;
//
//    @Nullable
//    @ColumnInfo(name = "title")
//    private final String mTitle;
//
//    @Nullable
//    @ColumnInfo(name = "vote")
//    private final Double mVote;
//
//    @Nullable
//    @ColumnInfo(name = "poster")
//    private final String mPoster;
//
//    @Nullable
//    @ColumnInfo(name = "language")
//    private final String mLanguage;
//
//    @Nullable
//    @ColumnInfo(name = "genre")
//    private final String mGenre;
//
//    @Nullable
//    @ColumnInfo(name = "backdrop")
//    private final String mBackdropPoster;
//
//    @Nullable
//    @ColumnInfo(name = "overview")
//    private final String mOverview;
//
//    @Nullable
//    @ColumnInfo(name = "date")
//    private final String mReleaseDate;
//
//    @Nullable
//    @ColumnInfo(name = "trailer")
//    private final String mTrailerKey;
//
//    @Nullable
//    @ColumnInfo(name = "author")
//    private final String mReviewAuthor;
//
//    @Nullable
//    @ColumnInfo(name = "review")
//    private final String mReviewContent;
//
//    @Ignore
//    public Movie(@NonNull Integer mId, @Nullable String mTitle, @Nullable Double mVote, @Nullable String mPoster, @Nullable String mLanguage, @Nullable String mGenre, @Nullable String mBackdropPoster, @Nullable String mOverview, @Nullable String mReleaseDate) {
//        this(mId, mTitle, mVote, mPoster, mLanguage, mGenre, mBackdropPoster, mOverview, mReleaseDate, null, null, null);
//    }
//
//    @Ignore
//    public Movie(@NonNull Integer mId, @Nullable String mTitle, @Nullable Double mVote, @Nullable String mPoster, @Nullable String mLanguage, @Nullable String mGenre, @Nullable String mBackdropPoster, @Nullable String mOverview, @Nullable String mReleaseDate, @Nullable String mTrailerKey, @Nullable String mReviewAuthor, @Nullable String mReviewContent) {
//        this.mId = mId;
//        this.mTitle = mTitle;
//        this.mVote = mVote;
//        this.mPoster = mPoster;
//        this.mLanguage = mLanguage;
//        this.mGenre = mGenre;
//        this.mBackdropPoster = mBackdropPoster;
//        this.mOverview = mOverview;
//        this.mReleaseDate = mReleaseDate;
//        this.mTrailerKey = mTrailerKey;
//        this.mReviewAuthor = mReviewAuthor;
//        this.mReviewContent = mReviewContent;
//    }
//
//
//    @Ignore
//    public Movie(@NonNull Integer mId, String author, String review) {
//        this(mId, null, null, null, null, null, null, null, null, null, author, review);
//    }
//
//    @Ignore
//    public Movie(@NonNull Integer mId, String key) {
//        this(mId, null, null, null, null, null, null, null, null, key, null, null);
//    }
//
//
//    @NonNull
//    public Integer getmId() {
//        return mId;
//    }
//
//    @Nullable
//    public String getmTitle() {
//        return mTitle;
//    }
//
//    @Nullable
//    public Double getmVote() {
//        return mVote;
//    }
//
//    @Nullable
//    public String getmPoster() {
//        return mPoster;
//    }
//
//    @Nullable
//    public String getmLanguage() {
//        return mLanguage;
//    }
//
//    @Nullable
//    public String getmGenre() {
//        return mGenre;
//    }
//
//    @Nullable
//    public String getmBackdropPoster() {
//        return mBackdropPoster;
//    }
//
//    @Nullable
//    public String getmOverview() {
//        return mOverview;
//    }
//
//    @Nullable
//    public String getmReleaseDate() {
//        return mReleaseDate;
//    }
//
//    @Nullable
//    public String getmTrailerKey() {
//        return mTrailerKey;
//    }
//
//    @Nullable
//    public String getmReviewAuthor() {
//        return mReviewAuthor;
//    }
//
//    @Nullable
//    public String getmReviewContent() {
//        return mReviewContent;
//    }
//}
