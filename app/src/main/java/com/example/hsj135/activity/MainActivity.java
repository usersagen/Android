package com.example.hsj135.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.hsj135.R;
import com.example.hsj135.base.OnFragmentKeyDownListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hsj135.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    public List<OnFragmentKeyDownListener> onFragmentkeyDownListenerList = new ArrayList<>();
    private long exitTime;

    public void setOnFragmentKeyDownListener(OnFragmentKeyDownListener onFragmentkeyDownListener) {
        if (!onFragmentkeyDownListenerList.contains(onFragmentkeyDownListener)) {
            onFragmentkeyDownListenerList.add(onFragmentkeyDownListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
         appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_chart, R.id.navigation_video, R.id.navigation_me)
                .build();
         navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("MainActivity", "onKeyDown called, keyCode: " + keyCode);
        Log.d("MainActivity", "Listener list size: " + onFragmentkeyDownListenerList.size());

        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前目的地ID
            int currentDestinationId = getCurrentDestinationId();

            // 1. 优先让Fragment处理返回事件
            if (!onFragmentkeyDownListenerList.isEmpty()) {
                OnFragmentKeyDownListener lastListener = onFragmentkeyDownListenerList.get(onFragmentkeyDownListenerList.size() - 1);
                if (lastListener != null && lastListener.onKeyDown(keyCode, event)) {
                    return true; // Fragment处理了事件，直接返回
                }
            }

            // 2. 判断是否是主页
            boolean isAtHome = isAtHomeDestination();

            // 3. 如果不在主页，执行导航返回
            if (!isAtHome) {
                // 先移除当前Fragment的监听器
                if (!onFragmentkeyDownListenerList.isEmpty()) {
                    onFragmentkeyDownListenerList.remove(onFragmentkeyDownListenerList.size() - 1);
                }
                // 执行导航返回
                return navController.navigateUp();
            }

            // 4. 已经在主页，执行退出提示逻辑
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            Log.d("MainActivity", "isAtHomeDestination: " + isAtHomeDestination());
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    // 检查是否在主页
    private boolean isAtHomeDestination() {
        if (navController == null) return true;

        NavDestination currentDestination = navController.getCurrentDestination();
        if (currentDestination == null) return true;

        int currentId = currentDestination.getId();

        // 主页的Fragment ID（根据你的导航图配置）
        return currentId == R.id.navigation_home ||
                currentId == R.id.navigation_chart ||
                currentId == R.id.navigation_video ||
                currentId == R.id.navigation_me;
    }

    // 获取当前目的地ID
    private int getCurrentDestinationId() {
        if (navController != null && navController.getCurrentDestination() != null) {
            return navController.getCurrentDestination().getId();
        }
        return -1;
    }

    // 移除onSupportNavigateUp中的监听器移除逻辑，因为现在在onKeyDown中处理
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

}