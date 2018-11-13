package com.example.chirag.moviedb.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.chirag.moviedb.data.Constant.BASE_URL;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class ServiceInstance {

    private static Retrofit mRetrofit;

    public static Retrofit getServiceInstance() {
        if (mRetrofit == null) {
            mRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
