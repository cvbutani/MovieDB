package com.example.chirag.moviedb.moviedetail;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.remote.OnTaskCompletion;
import com.example.chirag.moviedb.data.Repository;
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

    private Repository mRepository;

    private MovieDetailContract.View mCallback;

    MovieDetailPresenter(Context context, boolean isConnected) {
        LocalService mLocalService = LocalService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());
        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).loacalDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getTrailerList(int movieId) {

        mRepository.getTrailerList(movieId, new OnTaskCompletion.OnGetTrailerCompletion() {
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
        mRepository.getPopularMoviesData(new OnTaskCompletion.OnGetMovieCompletion() {
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
        mRepository.getNowPlayingMoviesData(new OnTaskCompletion.OnGetNowPlayingCompletion() {
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
        mRepository.getTopRatedMoviesData(new OnTaskCompletion.OnGetTopRatedMovieCompletion() {
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
        mRepository.getUpcomingMoviesData(new OnTaskCompletion.OnGetUpcomingMovieCompletion() {
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
        mRepository.getGenreList(new OnTaskCompletion.OnGetGenresCompletion() {
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
        mRepository.getReviews(movieId, new OnTaskCompletion.OnGetReviewCompletion() {
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
        mRepository.getSimilarMoviesData(movieId, new OnTaskCompletion.OnGetSimilarMovieCompletion() {
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
    public void getPopularTV(final int tvId) {
        mRepository.getPopularTvData(new OnTaskCompletion.OnGetPopularTvCompletion() {
            @Override
            public void onPopularTvSuccess(Movies data) {
                mCallback.onPopularTV(data, tvId);
            }

            @Override
            public void onPopularTvFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getTVGenreDetail() {
        mRepository.getTVGenreList(new OnTaskCompletion.OnGetTVGenreCompletion() {
            @Override
            public void onTVGenreSuccess(Genre data) {
                mCallback.onTVGenreDetail(data);
            }

            @Override
            public void onTVGenreFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void getTVTopRatedDetail() {
        mRepository.getTVTopRated(new OnTaskCompletion.GetTopRatedTvCompletion() {
            @Override
            public void getTvTopRatedContentSuccess(Movies data) {
                mCallback.getTvTopRatedDetail(data);
            }

            @Override
            public void getTvTopRatedContentFailure(String errorMessage) {

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
        getPopularTV(movieId);
        getTVTopRatedDetail();
    }
}
