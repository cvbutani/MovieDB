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
    public void getGenreList(OnTaskCompletion.OnGetGenresCompletion callback) {
        mRemoteService.getGenres(callback);
    }

    @Override
    public void getTrailerList(int movieId, OnTaskCompletion.OnGetTrailerCompletion callback) {
        mRemoteService.getTrailers(movieId, callback);
    }


}
