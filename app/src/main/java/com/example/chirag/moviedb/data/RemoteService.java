package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.service.GetDataService;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.network.ServiceInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class RemoteService {

    RemoteService() {
    }

    void getData(final OnTaskCompletion callback) {
        GetDataService service = ServiceInstance.getServiceInstance().create(GetDataService.class);

        Call<HeaderItem> call = service.getAllMovieInfo();

        call.enqueue(new Callback<HeaderItem>() {
            @Override
            public void onResponse(Call<HeaderItem> call, Response<HeaderItem> response) {
                callback.onHeaderItemSuccess(response.body());
            }

            @Override
            public void onFailure(Call<HeaderItem> call, Throwable t) {
                callback.onHeaderItemFailure(t.getMessage());
            }
        });
    }
}
