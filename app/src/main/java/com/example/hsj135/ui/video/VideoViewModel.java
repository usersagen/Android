package com.example.hsj135.ui.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hsj135.bean.VideoBean;
import com.example.hsj135.bean.VideoBean;
import com.example.hsj135.utils.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoViewModel extends ViewModel {


    private final MutableLiveData<List<VideoBean>> videoList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<VideoBean>> getVideoList() {
        // 如果已经有数据，直接返回
        if (videoList.getValue() != null && !videoList.getValue().isEmpty()) {
            return videoList;
        }

        // 否则发起网络请求
        NetUtils.get().getVideoList().enqueue(new Callback<List<VideoBean>>() {
            @Override
            public void onResponse(Call<List<VideoBean>> call, Response<List<VideoBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    videoList.setValue(response.body());
                } else {
                    errorMessage.setValue("获取新闻失败: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<VideoBean>> call, Throwable t) {
                errorMessage.setValue("网络错误: " + t.getMessage());
            }
        });
        return videoList;
    }


    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}