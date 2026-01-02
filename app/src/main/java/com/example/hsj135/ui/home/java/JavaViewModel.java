package com.example.hsj135.ui.home.java;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hsj135.bean.JavaBean;
import com.example.hsj135.utils.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JavaViewModel extends ViewModel {
    private final MutableLiveData<List<JavaBean>> javaList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<JavaBean>> getJavaList() {
        // 如果已经有数据，直接返回
        if (javaList.getValue() != null && !javaList.getValue().isEmpty()) {
            return javaList;
        }

        // 否则发起网络请求
        NetUtils.get().getJavaList().enqueue(new Callback<List<JavaBean>>() {
            @Override
            public void onResponse(Call<List<JavaBean>> call, Response<List<JavaBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    javaList.setValue(response.body());
                } else {
                    errorMessage.setValue("获取新闻失败: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<JavaBean>> call, Throwable t) {
                errorMessage.setValue("网络错误: " + t.getMessage());
            }
        });
        return javaList;
    }


    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}