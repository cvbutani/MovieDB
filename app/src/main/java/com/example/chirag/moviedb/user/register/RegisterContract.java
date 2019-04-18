package com.example.chirag.moviedb.user.register;

import com.example.chirag.moviedb.data.model.User;

import java.util.List;

public interface RegisterContract {
    interface View {
        void createNewUser();

        void getUserDetail(List<User> user);

        void createNewUserFailure(String errorMessage);
    }

    interface Presenter {
        void registerUser(User user);

        void getUserAccountDetail();

        void attachView(RegisterContract.View view);
    }
}
