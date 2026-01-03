package com.example.hsj135.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hsj135.R;
import com.example.hsj135.base.BaseFragment2;
import com.example.hsj135.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegisterFragment extends BaseFragment2 {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        editText = root.findViewById(R.id.editText);
        editText2 = root.findViewById(R.id.editText2);
        editText3 = root.findViewById(R.id.editText3);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::signUp);

        return root;
    }

    private void signUp(final View v) {
        String username = editText.getText().toString().trim();
        String password = editText2.getText().toString().trim();
        String email = editText3.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            editText.setError("账号不能为空");
            return;
        }if (TextUtils.isEmpty(password)) {
            editText.setError("密码不能为空");
            return;
        }if (TextUtils.isEmpty(email)) {
            editText.setError("邮箱不能为空");
            return;
        }
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setNickName(username);
        user.setSex(true);
        user.setInfo("这个人很懒，什么都没有留下...");
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    //注册成功
                    Snackbar.make(v, "注册成功", Snackbar.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigateUp();
                } else {
                    //注册失败
                    Snackbar.make(v, "注册失败：" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}