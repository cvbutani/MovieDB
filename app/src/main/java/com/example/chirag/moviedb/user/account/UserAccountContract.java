package com.example.chirag.moviedb.user.account;

import com.example.chirag.moviedb.data.model.User;

public interface UserAccountContract {
    interface View {
        void saveCurrentUser();

        void getUserDetail(User user);

        void saveUserFailure(String errorMessage);
    }

    interface Presenter {
        void saveUser(User user);

        void getUserAccountDetail(String emailId);

        void attachView(UserAccountContract.View view, String email);
    }
}
