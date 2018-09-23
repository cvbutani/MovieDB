package com.example.chirag.moviedb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class ResultHeaderItem implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("poster_path")
    @Expose
    private String poster;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("overview")
    @Expose
    private String description;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * No args constructor for use in serialization
     */
    public ResultHeaderItem() {
    }

    public ResultHeaderItem(Integer id, Double voteAverage, String title, String poster, String originalLanguage, List<Integer> genreIds, String backdropPath, String overview, String releaseDate, String originalName) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.poster = poster;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.description = overview;
        this.releaseDate = releaseDate;
        this.originalName = originalName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Integer> getGenreId() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}