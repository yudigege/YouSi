package com.example.qsys.yousi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qsys.yousi.fragment.mine.recordtrack.RecordTrackShowFragment;

/**
 * @author hanshaokai
 * @date 2017/12/7 17:00
 */


public class RecordTrackActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(RecordTrackShowFragment.newInstance());
    }
}
