package com.example.hsj135.ui.home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hsj135.bean.NewsBean;
import com.example.hsj135.utils.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<NewsBean>> newsList = new MutableLiveData<>();
    private final MutableLiveData<List<NewsBean>> adList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<NewsBean>> getNewsList() {
        // 如果已经有数据，直接返回
        if (newsList.getValue() != null && !newsList.getValue().isEmpty()) {
            return newsList;
        }

        // 否则发起网络请求
        NetUtils.get().getNewsList().enqueue(new Callback<List<NewsBean>>() {
            @Override
            public void onResponse(Call<List<NewsBean>> call, Response<List<NewsBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsList.setValue(response.body());
                } else {
                    errorMessage.setValue("获取新闻失败: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<NewsBean>> call, Throwable t) {
                errorMessage.setValue("网络错误: " + t.getMessage());
            }
        });
        return newsList;
    }

    public LiveData<List<NewsBean>> getAdList() {
        // 如果已经有数据，直接返回
        if (adList.getValue() != null && !adList.getValue().isEmpty()) {
            return adList;
        }

        NetUtils.get().getAdList().enqueue(new Callback<List<NewsBean>>() {
            @Override
            public void onResponse(Call<List<NewsBean>> call, Response<List<NewsBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adList.setValue(response.body());
                } else {
                    errorMessage.setValue("获取广告失败: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<NewsBean>> call, Throwable t) {
                errorMessage.setValue("网络错误: " + t.getMessage());
            }
        });
        return adList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}