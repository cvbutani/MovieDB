package com.example.chirag.moviedb.login;

import com.example.chirag.moviedb.data.model.User;

import java.util.List;

/**
 * MovieDB
 * Created by Chirag on 21/10/18.
 */
public interface SignInContract {
    interface View {
        void getUserDetail(List<User> user);

        void getSignInError(String errorMessage);

        void createNewUser();
    }

    interface Presenter {
        void getUserAccountDetail();

        void createNewUserAccount(User user);

        void attachView(SignInContract.View view);
    }
}
