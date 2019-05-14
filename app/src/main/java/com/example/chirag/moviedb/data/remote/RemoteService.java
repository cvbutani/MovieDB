package com.example.chirag.moviedb.data.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.TMDBDao;
import com.example.chirag.moviedb.data.model.Favourite;
import com.example.chirag.moviedb.data.model.TMDB;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.ReviewResponse;
import com.example.chirag.moviedb.data.model.Reviews;
import com.example.chirag.moviedb.data.model.Trailer;
import com.example.chirag.moviedb.data.model.TrailerResponse;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.network.ServiceInstance;
import com.example.chirag.moviedb.service.GetDataService;
import com.example.chirag.moviedb.util.AppExecutors;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.chirag.moviedb.data.Constant.CONTENT_MOVIE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TV;
import static com.example.chirag.moviedb.data.Constant.LANGUAGE;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_NOW_PLAYING;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_POPULAR;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_TOP_RATED;
import static com.example.chirag.moviedb.data.Constant.CONTENT_TYPE_UPCOMING;
import static com.example.chirag.moviedb.data.Constant.TMDB_API_KEY;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteService implements RepositoryContract {

    private GetDataService mServiceApi;

    private static volatile RemoteService INSTANCE;

    private TMDBDao mTMDBDao;

    RemoteService(@NonNull TMDBDao TMDBDao) {
        mTMDBDao = TMDBDao;
        mServiceApi = ServiceInstance.getServiceInstance();
    }

    public static RemoteService getInstance(@NonNull TMDBDao TMDBDao) {
        if (INSTANCE == null) {
            synchronized (RemoteService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteService(TMDBDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getMovieInfoRepo(int movieId,
                                 final OnTaskCompletion.OnGetMovieInfoCompletion callback) {
        mServiceApi
                .getMovieInfoDataService(movieId, TMDB_API_KEY, LANGUAGE)
                .flatMap(data ->
                        Flowable.zip(
                                Flowable.just(data),
                                mServiceApi.getTrailerDataService(movieId,
                                        TMDB_API_KEY),
                                mServiceApi.getReviewDataService(movieId,
                                        TMDB_API_KEY, LANGUAGE),
                                this::mergeInfo
                        )
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<ResultResponse>() {
                    @Override
                    public void onNext(ResultResponse resultResponse) {
                        callback.getMovieInfoSuccess(resultResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.getMovieInfoFailure(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getTvInfoRepo(int tvId, final OnTaskCompletion.OnGetTvInfoCompletion callback) {
        mServiceApi.getTvInfoDataService(tvId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<TMDB>() {
            @Override
            public void onResponse(Call<TMDB> call, Response<TMDB> response) {
                if (response.isSuccessful()) {
                    TMDB tvInfo = response.body();
                    if (tvInfo != null) {
                        callback.getTvInfoSuccess(tvInfo);
                        insertInfo(tvInfo);
                    } else {
                        callback.getTvInfoFailure("FAILURE");
                    }
                } else {
                    callback.getTvInfoFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<TMDB> call, Throwable t) {
                callback.getTvInfoFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getPopularMoviesRepo(final OnTaskCompletion.OnGetMovieCompletion callback) {
//        mServiceApi.getContentDataService(CONTENT_MOVIE, CONTENT_TYPE_POPULAR, TMDB_API_KEY,
//                LANGUAGE).map(Result::getResults)
//                .flatMapIterable(data -> data)
//                .take(10)
//                .flatMap(item ->
//                        mServiceApi.getMovieInfoDataService(item.mId, TMDB_API_KEY, LANGUAGE))
//                .filter(data -> data != null)
//                .flatMap(data ->
//                        Flowable.zip(
//                                Flowable.just(data),
//                                mServiceApi.getTrailerDataService(data.mId,
//                                        TMDB_API_KEY),
//                                mServiceApi.getReviewDataService(data.mId,
//                                        TMDB_API_KEY, LANGUAGE),
//                                (t1, t2, t3) -> mergeInfo(t1, t2, t3)
//                        )
//                )
//                .toList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<List<ResultResponse>>() {
//                    @Override
//                    public void onSuccess(List<ResultResponse> resultResponses) {
//                        callback.getPopularMovieSuccess(resultResponses);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.getPopularMovieFailure(e.getMessage());
//                    }
//                });
        mServiceApi
                .getContentDataService(CONTENT_MOVIE, CONTENT_TYPE_POPULAR, TMDB_API_KEY, LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<Result>() {
                    @Override
                    public void onNext(Result result) {
                        callback.getPopularMovieSuccess(result.getResults());
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.getPopularMovieFailure(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private ResultResponse mergeInfo(ResultResponse t1, ResultResponse t2, ResultResponse t3) {

        t1.mKey = t2.getTrailerKey();
        if (t3 != null) {
            t1.mReviewAuthor = t3.getReviewAuthor();
            t1.mReviewText = t3.getReviewText();
        }
        return t1;
    }


    @Override
    public void getNowPlayingMoviesRepo(final OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        mServiceApi
                .getContentDataService(CONTENT_MOVIE, CONTENT_TYPE_NOW_PLAYING, TMDB_API_KEY,
                        LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<Result>() {
                    @Override
                    public void onNext(Result result) {
                        callback.getNowPlayingMovieSuccess(result.getResults());
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.getNowPlayingMovieFailure(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getTopRatedMoviesRepo(final OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        mServiceApi
                .getContentDataService(CONTENT_MOVIE, CONTENT_TYPE_TOP_RATED, TMDB_API_KEY,
                        LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<Result>() {
                    @Override
                    public void onNext(Result result) {
                        callback.getTopRatedMovieSuccess(result.getResults());
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.getTopRatedMovieFailure(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getUpcomingMoviesRepo(final OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        mServiceApi
                .getContentDataService(CONTENT_MOVIE, CONTENT_TYPE_UPCOMING, TMDB_API_KEY, LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<Result>() {
                    @Override
                    public void onNext(Result result) {
                        callback.getUpcomingMovieSuccess(result.getResults());
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.getUpcomingMovieFailure(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getTrailersRepo(final int movieId,
                                final OnTaskCompletion.OnGetTrailerCompletion callback) {
//        mServiceApi.GetTrailerDataService(movieId, TMDB_API_KEY).enqueue(new Callback<Trailer>() {
//            @Override
//            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
//                if (response.isSuccessful()) {
//                    final Trailer trailerItem = response.body();
//                    if (trailerItem != null && trailerItem.getResults() != null) {
//                        callback.getTrailerItemSuccess(trailerItem);
//                        Runnable trailerRunnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                List<TrailerResponse> trailerList = trailerItem.getResults();
//                                List<TrailerResponse> trailerData = mTMDBDao.getTrailers(movieId);
//                                if (trailerData.isEmpty()) {
//                                    for (TrailerResponse list : trailerList) {
//                                        list.setMovieId(movieId);
//                                        mTMDBDao.insertTrailer(list);
//                                    }
//                                }
//                            }
//                        };
//                        mAppExecutors.getDiskIO().execute(trailerRunnable);
//                    } else {
//                        callback.getTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING " +
//                                "TRAILER");
//                    }
//                } else {
//                    callback.getTrailerItemFailure("SOMETHING WENT WRONG WHILE GETTING TRAILER");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Trailer> call, Throwable t) {
//                callback.getTrailerItemFailure(t.getMessage());
//            }
//        });

    }

    @Override
    public void getReviewsRepo(final int movieId,
                               final OnTaskCompletion.OnGetReviewCompletion callback) {
//        mServiceApi.getReviewDataService(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new
//        Callback<Reviews>() {
//            @Override
//            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
//                if (response.isSuccessful()) {
//                    final Reviews reviews = response.body();
//                    if (reviews != null && reviews.getResults() != null) {
//                        callback.getReviewResponseSuccess(reviews);
//                        Runnable reviewRunnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                List<ReviewResponse> reviewList = reviews.getResults();
//                                List<ReviewResponse> reviewData = mTMDBDao.getReviews(movieId);
//                                if (reviewData.isEmpty()) {
//                                    for (ReviewResponse list : reviewList) {
//                                        list.setMovieId(movieId);
//                                        mTMDBDao.insertReviews(list);
//                                    }
//                                }
//                            }
//                        };
//                        mAppExecutors.getDiskIO().execute(reviewRunnable);
//                    } else {
//                        callback.getReviewResponseFailure("SOMETHING WENT WRONG WHILE GETTING " +
//                                "TRAILER");
//                    }
//                } else {
//                    callback.getReviewResponseFailure("SOMETHING WENT WRONG WHILE GETTING
//                    TRAILER");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Reviews> call, Throwable t) {
//                callback.getReviewResponseFailure(t.getMessage());
//            }
//        });
    }

    @Override
    public void getSimilarMoviesRepo(int movieId,
                                     final OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mServiceApi.getSimilarMovieDataService(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result item = response.body();
                    if (item != null && item.getResults() != null) {
                        callback.getSimilarMovieSuccess(item);
                    } else {
                        callback.getSimilarMovieFailure("FAILURE");
                    }
                } else {
                    callback.getSimilarMovieFailure("FAILURE");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.getSimilarMovieFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getPopularTvRepo(final OnTaskCompletion.OnGetPopularTvCompletion callback) {
        //  NEED TO VERIFY RESULT RESPONSE AND API CALL FOR TV CONTENT.
//        mServiceApi.getContentDataService(CONTENT_TV, CONTENT_TYPE_POPULAR, TMDB_API_KEY,
//                LANGUAGE).map(Result::getResults)
//                .flatMapIterable(data -> data)
//                .flatMap(item -> mServiceApi.getMovieInfoDataService(item.mId, TMDB_API_KEY,
//                        LANGUAGE))
//                .flatMap(data -> Flowable.zip(
//                        Flowable.just(data),
//                        mServiceApi.getTrailerDataService(data.mId, TMDB_API_KEY),
//                        mServiceApi.getReviewDataService(data.mId, TMDB_API_KEY, LANGUAGE),
//                        this::addTrailerInfo))
//                .toList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<List<ResultResponse>>() {
//                    @Override
//                    public void onSuccess(List<ResultResponse> resultResponses) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
    }

    @Override
    public void getTopRatedTvRepo(final OnTaskCompletion.GetTopRatedTvCompletion callback) {
//        mServiceApi.getContentDataService(CONTENT_TV, CONTENT_TYPE_TOP_RATED, TMDB_API_KEY,
//                LANGUAGE).map(Result::getResults)
//                .flatMapIterable(data -> data)
//                .flatMap(item -> mServiceApi.getMovieInfoDataService(item.mId, TMDB_API_KEY,
//                        LANGUAGE))
//                .flatMap(data -> Flowable.zip(
//                        Flowable.just(data),
//                        mServiceApi.getTrailerDataService(data.mId, TMDB_API_KEY),
//                        mServiceApi.getReviewDataService(data.mId, TMDB_API_KEY, LANGUAGE),
//                        this::addTrailerInfo))
//                .toList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<List<ResultResponse>>() {
//                    @Override
//                    public void onSuccess(List<ResultResponse> resultResponses) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
    }

    @Override
    public void getUserSignInInfo(OnTaskCompletion.GetUserSignInCompletion callback) {
        // Used in Local Data Storage
    }

    @Override
    public void insertUserSignInInfo(User user) {
        // Used in Local Data Storage
    }

    @Override
    public void getUserAccountInfo(String emailId,
                                   OnTaskCompletion.GetUserAccountCompletion callback) {
        // Used in Local Data Storage
    }

    @Override
    public void updateUserAccount(User user) {
        // Used in Local Data Storage
    }

    @Override
    public void updateTMDBRepo(Favourite info) {
        // Used in Local Data Storage
    }

    @Override
    public void getFavouriteTMDBRepo(String emailId,
                                     OnTaskCompletion.GetFavouriteTMDBCompletion callback) {

    }

    private void insertTmdbId(final Result item, final String movieType, final String content) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (!mTMDBDao.getMovieId(movieType, content).isEmpty()) {
//                    mTMDBDao.deleteMovieId(movieType);
//                }
//                for (final ResultResponse info : item.getResults()) {
//                    info.setType(movieType);
//                    info.setContent(content);
//                    mTMDBDao.insertMovieId(info);
//                }
//            }
//        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

    private void insertInfo(final TMDB item) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (mTMDBDao.getMovieInfo(item.getId()) != null) {
//                    mTMDBDao.deleteMovieInfo(item.getId());
//                }
//                item.setGenreInfo(item.getGenresDetail());
//                mTMDBDao.insertMovieInfo(item);
//            }
//        };
//        mAppExecutors.getDiskIO().execute(runnable);
    }

}
