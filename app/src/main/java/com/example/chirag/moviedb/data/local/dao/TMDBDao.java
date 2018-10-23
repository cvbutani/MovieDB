package com.example.chirag.moviedb.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.TrailerResponse;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
@Dao
public interface TMDBDao {

    /**
     * Select all movies from the movie table.
     *
     * @return all movies.
     */
    @Query("SELECT * FROM movie_id WHERE type= :value AND content= :key")
    List<ResultResponse> getMovieId(String value, String key);

    /**
     * Select a movie by id.
     *
     * @param movieId the movie Id.
     * @return movie information with movieId.
     */
    @Query("SELECT * FROM movie_info WHERE id = :movieId ")
    TMDB getMovieInfo(int movieId);

    @Query("SELECT * FROM favourite WHERE email= :emailId")
    List<Favourite> getFavouriteInfo(String emailId);

    @Query("SELECT * FROM review WHERE movieId= :id")
    List<ReviewResponse> getReviews(int id);

    @Query("SELECT * FROM trailer WHERE movieId= :id")
    List<TrailerResponse> getTrailers(int id);

    @Insert(onConflict = REPLACE)
    void updateTMDBInfo(Favourite data);
    /**
     * Insert a movie in the database. If the movie already exists then it will still add it to database.
     *
     * @param movie the movie to be inserted.
     */
    @Insert
    void insertMovieId(ResultResponse movie);

    @Insert
    void insertMovieInfo(TMDB movieInfo);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertReviews(ReviewResponse response);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTrailer(TrailerResponse response);

    @Query("DELETE FROM trailer WHERE movieId= :value")
    void deleteTrailers(int value);

    /**
     * Delete all Result.
     */
    @Query("DELETE FROM movie_id WHERE type= :value")
    void deleteMovieId(String value);

    @Query("DELETE FROM movie_info WHERE id= :value")
    void deleteMovieInfo(int value);

}
