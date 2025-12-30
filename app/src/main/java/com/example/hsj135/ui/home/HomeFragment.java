package com.example.hsj135.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.hsj135.R;
import com.example.hsj135.bean.NewsBean;
import com.example.hsj135.databinding.FragmentHomeBinding;
import com.scwang.smart.refresh.layout.api.RefreshLayout;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RefreshLayout refreshLayout = (RefreshLayout) root.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getNewsList();
                getAdList();



        });

        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            Toast.makeText(getContext(), "没有更多新闻了哦~", Toast.LENGTH_LONG).show();
        });
        getNewsList();
        getAdList();
        return root;
    }

    private void getNewsList() {
        homeViewModel.getNewsList().observe(getViewLifecycleOwner(), newsBeans -> {
            for(NewsBean newsBean : newsBeans) {
                Log.i("News", newsBean.getNewsName());
            }
        });
    }

    private void getAdList() {
        homeViewModel.getAdList().observe(getViewLifecycleOwner(), newsBeans -> {
            for(NewsBean newsBean : newsBeans) {
                Log.i("Ad", newsBean.getNewsName());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}