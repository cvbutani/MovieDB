package com.example.chirag.moviedb.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Maybe;
import io.reactivex.Single;

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

    @Query("SELECT * FROM user WHERE email_address=:emailId")
    User getUserInfo(String emailId);

    @Insert
    void insertUserRegisterInfo(User user);

    @Update
    void updateUserAccountInfo(User user);
}
