package com.example.chirag.moviedb.model.childitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * MovieDB
 * Created by Chirag on 07/09/18.
 */
public class Crew {

    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public Crew() {
    }

    public Crew(String job, String name) {
        this.job = job;
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
