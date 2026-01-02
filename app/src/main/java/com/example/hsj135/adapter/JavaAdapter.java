package com.example.hsj135.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.hsj135.R;
import com.example.hsj135.bean.JavaBean;

import java.util.List;

public class JavaAdapter extends BaseQuickAdapter<JavaBean, BaseViewHolder> {
        public JavaAdapter(@Nullable List<JavaBean> data) {
            super(R.layout.item_python, data);
        }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, JavaBean javaBean) {
        baseViewHolder.setText(R.id.textView, javaBean.getAddress());
        baseViewHolder.setText(R.id.textView2, javaBean.getContent());
        baseViewHolder.setText(R.id.textView3, javaBean.getOpen_class());
    }
}
