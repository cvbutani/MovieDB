package com.example.chirag.moviedb.data;

import com.example.chirag.moviedb.GetDataService;
import com.example.chirag.moviedb.model.HeaderItem;
import com.example.chirag.moviedb.network.ServiceInstance;

import java.util.List;

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

        Call<List<HeaderItem>> call = service.getAllMovieInfo();

        call.enqueue(new Callback<List<HeaderItem>>() {
            @Override
            public void onResponse(Call<List<HeaderItem>> call, Response<List<HeaderItem>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<HeaderItem>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
