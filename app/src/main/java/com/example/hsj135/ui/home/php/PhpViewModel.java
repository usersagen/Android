package com.example.hsj135.ui.home.php;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hsj135.bean.PhpBean;
import com.example.hsj135.utils.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhpViewModel extends ViewModel {
    private final MutableLiveData<List<PhpBean>> phpList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<PhpBean>> getPhpList() {
        // 如果已经有数据，直接返回
        if (phpList.getValue() != null && !phpList.getValue().isEmpty()) {
            return phpList;
        }

        // 否则发起网络请求
        NetUtils.get().getPhpList().enqueue(new Callback<List<PhpBean>>() {
            @Override
            public void onResponse(Call<List<PhpBean>> call, Response<List<PhpBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    phpList.setValue(response.body());
                } else {
                    errorMessage.setValue("获取新闻失败: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PhpBean>> call, Throwable t) {
                errorMessage.setValue("网络错误: " + t.getMessage());
            }
        });
        return phpList;
    }


    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}