package com.example.hsj135.ui.home.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hsj135.bean.AndroidBean;
import com.example.hsj135.utils.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AndroidViewModel extends ViewModel {
    private final MutableLiveData<List<AndroidBean>> androidList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<AndroidBean>> getAndroidList() {
        // 如果已经有数据，直接返回
        if (androidList.getValue() != null && !androidList.getValue().isEmpty()) {
            return androidList;
        }

        // 否则发起网络请求
        NetUtils.get().getAndroidList().enqueue(new Callback<List<AndroidBean>>() {
            @Override
            public void onResponse(Call<List<AndroidBean>> call, Response<List<AndroidBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    androidList.setValue(response.body());
                } else {
                    errorMessage.setValue("获取新闻失败: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<AndroidBean>> call, Throwable t) {
                errorMessage.setValue("网络错误: " + t.getMessage());
            }
        });
        return androidList;
    }


    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}