package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 29/09/18.
 */
public interface RepositoryContract {

    void getUserSignInInfo(final OnTaskCompletion.GetUserSignInCompletion callback);

    void insertUserSignInInfo(User user);

    void getUserAccountInfo(String emailId,
                            final OnTaskCompletion.GetUserAccountCompletion callback);

    void updateUserAccount(User user);

}
