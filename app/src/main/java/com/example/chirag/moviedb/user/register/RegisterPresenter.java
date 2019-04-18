package com.example.chirag.moviedb.user.register;

import android.content.Context;

import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mCallback;

    private Repository mRepository;

    public RegisterPresenter(Context context, boolean isConnected) {
        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).userDao());
        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void registerUser(User user) {
        mRepository.insertUserSignInInfo(user);
    }

    @Override
    public void getUserAccountDetail() {
        mRepository.getUserSignInInfo(new OnTaskCompletion.GetUserSignInCompletion() {
            @Override
            public void getUserInfoSuccess(List<User> user) {
                mCallback.getUserDetail(user);
            }

            @Override
            public void getUserInfoFailure(String errorMessage) {
                mCallback.createNewUserFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(RegisterContract.View view) {
        mCallback = view;
        getUserAccountDetail();
    }
}
