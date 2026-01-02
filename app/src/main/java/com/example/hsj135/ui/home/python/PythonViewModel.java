package com.example.hsj135.ui.home.python;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.hsj135.bean.PythonBean;
import com.example.hsj135.utils.NetUtils;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PythonViewModel extends ViewModel {
    private final MutableLiveData<List<PythonBean>> pythonList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<PythonBean>> getPythonList() {
        // 如果已经有数据，直接返回
        if (pythonList.getValue() != null && !pythonList.getValue().isEmpty()) {
            return pythonList;
        }

        // 否则发起网络请求
        NetUtils.get().getPythonList().enqueue(new Callback<List<PythonBean>>() {
            @Override
            public void onResponse(Call<List<PythonBean>> call, Response<List<PythonBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pythonList.setValue(response.body());
                } else {
                    errorMessage.setValue("获取新闻失败: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PythonBean>> call, Throwable t) {
                errorMessage.setValue("网络错误: " + t.getMessage());
            }
        });
        return pythonList;
    }
    

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}