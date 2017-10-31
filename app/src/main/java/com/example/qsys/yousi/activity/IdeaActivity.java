package com.example.qsys.yousi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.fragment.idea.daily.WriteDailyFragment;

public class IdeaActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int intExtra = getIntent().getIntExtra(Constant.IDEA_STYPE, Constant.DAYLIE);

        super.onCreate(savedInstanceState);
        switch (intExtra) {
            case Constant.DAYLIE:
                addFragment(WriteDailyFragment.newInstance());
                break;
            case Constant.READPRESSION:
                addFragment(WriteDailyFragment.newInstance());
                break;

            default:
        }

    }
}
