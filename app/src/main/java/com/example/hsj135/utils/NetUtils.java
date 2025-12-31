package com.example.hsj135.utils;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {
    private static final String BASE_URL = "http://10.22.199.160:8080/topline/";
    public static GetRequest get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
//                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GetRequest.class);
    }
}
