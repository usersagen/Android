package com.example.hsj135.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hsj135.R;
import com.example.hsj135.base.BaseFragment2;
import com.example.hsj135.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;


public class InfoFragment extends BaseFragment2 {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        TextView textView = root.findViewById(R.id.textView);
        if (textView != null) {
            textView.setText("一些文本");
        } else {
            Log.e("InfoFragment", "找不到 TextView，ID: R.id.some_text_view");
        }
        TextView textView2 = root.findViewById(R.id.textView2);
        TextView textView3 = root.findViewById(R.id.textView3);
        TextView textView4 = root.findViewById(R.id.textView4);
        TextView textView5 = root.findViewById(R.id.textView5);

        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            textView.setText(user.getUsername());
            textView2.setText(user.getNickName());
            textView3.setText(user.isSex() ? "男" : "女");
            textView4.setText(user.getEmail());
            textView5.setText(user.getInfo());
        }
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::logOut);



        return root;
    }

    private void logOut(View view) {
        BmobUser.logOut();
        Navigation.findNavController(view).navigateUp();
        Snackbar.make(view, "注销成功", Snackbar.LENGTH_SHORT).show();
    }
}