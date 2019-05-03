package com.example.chirag.moviedb.data.model;

import java.util.List;

/**
 * Created by Chirag on 5/2/2019 at 21:57.
 * Project - MovieDB
 */
public interface TMDBInterface {

    String getBackDropPath();
    List<Genre> getGenre();
    String getGenreInfo();
    Integer getID();
    String getImdbId();
    String getOriginalLanguange();
    String getOriginalTitle();
    String getOverView();
    String getPosterPath();
    String getReleaseDate();
    Integer getRunTime();
    Double getVoteAvg();
    String getName();
    String getFirstAirDate();
    String getOriginalName();
    List<Season> getSeasons();
}
