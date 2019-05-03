package com.example.chirag.moviedb.data.local;

import androidx.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.TMDBDao;
import com.example.chirag.moviedb.data.local.dao.UserDao;
import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.util.AppExecutors;

import java.util.List;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_NOW_PLAYING;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_POPULAR;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_TOP_RATED;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_UPCOMING;

/**
 * MovieDB
 * Created by Chirag on 24/09/18.
 */
public class LocalService implements RepositoryContract {

    private static volatile LocalService INSTANCE;

    private TMDBDao mTMDBDao;

    private UserDao mUserDao;

    private AppExecutors mAppExecutors;

    private LocalService(@NonNull AppExecutors appExecutors,
                         @NonNull TMDBDao TMDBDao, @NonNull UserDao userDao) {
        mAppExecutors = appExecutors;
        mTMDBDao = TMDBDao;
        mUserDao = userDao;
    }

    public static LocalService getInstance(@NonNull AppExecutors appExecutors,
                                           @NonNull TMDBDao TMDBDao, @NonNull UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (LocalService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalService(appExecutors, TMDBDao, userDao);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getMovieInfoRepo(int movieId, OnTaskCompletion.OnGetMovieInfoCompletion callback) {

    }

    @Override
    public void getTvInfoRepo(int tvId, OnTaskCompletion.OnGetTvInfoCompletion callback) {

    }

    @Override
    public void getPopularMoviesRepo(OnTaskCompletion.OnGetMovieCompletion callback) {

    }

    @Override
    public void getNowPlayingMoviesRepo(OnTaskCompletion.OnGetNowPlayingCompletion callback) {

    }

    @Override
    public void getTopRatedMoviesRepo(OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {

    }

    @Override
    public void getUpcomingMoviesRepo(OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {

    }

    @Override
    public void getTrailersRepo(int movieId, OnTaskCompletion.OnGetTrailerCompletion callback) {

    }

    @Override
    public void getReviewsRepo(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {

    }

    @Override
    public void getSimilarMoviesRepo(int movieId, OnTaskCompletion.OnGetSimilarMovieCompletion callback) {

    }

    @Override
    public void getPopularTvRepo(OnTaskCompletion.OnGetPopularTvCompletion callback) {

    }

    @Override
    public void getTopRatedTvRepo(OnTaskCompletion.GetTopRatedTvCompletion callback) {

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

    @Override
    public void updateTMDBRepo(Favourite info) {

    }

    @Override
    public void getFavouriteTMDBRepo(String emailId, OnTaskCompletion.GetFavouriteTMDBCompletion callback) {

    }
}
