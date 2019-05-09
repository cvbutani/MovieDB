package com.example.chirag.moviedb.data.local;
//

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
//
import com.example.chirag.moviedb.data.local.dao.TMDBDao;
import com.example.chirag.moviedb.data.local.dao.UserDao;
import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.model.User;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
@Database(entities = {ResultResponse.class},
        version = 1,
        exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase INSTANCE;

    public abstract TMDBDao localDao();

//    public abstract UserDao userDao();

    public static synchronized LocalDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "Movie.db")
                    .build();
        }
        return INSTANCE;
    }
}
