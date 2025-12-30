package com.example.hsj135.ui.video;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hsj135.R;
import com.example.hsj135.bean.NewsBean;
import com.example.hsj135.bean.VideoBean;
import com.example.hsj135.databinding.FragmentVideoBinding;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;
    private VideoViewModel videoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         videoViewModel =
                new ViewModelProvider(this).get(VideoViewModel.class);

        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RefreshLayout refreshLayout = (RefreshLayout) root.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            getVideoList();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            Toast.makeText(getContext(), "没有更多视频了哦~", Toast.LENGTH_LONG).show();
        });
        getVideoList();
        return root;
    }

    private void getVideoList() {
        videoViewModel.getVideoList().observe(getViewLifecycleOwner(), videoBeans -> {
            for(VideoBean videoBean : videoBeans) {
                Log.i("Video", videoBean.getName());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}