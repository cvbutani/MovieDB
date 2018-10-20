package com.example.chirag.moviedb.moviedetail;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.util.AppExecutors;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private Repository mRepository;

    private MovieDetailContract.View mCallback;

    MovieDetailPresenter(Context context, boolean isConnected) {
        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao());
        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getMovieInfo(final int movieId) {
        mRepository.getMovieInfoData(movieId, new OnTaskCompletion.OnGetMovieInfoCompletion() {
            @Override
            public void getMovieInfoSuccess(TMDB data) {
                mCallback.getMovieInfoHome(movieId, data);
            }

            @Override
            public void getMovieInfoFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getTvInfo(final int tvId) {
        mRepository.getTvInfoData(tvId, new OnTaskCompletion.OnGetTvInfoCompletion() {
            @Override
            public void getTvInfoSuccess(TMDB data) {
                mCallback.getTvInfoHome(tvId, data);
            }

            @Override
            public void getTvInfoFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getTrailer(int movieId) {

        mRepository.getTrailerListData(movieId, new OnTaskCompletion.OnGetTrailerCompletion() {
            @Override
            public void getTrailerItemSuccess(Trailer data) {
                mCallback.getTrailerDetail(data);
            }

            @Override
            public void getTrailerItemFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }


    @Override
    public void getReviews(int movieId) {
        mRepository.getReviewsListData(movieId, new OnTaskCompletion.OnGetReviewCompletion() {
            @Override
            public void getReviewResponseSuccess(Reviews data) {
                mCallback.getReviewDetail(data);
            }

            @Override
            public void getReviewResponseFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getSimilarMovie(final int movieId) {
        mRepository.getSimilarMoviesData(movieId, new OnTaskCompletion.OnGetSimilarMovieCompletion() {
            @Override
            public void getSimilarMovieSuccess(Result data) {
                mCallback.getSimilarMovieDetail(data, movieId);
            }

            @Override
            public void getSimilarMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(MovieDetailContract.View view, int id) {
        mCallback = view;
        getMovieInfo(id);
        getTvInfo(id);
        getTrailer(id);
        getReviews(id);
        getSimilarMovie(id);
    }
}
