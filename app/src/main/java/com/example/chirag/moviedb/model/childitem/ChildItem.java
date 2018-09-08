package com.example.chirag.moviedb.model.childitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 06/09/18.
 */
public class ChildItem {

    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ChildItem() {
    }

    /**
     *
     * @param cast
     * @param crew
     */
    public ChildItem(List<Cast> cast, List<Crew> crew) {
        super();
        this.cast = cast;
        this.crew = crew;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

}