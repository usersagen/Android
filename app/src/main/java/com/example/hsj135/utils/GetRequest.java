package com.example.hsj135.utils;

import com.example.hsj135.bean.NewsBean;
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

    @GET("python_list_data.json")
    Call<List<PythonBean>> getPythonList();

    @GET("video_list_data.json")
    Call<List<VideoBean>> getVideoList();

}
