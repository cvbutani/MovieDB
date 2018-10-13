package com.example.chirag.moviedb.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
@Entity(tableName = "movie")
public class MovieResponse implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "primaryKey")
    private int primaryKey;

    @SerializedName("id")
    @Expose
    @NonNull
    @ColumnInfo(name = "entryid")
    private Integer id;

    @SerializedName("vote_average")
    @Expose
    @Nullable
    @ColumnInfo(name = "vote")
    private Double voteAverage;

    @SerializedName("poster_path")
    @Expose
    @Nullable
    @ColumnInfo(name = "poster")
    private String poster;

    @SerializedName("original_language")
    @Expose
    @Nullable
    @ColumnInfo(name = "language")
    private String originalLanguage;

    @SerializedName("genre_ids")
    @Expose
    @Ignore
    private List<Integer> genreIds = null;

    @SerializedName("backdrop_path")
    @Expose
    @Nullable
    @ColumnInfo(name = "backdrop")
    private String backdropPath;

    @SerializedName("overview")
    @Expose
    @Nullable
    @ColumnInfo(name = "overview")
    private String description;

    @SerializedName("release_date")
    @Expose
    @Nullable
    @ColumnInfo(name = "date")
    private String releaseDate;

    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("title")
    @Expose
    @Nullable
    @ColumnInfo(name = "title")
    private String title;

    @Nullable
    @ColumnInfo(name = "type")
    private String type;

    @Nullable
    @ColumnInfo(name = "genre")
    private String genre;


    /**
     * No args constructor for use in serialization
     */
    public MovieResponse() {
    }

    @Ignore
    public MovieResponse(@NonNull int primaryKey,@NonNull Integer id, @Nullable Double voteAverage, @Nullable String poster, @Nullable String originalLanguage, @Nullable List<Integer> genreIds, @Nullable String backdropPath, @Nullable String description, @Nullable String releaseDate, String originalName, @Nullable String title, @Nullable String type, @Nullable String genre) {
        this.primaryKey = primaryKey;
        this.id = id;
        this.voteAverage = voteAverage;
        this.poster = poster;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.description = description;
        this.releaseDate = releaseDate;
        this.originalName = originalName;
        this.title = title;
        this.type = type;
        this.genre = genre;
    }

    @Ignore
    public MovieResponse(@NonNull Integer mId, @Nullable String mTitle, @Nullable Double mVote, @Nullable String mPoster, @Nullable String mLanguage, @Nullable String mBackdropPoster, @Nullable String mOverview, @Nullable String mReleaseDate, @Nullable String type) {
        this.id = mId;
        this.title = mTitle;
        this.voteAverage = mVote;
        this.poster = mPoster;
        this.originalLanguage = mLanguage;
        this.backdropPath = mBackdropPoster;
        this.description = mOverview;
        this.releaseDate = mReleaseDate;
        this.type = type;
    }

    @NonNull
    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(@NonNull int primaryKey) {
        this.primaryKey = primaryKey;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @Nullable
    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(@Nullable Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Nullable
    public String getPoster() {
        return poster;
    }

    public void setPoster(@Nullable String poster) {
        this.poster = poster;
    }

    @Nullable
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(@Nullable String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @Nullable
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(@Nullable List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    @Nullable
    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(@Nullable String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@Nullable String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGenre(@Nullable String genre) {
        this.genre = genre;
    }

    @Nullable
    public String getGenre() {
        return genre;
    }

    public String getMovieGenre() {
        StringBuilder genreAll = new StringBuilder();
        genreAll.append(genreString(genreIds.get(0)));
        for (int i = 1; i < genreIds.size(); i++) {
            genreAll.append(", ");
            genreAll.append(genreString(genreIds.get(i)));
        }
        return genreAll.toString();
    }
    String genreString(int genreId) {
        String genre;
        switch (genreId) {
            case 28:
                genre = "Action";
                break;
            case 12:
                genre = "Adventure";
                break;
            case 16:
                genre = "Animation";
                break;
            case 35:
                genre = "Comedy";
                break;
            case 80:
                genre = "Crime";
                break;
            case 99:
                genre = "Documentary";
                break;
            case 18:
                genre = "Drama";
                break;
            case 10751:
                genre = "Family";
                break;
            case 14:
                genre = "Fantasy";
                break;
            case 36:
                genre = "History";
                break;
            case 27:
                genre = "Horror";
                break;
            case 10402:
                genre = "Music";
                break;
            case 9648:
                genre = "Mystery";
                break;
            case 10749:
                genre = "Romance";
                break;
            case 878:
                genre = "Science Fiction";
                break;
            case 10770:
                genre = "TV Movie";
                break;
            case 53:
                genre = "Thriller";
                break;
            case 10752:
                genre = "War";
                break;
            case 37:
                genre = "Western";
                break;
            default:
                genre = "unknown";
                break;
        }
        return genre;
    }

}