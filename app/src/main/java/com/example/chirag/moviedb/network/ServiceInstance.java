package com.example.chirag.moviedb.network;

import com.example.chirag.moviedb.data.model.Result;
import com.example.chirag.moviedb.data.model.ResultResponse;
import com.example.chirag.moviedb.data.remote.ResultDeserializer;
import com.example.chirag.moviedb.data.remote.ResultResponseDeserializer;
import com.example.chirag.moviedb.service.GetDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.chirag.moviedb.data.Constant.BASE_URL;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class ServiceInstance {

    private static GetDataService mDataService;

    public static synchronized GetDataService getServiceInstance() {

        if (mDataService == null) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Result.class, new ResultDeserializer())
                    .registerTypeAdapter(ResultResponse.class, new ResultResponseDeserializer())
                    .create();

            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            mDataService = retrofit.create(GetDataService.class);
        }
        return mDataService;
    }
}
