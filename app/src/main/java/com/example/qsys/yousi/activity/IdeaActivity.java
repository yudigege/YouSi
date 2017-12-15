package com.example.qsys.yousi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.fragment.idea.daily.WriteDailyFragment;
import com.example.qsys.yousi.fragment.idea.detailshow.IdeaDetailShowFragment;
import com.example.qsys.yousi.fragment.idea.readpression.WriteReadPressionFragment;
import com.example.qsys.yousi.fragment.idea.readshow.ReadDetailShowFragment;
import com.example.qsys.yousi.fragment.idea.search.SearchDailyFragment;

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
            //写日志界面
            case Constant.DAYLIE:
                addFragment(WriteDailyFragment.newInstance());
                break;
            case Constant.READPRESSION:
                //写读后感界面
                addFragment(WriteReadPressionFragment.newInstance());
                break;
            case Constant.READPRESSION_DETAIL:
                //展示日志 详情界面
                DaysResportResponse.ResultsBean parcelable = extras.getParcelable(Constant.READPRESSION_OBJECT);
                IdeaDetailShowFragment ideaDetailShowFragment = IdeaDetailShowFragment.newInstance(parcelable);
                addFragment(ideaDetailShowFragment);
                break;
            case Constant.READPRESSION_READ:
                //展示读后感界面
                DaysResportResponse.ResultsBean parcelable2 = extras.getParcelable(Constant.READPRESSION_OBJECT);
                ReadDetailShowFragment readDetailShowFragment = ReadDetailShowFragment.newInstance(parcelable2);
                addFragment(readDetailShowFragment);
                break;
            case Constant.SEARCH_DAILY:
                addFragment(SearchDailyFragment.newInstance());
                break;
            default:
        }

    }
}
