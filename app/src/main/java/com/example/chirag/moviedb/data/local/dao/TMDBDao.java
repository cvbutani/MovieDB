package com.example.chirag.moviedb.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.Flowable;
import io.reactivex.Single;

import com.example.chirag.moviedb.data.model.ResultResponse;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
@Dao
public interface TMDBDao {
    //
//    /**
//     * Select all movies from the movie table.
//     *
//     * @return all movies.
//     */
    @Query("SELECT * FROM movie_id WHERE type= :value AND content= :key")
    Single<List<ResultResponse>> getMovieId(String value, String key);

    //
//    /**
//     * Select a movie by id.
//     *
//     * @param movieId the movie Id.
//     * @return movie information with movieId.
//     */
    @Query("SELECT * FROM movie_id WHERE id = :movieId AND type= :value AND content= :key")
    Flowable<ResultResponse> getMovieInfo(int movieId, String value, String key);

    //
//    /**
//     * Select all favourite movie/tv info by email Id.
//     *
//     * @param emailId the movie Id.
//     * @return favourite movie/tv information with movieId.
//     */
//    @Query("SELECT * FROM favourite WHERE email= :emailId")
//    List<Favourite> getFavouriteInfo(String emailId);
//
//    /**
//     * Select reviews by id.
//     *
//     * @param id the movie Id.
//     * @return movie reviews with movie Id.
//     */
//    @Query("SELECT * FROM review WHERE movieId= :id")
//    List<ReviewResponse> getReviews(int id);
//
//    /**
//     * Select trailers by id.
//     *
//     * @param id the movie Id.
//     * @return movie trailers with movie Id.
//     */
//    @Query("SELECT * FROM trailer WHERE movieId= :id")
//    List<TrailerResponse> getTrailers(int id);
//
//    /**
//     * Insert favourite Movie/TV in database.
//     *
//     * @param data the favourite movie/tv info.
//     */
//    @Insert(onConflict = REPLACE)
//    void updateTMDBInfo(Favourite data);
    /**
     * Insert resultResponse in the database. If the movie already exists then it will still add
     * it to database.
     *
     * @param movie the resultResponse to be inserted.
     */
//    @Insert
//    void insertMovieId(ResultResponse movie);
//

    /**
     * Insert a movie in the database. If the movie already exists then it will still add it to
     * database.
     *
     * @param movieInfo the movie to be inserted.
     */
    @Insert
    void insertMovieInfo(ResultResponse movieInfo);
//
//    /**
//     * Insert movie/tv reviews in database
//     *
//     * @param response to be inserted in database
//     */
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insertReviews(ReviewResponse response);
//
//    /**
//     * Insert movie/tv trailer in database
//     *
//     * @param response to be inserted in database
//     */
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insertTrailer(TrailerResponse response);
//
//    @Query("DELETE FROM trailer WHERE movieId= :value")
//    void deleteTrailers(int value);
//

    /**
     * Delete all Result.
     */
    @Query("DELETE FROM movie_id WHERE type= :value")
    void deleteMovieId(String value);

    /**
     * Delete all Result.
     */
    @Query("DELETE FROM movie_id WHERE id= :id AND type= :value AND content= :key")
    void deleteMovieInfo(int id, String value, String key);

}
