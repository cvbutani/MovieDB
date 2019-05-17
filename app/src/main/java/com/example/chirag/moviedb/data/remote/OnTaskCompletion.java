package com.example.chirag.moviedb.data.remote;

import com.example.chirag.moviedb.data.model.User;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public interface OnTaskCompletion {

    interface GetUserSignInCompletion {
        void getUserInfoSuccess(List<User> user);

        void getUserInfoFailure(String errorMessage);
    }

    interface GetUserAccountCompletion {
        void getUserAccountSuccess(User user);

        void getUserAccountFailure(String errorMessage);
    }
}
