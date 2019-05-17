package com.example.chirag.moviedb.user.account;

import android.content.Context;

import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.remote.RemoteService;

public class UserAccountPresenter implements UserAccountContract.Presenter {

    private UserAccountContract.View mCallback;

    private Repository mRepository;

    UserAccountPresenter(Context context, boolean isConnected) {
        LocalService mLocalService = LocalService.getInstance(
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).userDao());
        RemoteService mRemoteService =
                RemoteService.getInstance(LocalDatabase.getInstance(context).localDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void saveUser(User user) {
        mRepository.updateUserAccount(user);
    }

    @Override
    public void getUserAccountDetail(String emailId) {
        mRepository.getUserAccountInfo(emailId, new OnTaskCompletion.GetUserAccountCompletion() {
            @Override
            public void getUserAccountSuccess(User user) {
                mCallback.getUserDetail(user);
            }

            @Override
            public void getUserAccountFailure(String errorMessage) {
                mCallback.saveUserFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(UserAccountContract.View view, String email) {
        mCallback = view;
        getUserAccountDetail(email);
    }
}
