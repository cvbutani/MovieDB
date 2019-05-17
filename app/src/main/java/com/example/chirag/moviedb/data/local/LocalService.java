package com.example.chirag.moviedb.data.local;

import androidx.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.TMDBDao;
import com.example.chirag.moviedb.data.local.dao.UserDao;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
public class LocalService implements RepositoryContract {

    private static volatile LocalService INSTANCE;

    private TMDBDao mTMDBDao;

    private UserDao mUserDao;

    private LocalService(@NonNull TMDBDao TMDBDao, @NonNull UserDao userDao) {
        mTMDBDao = TMDBDao;
        mUserDao = userDao;
    }

    public static LocalService getInstance(
            @NonNull TMDBDao TMDBDao, @NonNull UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (LocalService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalService(TMDBDao, userDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getUserSignInInfo(OnTaskCompletion.GetUserSignInCompletion callback) {

    }

    @Override
    public void insertUserSignInInfo(User user) {

    }

    @Override
    public void getUserAccountInfo(String emailId,
                                   OnTaskCompletion.GetUserAccountCompletion callback) {

    }

    @Override
    public void updateUserAccount(User user) {

    }

}
