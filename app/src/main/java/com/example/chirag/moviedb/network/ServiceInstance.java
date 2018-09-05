package com.example.chirag.moviedb.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MovieDB
 * Created by Chirag on 04/09/18.
 */
public class ServiceInstance {

    private static Retrofit mRetrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=51b4547daeeca9a0a1dec36a7013b1ad&language=en-US&page=1";

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
