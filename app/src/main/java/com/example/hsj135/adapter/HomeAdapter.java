package com.example.hsj135.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.hsj135.R;
import com.example.hsj135.bean.NewsBean;
import com.example.hsj135.utils.NetUtils;

import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<NewsBean, BaseViewHolder> {

    public HomeAdapter(List<NewsBean> data) {
        super(data);
        addItemType(1, R.layout.item_home1);
        addItemType(2, R.layout.item_home2);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsBean item) {
        switch (item.getItemType()) {
            case 1:
                helper.setText(R.id.textView, item.getNewsName());
                helper.setText(R.id.textView2, item.getNewsTypeName());
                Glide.with(getContext())
                        .load(NetUtils.BASE_URL + item.getImg1())
                        .into((ImageView) helper.getView(R.id.imageView));
                break;
            case 2:
                helper.setText(R.id.textView, item.getNewsName());
                helper.setText(R.id.textView2, item.getNewsTypeName());
                Glide.with(getContext()).load(NetUtils.BASE_URL + item.getImg1()).into((ImageView) helper.getView(R.id.imageView));
                Glide.with(getContext()).load(NetUtils.BASE_URL + item.getImg2()).into((ImageView) helper.getView(R.id.imageView2));
                Glide.with(getContext()).load(NetUtils.BASE_URL + item.getImg3()).into((ImageView) helper.getView(R.id.imageView3));
                break;
        }
    }
}
