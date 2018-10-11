package com.example.chirag.moviedb.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.chirag.moviedb.data.model.TrailerResponse;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 07/10/18.
 */
@Dao
public interface TrailerDao {

    @Query("SELECT * FROM trailer WHERE movieId= :id")
    List<TrailerResponse> getTrailers(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTrailer(TrailerResponse response);

    @Query("DELETE FROM trailer WHERE movieId= :value")
    void deleteTrailers(int value);
}
