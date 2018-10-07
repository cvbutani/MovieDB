package com.example.chirag.moviedb.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.chirag.moviedb.data.model.MovieResponse;
import com.example.chirag.moviedb.data.model.TrailerResponse;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
@Database(entities = {MovieResponse.class, TrailerResponse.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase INSTANCE;

    public abstract LocalDao loacalDao();

    public abstract TrailerDao trailerDao();

    private static final Object sLock = new Object();

    public static LocalDatabase getInstance(Context context) {
        synchronized(sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LocalDatabase.class, "Movie.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
