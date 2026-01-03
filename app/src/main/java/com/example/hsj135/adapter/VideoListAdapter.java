package com.example.hsj135.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.hsj135.R;
import com.example.hsj135.bean.VideoBean;
import com.example.hsj135.utils.NetUtils;

import java.util.List;

public class VideoListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public VideoListAdapter(@NonNull List<String> data) {
            super(R.layout.item_video_list, data);
        }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.textView,s);
    }
}
