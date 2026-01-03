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

public class VideoAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
        public VideoAdapter(@NonNull List<VideoBean> data) {
            super(R.layout.item_video, data);
        }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, VideoBean videoBean) {
        Glide.with(baseViewHolder.getView(R.id.imageView).getContext())
                .load(NetUtils.BASE_URL + videoBean.getImg())
                .into((ImageView) baseViewHolder.getView(R.id.imageView));
    }
}
