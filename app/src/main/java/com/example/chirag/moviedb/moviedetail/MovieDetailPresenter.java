package com.example.chirag.moviedb.moviedetail;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.RemoteRepository;
import com.example.chirag.moviedb.data.remote.RemoteService;
import com.example.chirag.moviedb.data.model.Genre;
import com.example.chirag.moviedb.data.model.Movies;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.util.AppExecutors;

/**
 * MovieDB
 * Created by Chirag on 15/09/18.
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private RemoteRepository mRemoteRepository;

    private MovieDetailContract.View mCallback;

    MovieDetailPresenter(Context context) {
        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());
        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        mRemoteRepository = RemoteRepository.getInstance(mLocalService, mRemoteService);
    }

    @Override
    public void getTrailerList(int movieId) {

        mRemoteRepository.getTrailerList(movieId, new OnTaskCompletion.OnGetTrailerCompletion() {
            @Override
            public void onTrailerItemSuccess(Trailer data) {
                mCallback.onTrailerListSuccess(data);
            }

            @Override
            public void onTrailerItemFailure(String errorMessage) {
                mCallback.onTrailerListFailure(errorMessage);
            }
        });
    }

    @Override
    public void getMovieData(final int movieId) {
        mRemoteRepository.getPopularMoviesData(new OnTaskCompletion.OnGetMovieCompletion() {
            @Override
            public void onHeaderItemSuccess(Movies data) {
                mCallback.onMovieDetail(data, movieId);
            }

            @Override
            public void onHeaderItemFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getNowPlayingData(final int movieId) {
        mRemoteRepository.getNowPlayingMoviesData(new OnTaskCompletion.OnGetNowPlayingCompletion() {
            @Override
            public void onNowPlayingMovieSuccess(Movies data) {
                mCallback.onNowPlayingMovie(data, movieId);
            }

            @Override
            public void onNowPlayingMovieFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getTopRatedData(final int movieId) {
        mRemoteRepository.getTopRatedMoviesData(new OnTaskCompletion.OnGetTopRatedMovieCompletion() {
            @Override
            public void onTopRatedMovieSuccess(Movies data) {
                mCallback.onTopRatedMovie(data, movieId);
            }

            @Override
            public void onTopRatedMovieFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getUpcomingData(final int movieId) {
        mRemoteRepository.getUpcomingMoviesData(new OnTaskCompletion.OnGetUpcomingMovieCompletion() {
            @Override
            public void onUpcomingMovieSuccess(Movies data) {
                mCallback.onUpcomingMovie(data, movieId);
            }

            @Override
            public void onUpcomingMovieFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getGenreItem() {
        mRemoteRepository.getGenreList(new OnTaskCompletion.OnGetGenresCompletion() {
            @Override
            public void onGenreListSuccess(Genre data) {
                mCallback.onGenreDetail(data);
            }

            @Override
            public void onGenreListFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getReviews(int movieId) {
        mRemoteRepository.getReviews(movieId, new OnTaskCompletion.OnGetReviewCompletion() {
            @Override
            public void onReviewResponseSuccess(Reviews data) {
                mCallback.onReviewDetail(data);
            }

            @Override
            public void onReviewResponseFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getSimilarData(final int movieId) {
        mRemoteRepository.getSimilarMoviesData(movieId, new OnTaskCompletion.OnGetSimilarMovieCompletion() {
            @Override
            public void onSimilarMovieSuccess(Movies data) {
                mCallback.onSimilarMovieSuccess(data, movieId);
            }

            @Override
            public void onSimilarMovieFailure(String errorMessage) {
                mCallback.onSimilarMovieFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(MovieDetailContract.View view, int movieId) {
        mCallback = view;
        getTrailerList(movieId);
        getMovieData(movieId);
        getReviews(movieId);
        getNowPlayingData(movieId);
        getTopRatedData(movieId);
        getUpcomingData(movieId);
        getSimilarData(movieId);
        getGenreItem();
    }
}
