package com.example.chirag.moviedb.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.chirag.moviedb.data.model.ResultResponse;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
@Dao
public interface MovieDao {

    /**
     * Select all movies from the movie table.
     *
     * @return all movies.
     */
    @Query("SELECT * FROM movie WHERE type= :value")
    List<ResultResponse> getMovie(String value);

    /**
     * Select a movie by id.
     *
     * @param movieId the movie Id.
     * @return movie information with movieId.
     */
    @Query("SELECT * FROM movie WHERE id = :movieId ")
    ResultResponse getMovieById(int movieId);

    /**
     * Insert a movie in the database. If the movie already exists then it will still add it to database.
     *
     * @param movie the movie to be inserted.
     */
    @Insert
    void insertMovie(ResultResponse movie);

    @Query("UPDATE movie SET type =:newGenre WHERE id= :movieId")
    void insertGenre(String newGenre, int movieId);

//    /**
//     * Update a movie
//     *
//     * @param movie movie to be updated
//     * @return the number of movies updated. This should always be 1.
//     */
//    @Update
//    int UpdateMovie(ResultResponse movie);

//    /**
//     * Get a movie by Id. Update its author and review column.
//     *
//     * @param movieId movie to be updated
//     * @param author author to be updated
//     * @param review review to be updated
//     */
//    @Query("UPDATE movie SET author= :author, review = :review WHERE entryid = :movieId")
//    void insertReviewInMovie(String movieId, String author, String review);
//
//    /**
//     * Get a movie by Id. Update its trailer column.
//     *
//     * @param movieId movie to be updated
//     * @param trailerId trailer to be updated
//     */
//    @Query("UPDATE movie SET trailer= :trailerId WHERE entryid = :movieId")
//    void insertTrailerInMovie(String movieId, String trailerId);

    /**
     * Delete all Result.
     */
    @Query("DELETE FROM movie WHERE type= :value")
    void deleteMovies(String value);

}
