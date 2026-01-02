package com.example.hsj135.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.hsj135.R;
import com.example.hsj135.bean.AndroidBean;

import java.util.List;

public class AndroidAdapter extends BaseQuickAdapter<AndroidBean, BaseViewHolder> {
        public AndroidAdapter(@Nullable List<AndroidBean> data) {
            super(R.layout.item_python, data);
        }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, AndroidBean androidBean) {
        baseViewHolder.setText(R.id.textView, androidBean.getAddress());
        baseViewHolder.setText(R.id.textView2, androidBean.getContent());
        baseViewHolder.setText(R.id.textView3, androidBean.getOpen_class());
    }
}
