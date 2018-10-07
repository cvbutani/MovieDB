package com.example.chirag.moviedb.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.chirag.moviedb.data.model.ReviewResponse;


import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 07/10/18.
 */
@Dao
public interface ReviewDao {
    @Query("SELECT * FROM review WHERE movieId= :id")
    List<ReviewResponse> getReviews(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertReviews(ReviewResponse response);

}
