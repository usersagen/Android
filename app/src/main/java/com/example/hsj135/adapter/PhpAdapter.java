package com.example.hsj135.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.hsj135.R;
import com.example.hsj135.bean.PhpBean;

import java.util.List;

public class PhpAdapter extends BaseQuickAdapter<PhpBean, BaseViewHolder> {
        public PhpAdapter(@Nullable List<PhpBean> data) {
            super(R.layout.item_python, data);
        }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, PhpBean phpBean) {
        baseViewHolder.setText(R.id.textView, phpBean.getAddress());
        baseViewHolder.setText(R.id.textView2, phpBean.getContent());
        baseViewHolder.setText(R.id.textView3, phpBean.getOpen_class());
    }
}
