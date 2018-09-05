package com.example.chirag.moviedb.data;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteRepository implements DataContract {

    private static RemoteRepository sRemoteRepository;
    private static RemoteService mRemoteService;

    @Override
    public void getNewBatchOfData(OnTaskCompletion callback) {

    }
}
