package com.example.qsys.yousi.fragment.mine.recordtrack;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.RecordTrackResponse;
import com.example.qsys.yousi.bean.TrackDayResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.constants.TimeConstants;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.common.util.TimeUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author hanshaokai
 * @date 2017/12/7 17:16
 */
public class RecordTrackShowFragment extends BaseFragment implements RecordTrackView {

    public RecordTrackShowPresenter mPresenter;
    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.tv_track_above_time)
    TextView tvTrackAboveTime;
    @BindView(R.id.tv_track_above_entries)
    TextView tvTrackAboveEntries;
    @BindView(R.id.tv_track_continue_days)
    TextView tvTrackContinueDays;
    @BindView(R.id.nest_scroll_container)
    NestedScrollView nest_scroll_container;
    @BindView(R.id.record_track_view)
    com.example.qsys.yousi.common.widget.customView.RecordTrackCustomView recordTrackView;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_record_track, container, false);
        mPresenter = new RecordTrackShowPresenterExtend();
        mPresenter.setPresenterView(this);
        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        initToolBar();
        mPresenter.getRecordTrackData();
        initListener();
    }

    private void initListener() {
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nest_scroll_container.setVisibility(View.VISIBLE);
                llRoot.setVisibility(View.GONE);
                mPresenter.getRecordTrackData();
                mPresenter.getTrackDayData();
            }
        });

    }

    private void initToolBar() {
        setHasOptionsMenu(true);
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        tvTitleInclude.setText(getResources().getString(R.string.record_track));
        actionBar.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                removeFragment();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detacheView();

    }

    @Override
    public void showResponseData(BaseResponse response) {
        RecordTrackResponse recordTrackResponse = (RecordTrackResponse) response;
        RecordTrackResponse.ResultsBean results = recordTrackResponse.getResults();
        tvTrackContinueDays.setText(String.valueOf(results.getContinue_days() )+ "天");
        tvTrackAboveEntries.setText(results.getDaily_item_number() + results.getRead_item_number() + "条");
        Long time = TimeUtils.millis2TimeSpan(results.getDaily_record_time_count(), TimeConstants.MIN)
                + TimeUtils.millis2TimeSpan(results.getRead_record_time_count(), TimeConstants.MIN);
        tvTrackAboveTime.setText(time==0? "不足1分钟":String.valueOf(time));
        mPresenter.getTrackDayData();
    }

    @Override
    public void showMessage(String smg) {
        ToastUtils.showShort(smg);
    }

    @Override
    public void showEmptyViewByCode(int code, String msg) {
        nest_scroll_container.setVisibility(View.GONE);
        switch (code) {
            case Constant.SERVER_ERROR:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg, msg));
                break;
            case Constant.SERVER_UNREACH:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg2));
                break;
            case Constant.NET_UNABLE:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.no_net));
                break;
            case Constant.NO_CONTENT:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText("还没有轨迹哦，快快记录点什么吧");
                break;
            default:
        }
    }

    @Override
    public void showProgressView(Boolean b) {
        if (b) {
            showProgressDialog(getString(R.string.loading));
        } else {
            dismissProgressDialog();
        }
    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }

    public static Fragment newInstance() {
        return new RecordTrackShowFragment();
    }

    @Override
    public void showDayTrackData(BaseResponse trackDayResponse) {
        TrackDayResponse trackDayResponse1 = (TrackDayResponse) trackDayResponse;
        List<TrackDayResponse.ResultsBean> results = trackDayResponse1.getResults();
        long timeMillis = System.currentTimeMillis();
        List<String> day = new ArrayList<>();
        List<Long> writeTime = new ArrayList<>();
        TrackDayResponse.ResultsBean resultsHave = null;
        for (int i = 7; i > 0; i--) {
            Boolean isHave = false;
            long dayTime = timeMillis - (i - 1) * TimeConstants.DAY;
            day.add(TimeUtils.millisToYMDate(dayTime));

            for (TrackDayResponse.ResultsBean resultsBean : results) {
                if (resultsBean.getCreat_day() == dayTime / TimeConstants.DAY) {
                    isHave = true;
                    resultsHave = resultsBean;
                }
            }
            if (isHave) {
                writeTime.add((resultsHave.getDaily_record_time_count() + resultsHave.getRead_record_time_count()) / TimeConstants.MINL);
            } else {
                writeTime.add(0L);
            }
        }

        LogUtils.d(day.size() + "日期" + day.get(0));
        LogUtils.d(writeTime.size() + "时间个数" + writeTime.get(0));

        recordTrackView.setScore(writeTime, day);

    }










}
