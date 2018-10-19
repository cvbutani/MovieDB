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
 * Created by Chirag on 18/10/18.
 */
@Entity(tableName = "movie_info")
public class MovieInfo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "primaryKey")
    private int primaryKey;

    @SerializedName("backdrop_path")
    @Expose
    @Nullable
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @SerializedName("genres")
    @Expose
    @Ignore
    private List<Genre> genres = null;

    @Nullable
    @ColumnInfo(name = "genre")
    private String genreInfo;

    @SerializedName("id")
    @Expose
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("imdb_id")
    @Expose
    @Nullable
    @ColumnInfo(name = "imdb_id")
    private String imdbId;

    @SerializedName("original_language")
    @Expose
    @Nullable
    @ColumnInfo(name = "language")
    private String originalLanguage;

    @SerializedName("original_title")
    @Expose
    @Nullable
    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @SerializedName("overview")
    @Expose
    @Nullable
    @ColumnInfo(name = "overview")
    private String overview;

    //    @SerializedName("popularity")
//    @Expose
//    private Double popularity;

    @SerializedName("poster_path")
    @Expose
    @Nullable
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    @Nullable
    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @SerializedName("runtime")
    @Expose
    @Nullable
    @ColumnInfo(name = "runtime")
    private Integer runtime;

    @SerializedName("vote_average")
    @Expose
    @Nullable
    @ColumnInfo(name = "vote_average")
    private Double voteAverage;

    /**
     * No args constructor for use in serialization
     */
    public MovieInfo() {
    }

    public MovieInfo(@Nullable String backdropPath, List<Genre> genres, @Nullable String genreInfo,
                     @NonNull Integer id, @Nullable String imdbId, @Nullable String originalLanguage,
                     @Nullable String originalTitle, @Nullable String overview, @Nullable String posterPath,
                     @Nullable String releaseDate, @Nullable Integer runtime, @Nullable Double voteAverage) {
        this.backdropPath = backdropPath;
        this.genres = genres;
        this.genreInfo = genreInfo;
        this.id = id;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
    }

    @NonNull
    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(@NonNull int primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Nullable
    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(@Nullable String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setGenreInfo(@Nullable String genreInfo) {
        this.genreInfo = genreInfo;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @Nullable
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(@Nullable String imdbId) {
        this.imdbId = imdbId;
    }

    @Nullable
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(@Nullable String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @Nullable
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(@Nullable String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Nullable
    public String getOverview() {
        return overview;
    }

    public void setOverview(@Nullable String overview) {
        this.overview = overview;
    }

    @Nullable
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(@Nullable String posterPath) {
        this.posterPath = posterPath;
    }

    @Nullable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@Nullable String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Nullable
    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(@Nullable Integer runtime) {
        this.runtime = runtime;
    }

    @Nullable
    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(@Nullable Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getGenreInfo() {
        StringBuilder genre = new StringBuilder();
        genre.append(getGenres().get(0).getName());
        for (int i=1; i<getGenres().size(); i++) {
            genre.append(", ");
            genre.append(getGenres().get(i).getName());
        }
        return genre.toString();
    }
}
