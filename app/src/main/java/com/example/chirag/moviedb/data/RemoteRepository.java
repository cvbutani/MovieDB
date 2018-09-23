package com.example.chirag.moviedb.data;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteRepository implements DataContract {

    private static RemoteRepository sRemoteRepository;
    private static RemoteService mRemoteService;

    private RemoteRepository() {
    }

    public static RemoteRepository getInstance() {
        if (sRemoteRepository == null) {
            sRemoteRepository = new RemoteRepository();
            mRemoteService = new RemoteService();
        }
        return sRemoteRepository;
    }

    @Override
    public void getPopularMoviesData(OnTaskCompletion.OnGetMovieCompletion callback) {
        mRemoteService.getPopularMovies(callback);
    }

    @Override
    public void getNowPlayingMoviesData(OnTaskCompletion.OnGetNowPlayingCompletion callback) {
        mRemoteService.getNowPlayingMovies(callback);
    }

    @Override
    public void getTopRatedMoviesData(OnTaskCompletion.OnGetTopRatedMovieCompletion callback) {
        mRemoteService.getTopRatedMovies(callback);
    }

    @Override
    public void getUpcomingMoviesData(OnTaskCompletion.OnGetUpcomingMovieCompletion callback) {
        mRemoteService.getUpcomingMovies(callback);
    }

    @Override
    public void getSimilarMoviesData(int movieId, OnTaskCompletion.OnGetSimilarMovieCompletion callback) {
        mRemoteService.getSimilarMovies(movieId, callback);
    }

    @Override
    public void getGenreList(OnTaskCompletion.OnGetGenresCompletion callback) {
        mRemoteService.getGenres(callback);
    }

    @Override
    public void getTrailerList(int movieId, OnTaskCompletion.OnGetTrailerCompletion callback) {
        mRemoteService.getTrailers(movieId, callback);
    }

    @Override
    public void getReviews(int movieId, OnTaskCompletion.OnGetReviewCompletion callback) {
        mRemoteService.getReviews(movieId, callback);
    }

    @Override
    public void getPopularTvData(OnTaskCompletion.OnGetPopularTvCompletion callback) {
        mRemoteService.getPopularTv(callback);
    }


}
