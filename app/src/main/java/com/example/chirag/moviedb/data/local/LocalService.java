package com.example.chirag.moviedb.data.local;

import android.support.annotation.NonNull;

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
    public void getMovieInfoRepo(final int movieId, final OnTaskCompletion.OnGetMovieInfoCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final TMDB movieInfo = mTMDBDao.getMovieInfo(movieId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movieInfo != null) {
                            callback.getMovieInfoSuccess(movieInfo);
                        } else {
                            callback.getMovieInfoFailure("LOCAL DATA FAILURE");
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTvInfoRepo(final int tvId, final OnTaskCompletion.OnGetTvInfoCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final TMDB movieInfo = mTMDBDao.getMovieInfo(tvId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movieInfo != null) {
                            callback.getTvInfoSuccess(movieInfo);
                        } else {
                            callback.getTvInfoFailure("LOCAL DATA FAILURE");
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getPopularMoviesRepo(final OnTaskCompletion.OnGetMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mTMDBDao.getMovieId(CONTENT_TYPE_POPULAR, CONTENT_MOVIE);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getPopularMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getPopularMovieSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getNowPlayingMoviesRepo(final OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mTMDBDao.getMovieId(CONTENT_TYPE_NOW_PLAYING, CONTENT_MOVIE);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getNowPlayingMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getNowPlayingMovieSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTopRatedMoviesRepo(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mTMDBDao.getMovieId(CONTENT_TYPE_TOP_RATED, CONTENT_MOVIE);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getTopRatedMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getTopRatedMovieSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getUpcomingMoviesRepo(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mTMDBDao.getMovieId(CONTENT_TYPE_UPCOMING, CONTENT_MOVIE);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getUpcomingMovieFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getUpcomingMovieSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTrailersRepo(final int movieId, final OnTaskCompletion.OnGetTrailerCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TrailerResponse> trailers = mTMDBDao.getTrailers(movieId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (trailers.isEmpty()) {
                            callback.getTrailerItemFailure("LOCAL DATA FAILURE");
                        } else {
                            Trailer trailer = new Trailer();
                            trailer.setResults(trailers);
                            callback.getTrailerItemSuccess(trailer);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getReviewsRepo(final int movieId, final OnTaskCompletion.OnGetReviewCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ReviewResponse> reviews = mTMDBDao.getReviews(movieId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (reviews.isEmpty()) {
                            callback.getReviewResponseFailure("LOCAL DATA FAILURE");
                        } else {
                            Reviews reviewList = new Reviews();
                            reviewList.setResults(reviews);
                            callback.getReviewResponseSuccess(reviewList);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getSimilarMoviesRepo(final int movieId, final OnTaskCompletion.OnGetSimilarMovieCompletion callback) {

    }

    @Override
    public void getPopularTvRepo(final OnTaskCompletion.OnGetPopularTvCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mTMDBDao.getMovieId(CONTENT_TYPE_POPULAR, CONTENT_TV);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getPopularTvFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getPopularTvSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTopRatedTvRepo(final OnTaskCompletion.GetTopRatedTvCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ResultResponse> movies = mTMDBDao.getMovieId(CONTENT_TYPE_TOP_RATED, CONTENT_TV);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.isEmpty()) {
                            callback.getTvTopRatedContentFailure("LOCAL DATA FAILURE");
                        } else {
                            Result item = new Result();
                            item.setResults(movies);
                            callback.getTvTopRatedContentSuccess(item);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getUserSignInInfo(final OnTaskCompletion.GetUserSignInCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<User> user = mUserDao.getSignInDetail();
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (user.isEmpty()){
                            callback.getUserInfoFailure("No user information available in local database");
                        } else {
                            callback.getUserInfoSuccess(user);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void insertUserSignInInfo(final User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mUserDao.insertUserRegisterInfo(user);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getUserAccountInfo(final String emailId, final OnTaskCompletion.GetUserAccountCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final User user = mUserDao.getUserInfo(emailId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (user != null){
                            callback.getUserAccountSuccess(user);
                        } else {
                            callback.getUserAccountFailure("User Information is not available");
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void updateUserAccount(final User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mUserDao.updateUserAccountInfo(user);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void updateTMDBRepo(final Favourite info) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mTMDBDao.updateTMDBInfo(info);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getFavouriteTMDBRepo(final String emailId, final OnTaskCompletion.GetFavouriteTMDBCompletion callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Favourite> favouriteInfo = mTMDBDao.getFavouriteInfo(emailId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (favouriteInfo == null) {
                            callback.getFavouriteTMDBFailure("No Data Found");
                        } else {
                            callback.getFavouriteTMDBSuccess(favouriteInfo);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

}
