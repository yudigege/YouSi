package com.example.qsys.yousi;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qsys.yousi.activity.BaseActivity;
import com.example.qsys.yousi.fragment.initlogin.readylogin.ReadyLoginFragment;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new ReadyLoginFragment());
    }
}

