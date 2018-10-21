package com.example.chirag.moviedb.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.chirag.moviedb.data.model.User;

import java.util.List;


/**
 * MovieDB
 * Created by Chirag on 21/10/18.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getSignInDetail();

    @Insert
    void insertUserRegisterInfo(User user);
}
