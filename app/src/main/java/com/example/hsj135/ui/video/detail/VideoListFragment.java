package com.example.hsj135.ui.video.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hsj135.R;
import com.example.hsj135.adapter.VideoListAdapter;

import java.util.Arrays;
import java.util.List;


public class VideoListFragment extends Fragment {


    private final List<String> list;
    private final VideoDetailFragment videoDetailFragment;
//    private String url0= "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8";
    private String url0= "https://hichee.679ks.com/cheeoo/cache/lz/0a3fdba7f289271c4fcabe20ca203e56.m3u8";
    private String url1= "https://hichee.679ks.com/cheeoo/cache/lz/d4947400e61cd5e4ae39c8fefd201303.m3u8";

    public VideoListFragment(String[] list, VideoDetailFragment videoDetailFragment) {
        this.list = Arrays.asList(list);
        this.videoDetailFragment=videoDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_video_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(),DividerItemDecoration.VERTICAL));

        VideoListAdapter videoListAdapter = new VideoListAdapter(list);
        recyclerView.setAdapter(videoListAdapter);
        videoListAdapter.setOnItemClickListener((adapter, view, position)-> {
            if(position%2==0){
                videoDetailFragment.playNewVideo(url0);
            }else{
                videoDetailFragment.playNewVideo(url1);
            }
        });
        return root;
    }
}