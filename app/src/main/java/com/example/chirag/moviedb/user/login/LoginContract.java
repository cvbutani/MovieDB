package com.example.chirag.moviedb.user.login;

import com.example.chirag.moviedb.data.model.User;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 21/10/18.
 */
public interface LoginContract {
    interface View {
        void getUserDetail(List<User> user);

        void getSignInError(String errorMessage);

    }

    interface Presenter {
        void getUserAccountDetail();

        void attachView(LoginContract.View view);
    }
}
