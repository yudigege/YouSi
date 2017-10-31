package com.example.qsys.yousi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.fragment.idea.daily.WriteDailyFragment;
import com.example.qsys.yousi.fragment.idea.readpression.WriteReadPressionFragment;

public class IdeaActivity extends BaseActivity {


    public int intExtra;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            intExtra = extras.getInt(Constant.IDEA_STYPE);
        }
        super.onCreate(savedInstanceState);
        switch (intExtra) {
            case Constant.DAYLIE:
                addFragment(WriteDailyFragment.newInstance());
                break;
            case Constant.READPRESSION:
                addFragment(WriteReadPressionFragment.newInstance());
                break;

            default:
        }

    }
}
