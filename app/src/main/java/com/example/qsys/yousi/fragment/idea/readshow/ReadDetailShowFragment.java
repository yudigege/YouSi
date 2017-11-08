package com.example.qsys.yousi.fragment.idea.readshow;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.fragment.BaseFragment;

import butterknife.BindView;

/**
 * @author hanshaokai
 * @date 2017/11/6 18:01
 */
public class ReadDetailShowFragment extends BaseFragment implements ReadDetaiShowView {
    public ReadDetailShowPresenterExtend readDetailShowPresenterExtend;
    public String content;
    @BindView(R.id.img_btn_action_include)
    ImageView imgBtnActionInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.tv_title)
    EditText tvTitle;
    @BindView(R.id.tv_content)
    EditText tvContent;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        View inflate = inflater.inflate(R.layout.fragment_read_pression_detail, container, false);

        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        readDetailShowPresenterExtend = new ReadDetailShowPresenterExtend();
        readDetailShowPresenterExtend.setPresenterView(this);
        initToolBar();
        showdata();
    }

    private void initToolBar() {
        setHasOptionsMenu(true);
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarInclude.setNavigationIcon(R.mipmap.ic_notification_hide);
        actionBar.setTitle(getResources().getString(R.string.read_pression_mine));
    }

    private void showdata() {

        Bundle arguments = getArguments();
        DaysResportResponse.ResultsBean resultsBean = arguments.getParcelable("resultsBean");
        tvTitle.setText("《" + resultsBean.getBookname() + "》");
        tvTitle.setEnabled(false);
        tvContent.setText(resultsBean.getContent());
        tvContent.setEnabled(false);

    }

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

    public static ReadDetailShowFragment newInstance(DaysResportResponse.ResultsBean resultsBean) {
        Bundle args = new Bundle();
        args.putParcelable("resultsBean", resultsBean);
        ReadDetailShowFragment fragment = new ReadDetailShowFragment();
        fragment.setArguments(args);
        return fragment;
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
    public Boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        readDetailShowPresenterExtend.detacheView();
    }
}
