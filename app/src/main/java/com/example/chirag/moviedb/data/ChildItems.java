package com.example.chirag.moviedb.data;

/**
 * MovieDB
 * Created by Chirag on 18/08/18.
 */
public class ChildItems {

    private String description;
    private int image;
    private String dirctor;
    private String cast;

    public ChildItems(String description, int image, String dirctor, String cast) {
        this.description = description;
        this.image = image;
        this.dirctor = dirctor;
        this.cast = cast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDirctor() {
        return dirctor;
    }

    public void setDirctor(String dirctor) {
        this.dirctor = dirctor;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
