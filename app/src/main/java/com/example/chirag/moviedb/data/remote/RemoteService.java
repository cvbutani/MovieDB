package com.example.chirag.moviedb.data.remote;

import androidx.annotation.NonNull;

import com.example.chirag.moviedb.data.RepositoryContract;
import com.example.chirag.moviedb.data.local.dao.TMDBDao;
import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.model.User;
import com.example.chirag.moviedb.network.ServiceInstance;
import com.example.chirag.moviedb.service.GetDataService;

import java.util.List;

import io.reactivex.Flowable;

import static com.example.chirag.moviedb.data.Constant.LANGUAGE;
import static com.example.chirag.moviedb.data.Constant.TMDB_API_KEY;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteService implements RepositoryContract {

    private GetDataService mServiceApi;

    private static volatile RemoteService INSTANCE;

    private TMDBDao mTMDBDao;

    private RemoteService(@NonNull TMDBDao TMDBDao) {
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

//    @Override
//    public void getMovieInfoRepo(int movieId,
//                                 final OnTaskCompletion.OnGetMovieInfoCompletion callback) {
//        mServiceApi
//                .getMovieTvInfoDataService(movieId,  TMDB_API_KEY, LANGUAGE)
//                .flatMap(data ->
//                        Flowable.zip(
//                                Flowable.just(data),
//                                mServiceApi.getTrailerDataService(movieId,
//                                        TMDB_API_KEY),
//                                mServiceApi.getReviewDataService(movieId,
//                                        TMDB_API_KEY, LANGUAGE),
//                                this::mergeInfo
//                        )
//                )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSubscriber<ResultResponse>() {
//                    @Override
//                    public void onNext(ResultResponse resultResponse) {
//                        callback.getMovieInfoSuccess(resultResponse);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        callback.getMovieInfoFailure(t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    public Flowable<ResultResponse> getMovieDetailData(int movieId, String movieType,
                                                       String contentType) {
        return new NetworkBoundResource<ResultResponse, ResultResponse>() {

            @Override
            void saveCallResult(@NonNull ResultResponse item) {
                mTMDBDao.deleteMovieInfo(movieId, movieType, contentType);
                item.mType = movieType;
                item.mContent = contentType;
                mTMDBDao.insertMovieInfo(item);

            }

            @Override
            protected Flowable<ResultResponse> loadFromDb() {
                return mTMDBDao.getMovieInfo(movieId, movieType, contentType);
            }

            @Override
            protected Flowable<ResultResponse> createCall() {
                return mServiceApi
                        .getMovieTvInfoDataService(movieId, contentType, TMDB_API_KEY, LANGUAGE)
                        .flatMap(data ->
                                Flowable.zip(
                                        Flowable.just(data),
                                        mServiceApi
                                                .getTrailerDataService(
                                                        movieId,
                                                        TMDB_API_KEY),
                                        mServiceApi
                                                .getReviewDataService(
                                                        movieId,
                                                        TMDB_API_KEY,
                                                        LANGUAGE),
                                        (t1, t2, t3) ->
                                                mergeInfo(t1, t2, t3)
                                )
                        );
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }
        }.asFlowable();
    }


    public Flowable<List<ResultResponse>> getMovieHomeScreenData(String movieType,
                                                                 String contentType) {
        return new NetworkBoundResource<List<ResultResponse>, Result>() {
            @Override
            void saveCallResult(@NonNull Result item) {

                for (final ResultResponse info : item.getResults()) {
                    mTMDBDao.deleteMovieInfo(info.getId(), movieType, contentType);
                    info.mType = movieType;
                    info.mContent = contentType;
                    mTMDBDao.insertMovieInfo(info);
                }
            }

            @Override
            protected Flowable<List<ResultResponse>> loadFromDb() {
                return mTMDBDao
                        .getMovieId(movieType, contentType)
                        .toFlowable();
            }

            @Override
            protected Flowable<Result> createCall() {
                return mServiceApi
                        .getContentDataService(
                                contentType,
                                movieType,
                                TMDB_API_KEY,
                                LANGUAGE);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }
        }.asFlowable();
    }

    private ResultResponse mergeInfo(ResultResponse t1, ResultResponse t2, ResultResponse t3) {

        t1.mKey = t2.getTrailerKey();
        if (t3 != null) {
            t1.mReviewAuthor = t3.getReviewAuthor();
            t1.mReviewText = t3.getReviewText();
        }
        return t1;
    }

//    @Override
//    public void getSimilarMoviesRepo(int movieId,
//                                     final OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
//        mServiceApi.getSimilarMovieDataService(movieId, TMDB_API_KEY, LANGUAGE).enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                if (response.isSuccessful()) {
//                    Result item = response.body();
//                    if (item != null && item.getResults() != null) {
//                        callback.getSimilarMovieSuccess(item);
//                    } else {
//                        callback.getSimilarMovieFailure("FAILURE");
//                    }
//                } else {
//                    callback.getSimilarMovieFailure("FAILURE");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                callback.getSimilarMovieFailure(t.getMessage());
//            }
//        });
//    }

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
}
