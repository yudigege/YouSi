package com.example.qsys.yousi.fragment.main.bookshelf;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by hanshaokai on 2017/10/17 15:19
 */

public class BookShelfFragment extends BaseFragment implements BookShelfView {


    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.img_btn_action_include)
    ImageButton imgBtnActionInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    private BookShelfPresenterExtend mPresenter = null;

    @Override
    public void showResponseData(BaseResponse response) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_book_shelf_main, null, false);
        return view;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new BookShelfPresenterExtend();
        mPresenter.setPresenterView(this);
        initToolBar(toolbarInclude, false, getResources().getString(R.string.book_stock), -1, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除持有的弱引用
        mPresenter.detacheView();
    }

    @Override
    public void showMessage(String smg) {

    }

    @Override
    public void showProgressView(Boolean b) {

    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }


    public static Fragment newInstance() {
        BookShelfFragment bookShelfFragment=new BookShelfFragment();

        return bookShelfFragment;
    }
}
