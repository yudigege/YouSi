package com.example.qsys.yousi.fragment.main.mainpage;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.BookResponse;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.card.SlideCardPanel;
import com.example.qsys.yousi.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hanshaokai on 2017/10/17 15:19
 */

public class MainPageFragment extends BaseFragment implements MainPageView {
    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.img_btn_action_include)
    ImageButton imgBtnActionInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;

    List<BookResponse.ResultsBean> bookList = new ArrayList<>();//当前显示卡片的数据

    @BindView(R.id.cardPanel_main_page)
    SlideCardPanel cardPanel;
    private MainPagePresenterExtend mPresenter = null;

    @Override
    public void showResponseData(BaseResponse response) {
        bookList.clear();
        bookList.addAll(((BookResponse) response).getResults());
        cardPanel.fillData(bookList);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_main_page_main, null, false);
        return view;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new MainPagePresenterExtend();
        mPresenter.setPresenterView(this);
        initToolBar(toolbarInclude, false, getResources().getString(R.string.main_page), -1, false);
        initListener();
        mPresenter.getBookData();
    }

    private void initListener() {
        //加上对滑动卡片的监听
        SlideCardPanel.CardSwitchListener cardSwitchListener = new SlideCardPanel.CardSwitchListener() {
            @Override
            public void onShow(View cardView, int index) {
               /* cardView.findViewById(R.id.card_item_like_iv).setAlpha(0);
                cardView.findViewById(R.id.card_item_dislike_iv).setAlpha(0);*/
            }

            @Override
            public void onCardVanish(View changedView, int index, int type) {
                ToastUtils.showShort(type == SlideCardPanel.VANISH_TYPE_LEFT ? "左划" : "右划", Toast.LENGTH_SHORT);
            }

            @Override
            public void onItemClick(View view, int index) {
            }

            @Override
            public void onViewPosition(View changedView, float dx, float dy) {
             /*   changedView.findViewById(R.id.card_item_like_iv).setAlpha(dx);
                changedView.findViewById(R.id.card_item_dislike_iv).setAlpha(dy);*/
            }
        };
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
        MainPageFragment mainPageFragment = new MainPageFragment();
        return mainPageFragment;
    }


}
