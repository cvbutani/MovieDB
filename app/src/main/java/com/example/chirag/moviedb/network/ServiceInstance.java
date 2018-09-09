package com.example.chirag.moviedb.network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class ServiceInstance {

    private static Retrofit mRetrofit;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

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
