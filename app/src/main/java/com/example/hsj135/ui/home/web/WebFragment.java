package com.example.hsj135.ui.home.web;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.hsj135.R;
import com.example.hsj135.activity.MainActivity;
import com.example.hsj135.base.BaseFragment2;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;


public class WebFragment extends BaseFragment2 {
    private AgentWeb mAgentWebView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_web, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.setOnFragmentKeyDownListener(this);
        }

        mAgentWebView = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) root, new ViewGroup.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            ActionBar supportActionBar = activity.getSupportActionBar();
                            if (supportActionBar != null) {
                                supportActionBar.setTitle(title);
                            }
                        }
                    }
                })
                .createAgentWeb()
                .ready()
                .go(getArguments() != null ? getArguments().getString("url") : "http://www.jd.com");
        return root;
    }

    @Override
    public void onPause() {
        mAgentWebView.getWebLifeCycle().onPause();
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        mAgentWebView.getWebLifeCycle().onResume();
        // 不需要在这里重复添加监听器，BaseFragment2 已经处理了
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView(); // 这会调用父类的 onDetach，从而移除监听器

        if (mAgentWebView != null) {
            mAgentWebView.getWebLifeCycle().onDestroy();
            mAgentWebView = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("WebFragment", "onKeyDown called, keyCode: " + keyCode);

        // 只有WebView能返回上一页时，才拦截事件
        if (mAgentWebView != null) {
            boolean handled = mAgentWebView.handleKeyEvent(keyCode, event);
            Log.d("WebFragment", "WebView handleKeyEvent result: " + handled);
            if (handled) {
                return true;
            }
        } else {
            Log.d("WebFragment", "mAgentWebView is null");
        }

        // WebView无历史记录时，返回false，让Activity处理
        return false;
    }
}