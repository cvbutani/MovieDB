package com.example.chirag.moviedb.moviedetail;

import android.content.Context;

import com.example.chirag.moviedb.data.local.LocalDatabase;
import com.example.chirag.moviedb.data.local.LocalService;
import com.example.chirag.moviedb.data.model.MovieInfo;
import com.example.chirag.moviedb.data.model.MovieResponse;
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
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());
        RemoteService mRemoteService = RemoteService.getInstance(new AppExecutors(),
                LocalDatabase.getInstance(context).localDao(),
                LocalDatabase.getInstance(context).trailerDao(),
                LocalDatabase.getInstance(context).reviewDao());

        mRepository = Repository.getInstance(isConnected, mLocalService, mRemoteService);
    }

    @Override
    public void getMovieInfo(final int movieId) {
        mRepository.getMovieInfoData(movieId, new OnTaskCompletion.OnGetMovieInfoCompletion() {
            @Override
            public void getMovieInfoSuccess(MovieInfo data) {
                mCallback.getMovieInfoHome(movieId, data);
            }

            @Override
            public void getMovieInfoFailure(String errorMessage) {
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
    public void getPopularMovie(final int movieId) {
        mRepository.getPopularMoviesData(new OnTaskCompletion.OnGetMovieCompletion() {
            @Override
            public void getPopularMovieSuccess(Movies data) {
                mCallback.getPopularMovieDetail(data, movieId);
            }

            @Override
            public void getPopularMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getNowPlayingMovie(final int movieId) {
        mRepository.getNowPlayingMoviesData(new OnTaskCompletion.OnGetNowPlayingCompletion() {
            @Override
            public void getNowPlayingMovieSuccess(Movies data) {
                mCallback.getNowPlayingMovieDetail(data, movieId);
            }

            @Override
            public void getNowPlayingMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getTopRatedMovie(final int movieId) {
        mRepository.getTopRatedMoviesData(new OnTaskCompletion.OnGetTopRatedMovieCompletion() {
            @Override
            public void getTopRatedMovieSuccess(Movies data) {
                mCallback.getTopRatedMovieDetail(data, movieId);
            }

            @Override
            public void getTopRatedMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getUpcomingMovie(final int movieId) {
        mRepository.getUpcomingMoviesData(new OnTaskCompletion.OnGetUpcomingMovieCompletion() {
            @Override
            public void getUpcomingMovieSuccess(Movies data) {
                mCallback.getUpcomingMovieDetail(data, movieId);
            }

            @Override
            public void getUpcomingMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getGenreMovie() {
        mRepository.getMovieGenreListData(new OnTaskCompletion.OnGetGenresCompletion() {
            @Override
            public void getMovieGenreItemSuccess(Genre data) {
                mCallback.getGenreMovieDetail(data);
            }

            @Override
            public void getMovieGenreItemFailure(String errorMessage) {
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
            public void getSimilarMovieSuccess(Movies data) {
                mCallback.getSimilarMovieDetail(data, movieId);
            }

            @Override
            public void getSimilarMovieFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getPopularTV(final int tvId) {
        mRepository.getPopularTvData(new OnTaskCompletion.OnGetPopularTvCompletion() {
            @Override
            public void getPopularTvSuccess(Movies data) {
                mCallback.getPopularTvDetail(data, tvId);
            }

            @Override
            public void getPopularTvFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getGenreTv() {
        mRepository.getTVGenreListData(new OnTaskCompletion.OnGetTVGenreCompletion() {
            @Override
            public void getTVGenreSuccess(Genre data) {
                mCallback.getGenreTvDetail(data);
            }

            @Override
            public void getTVGenreFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getTopRatedTv() {
        mRepository.getTopRatedTvData(new OnTaskCompletion.GetTopRatedTvCompletion() {
            @Override
            public void getTvTopRatedContentSuccess(Movies data) {
                mCallback.getTopRatedTvDetail(data);
            }

            @Override
            public void getTvTopRatedContentFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void getSeasonTv(final int tvId) {
        mRepository.getSeasonTvListData(tvId, new OnTaskCompletion.GetTvSeasonCompletion() {
            @Override
            public void getTvSeasonContentSuccess(MovieResponse data) {
                mCallback.getSeasonTvDetail(data, tvId);
            }

            @Override
            public void getTvSeasonContentFailure(String errorMessage) {
                mCallback.getResultFailure(errorMessage);
            }
        });
    }

    @Override
    public void attachView(MovieDetailContract.View view, int id) {
        mCallback = view;
        getMovieInfo(id);
        getTrailer(id);
        getPopularMovie(id);
        getReviews(id);
        getNowPlayingMovie(id);
        getTopRatedMovie(id);
        getUpcomingMovie(id);
        getSimilarMovie(id);
        getGenreMovie();
        getPopularTV(id);
        getTopRatedTv();
        getSeasonTv(id);
        getGenreTv();
    }
}
