package com.example.chirag.moviedb.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.chirag.moviedb.data.model.GenreResponse;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * MovieDB
 * Created by Chirag on 13/10/18.
 */
@Dao
public interface GenreDao {

    @Insert(onConflict = REPLACE)
    void insertGenres(GenreResponse response);

    @Query("SELECT * FROM genre")
    List<GenreResponse> getGenres();
}
