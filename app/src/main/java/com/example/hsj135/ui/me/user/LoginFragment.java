package com.example.hsj135.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hsj135.R;
import com.example.hsj135.base.BaseFragment2;
import com.example.hsj135.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginFragment extends BaseFragment2 {
    private EditText editText;
    private EditText editText2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        editText = root.findViewById(R.id.editText);
        editText2 = root.findViewById(R.id.editText2);

        TextView textView = root.findViewById(R.id.textView);
        textView.setOnClickListener(v -> Navigation.findNavController( v).navigate(R.id.action_loginFragment_to_registerFragment));
        TextView textView2 = root.findViewById(R.id.textView2);
        textView2.setOnClickListener(v -> Navigation.findNavController( v).navigate(R.id.action_loginFragment_to_findPasswordFragment));
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::login);


        return root;
    }

    private void login(final View v) {
        String username = editText.getText().toString().trim();
        String password = editText2.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            editText.setError("账号不能为空");
            return;
        }if (TextUtils.isEmpty(password)) {
            editText.setError("密码不能为空");
            return;
        }
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    //登录成功
                    User user = User.getCurrentUser(User.class);
                    Snackbar .make(v, "登录成功"+user.getUsername(), Snackbar.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigateUp();
                } else {
                    //登录失败
                    Snackbar .make(v, "登录失败：" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}