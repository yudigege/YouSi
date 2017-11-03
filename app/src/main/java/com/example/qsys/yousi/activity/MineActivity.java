package com.example.qsys.yousi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qsys.yousi.fragment.mine.minedetail.MineDetailFragment;

/**
 * @author hanshaokai
 */
public class MineActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(MineDetailFragment.newInstance());
    }
}
