package com.example.hsj135.utils;

import com.example.hsj135.bean.AndroidBean;
import com.example.hsj135.bean.JavaBean;
import com.example.hsj135.bean.NewsBean;
import com.example.hsj135.bean.PhpBean;
import com.example.hsj135.bean.PythonBean;
import com.example.hsj135.bean.VideoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest {
    @GET("home_ad_list_data.json")
    Call<List<NewsBean>> getAdList();

    @GET("home_news_list_data.json")
    Call<List<NewsBean>> getNewsList();

    @GET("android_list_data.json")
    Call<List<AndroidBean>> getAndroidList();
    @GET("java_list_data.json")
    Call<List<JavaBean>> getJavaList();
    @GET("python_list_data.json")
    Call<List<PythonBean>> getPythonList();
    @GET("php_list_data.json")
    Call<List<PhpBean>> getPhpList();

    @GET("video_list_data.json")
    Call<List<VideoBean>> getVideoList();

}
