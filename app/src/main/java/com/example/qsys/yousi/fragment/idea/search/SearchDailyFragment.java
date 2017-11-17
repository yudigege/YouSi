package com.example.qsys.yousi.fragment.idea.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.fragment.BaseFragment;
import com.google.android.flexbox.FlexboxLayout;

import butterknife.BindView;

/**
 * @author hanshaokai
 * @date 2017/11/15 10:38
 */


public class SearchDailyFragment extends BaseFragment implements SearchDailyView {

    public SearchDailyPresenterExtend searchDailyPresenterExtend;
    @BindView(R.id.et_search_key_words)
    EditText etSearchKeyWords;
    @BindView(R.id.tv_cancel_daily)
    TextView tvCancelDaily;

    @BindView(R.id.ll_search_cache_container)
    LinearLayout ll_search_cache_container;

    @BindView(R.id.flexbox_layout_record_frequently)
    FlexboxLayout flexbox_layout_record_frequently;

    @Override
    public void showResponseData(BaseResponse response) {

    }

    @Override
    public void showMessage(String smg) {

    }

    @Override
    public void showEmptyViewByCode(int code, String msg) {

    }

    @Override
    public void showProgressView(Boolean b) {

    }

    @Override
    public Boolean isActive() {

        return isAdded();

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_daily_search, container, false);
        return inflate;
    }


    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        searchDailyPresenterExtend = new SearchDailyPresenterExtend();
        searchDailyPresenterExtend.setPresenterView(this);
        initListener();
        searchDailyPresenterExtend.getSearchItemMostFrequently();
    }


    private void initListener() {
        tvCancelDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        searchDailyPresenterExtend.detacheView();
    }

    public static SearchDailyFragment newInstance() {
        return new SearchDailyFragment();

    }
}
