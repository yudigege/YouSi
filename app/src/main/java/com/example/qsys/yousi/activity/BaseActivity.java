package com.example.qsys.yousi.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.manager.AppManager;

/**
 * Created by hanshaokai on 2017/9/30 13:42
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected CustomApplication customApplication;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        customApplication = (CustomApplication) getApplicationContext();
      /*  //透明状态栏效果
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int systemUiFlagFullscreen = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(systemUiFlagFullscreen);//全屏设置 flag表示让应用的主题内容占用系统状态栏的空间
            getWindow().setStatusBarColor(Color.TRANSPARENT);//将状态栏设置成透明色
        }
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide(); //隐藏状态栏和anctionBar4.1以下不兼容*/
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        setContentView(R.layout.activity_fragment_container);
    }
    //添加fragment
    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }
    // 移除fragment
    public void removeFragemt() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
    //返回键 返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
