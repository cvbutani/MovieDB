package com.example.chirag.moviedb.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.chirag.moviedb.data.local.dao.MovieDao;
import com.example.chirag.moviedb.data.local.dao.ReviewDao;
import com.example.chirag.moviedb.data.local.dao.TrailerDao;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.TrailerResponse;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
@Database(entities = {ResultResponse.class, TrailerResponse.class, ReviewResponse.class, MovieInfo.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase INSTANCE;

    public abstract MovieDao localDao();

    public abstract TrailerDao trailerDao();

    public abstract ReviewDao reviewDao();

    private static final Object sLock = new Object();

    public static LocalDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LocalDatabase.class, "Movie.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
