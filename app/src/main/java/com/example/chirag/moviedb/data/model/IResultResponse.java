package com.example.chirag.moviedb.data.model;

import java.util.List;

/**
 * Created by Chirag on 5/2/2019 at 22:38.
 * Project - MovieDB
 */
public interface IResultResponse {
    Integer getId();

    String getPoster();

    String getTitle();

    List<String> getName();

    String getType();

    String getContent();

    String getBackDropPath();

    List<Genre> getGenre();

    String getGenreInfo();

    String getImdbId();

    String getOriginalLanguange();

    String getOriginalTitle();

    String getOverView();

    String getReleaseDate();

    Integer getRunTime();

    Double getVoteAvg();

    String getFirstAirDate();

    String getOriginalName();

    List<Season> getSeasons();

    List<String> getReviewAuthor();

    List<String> getReviewText();

    List<String> getTrailerKey();
}
