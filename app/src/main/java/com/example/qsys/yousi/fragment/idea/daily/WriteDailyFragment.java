package com.example.qsys.yousi.fragment.idea.daily;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author hanshaokai
 * @date 2017/10/27 16:23
 */


public class WriteDailyFragment extends BaseFragment implements WriteDailyView {

    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.et_write_title_daily)
    EditText etWriteTitleDaily;
    @BindView(R.id.et_write_content_daily)
    EditText etWriteContentDaily;
    Unbinder unbinder;
    public WriteDailyPresenterExtend mPresenter;

    @Override
    public void showResponseData(BaseResponse response) {

    }

    @Override
    public void showMessage(String smg) {

    }

    @Override
    public void showEmptyViewByCode(int code) {

    }

    @Override
    public void showProgressView(Boolean b) {

    }

    @Override
    public Boolean isActive() {
        return null;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_write_daily_idea, container,false);
        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new WriteDailyPresenterExtend();
        mPresenter.setPresenterView(this);
        initToolBarThis();
    }

    private void initToolBarThis() {
        setHasOptionsMenu(true);//不设置 导致不起作用
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarInclude.setNavigationIcon(R.mipmap.ic_notification_hide);
        actionBar.setTitle(getResources().getString(R.string.write_daily));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                removeFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.notifi_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detacheView();
    }

    public static Fragment newInstance() {

        return new WriteDailyFragment();
    }
}
