package com.example.qsys.yousi.fragment.main.bookshelf;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.adapter.bookshelf.BookShelfAdapter;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.BookResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.SizeUtils;
import com.example.qsys.yousi.common.widget.recyclerview.SpacesItemDecoration;
import com.example.qsys.yousi.fragment.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * @author hanshaokai
 * @date 2017/10/17 15:19
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
    @BindView(R.id.rlv_book_shelf_main)
    XRecyclerView rlvBookShelfMain;
    private BookShelfPresenterExtend mPresenter = null;

    private List<BookResponse.ResultsBean> mListBook = new ArrayList<>();
    public BookShelfAdapter bookShelfAdapter;

    @Override
    public void showResponseData(BaseResponse response) {
        mListBook.clear();
        List<BookResponse.ResultsBean> results = ((BookResponse) response).getResults();
        mListBook.addAll(results);
        bookShelfAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String smg) {

    }

    @Override
    public void showEmptyViewByCode(int code,String msg) {

        switch (code) {
            case Constant.SERVER_ERROR:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg,msg));
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
                errorTextView.setText(getResources().getString(R.string.no_content));
                break;
            default:
        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_book_shelf_main, container, false);
        return view;
    }

    private void initListener() {
        llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llRoot.setVisibility(View.GONE);
                mPresenter.getBookData();
            }
        });

    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new BookShelfPresenterExtend();
        mPresenter.setPresenterView(this);
        initListener();
        initToolBar(toolbarInclude, false, getResources().getString(R.string.book_stock), -1, false);
        initAdapter();
        imgBtnActionInclude.setVisibility(View.GONE);
        //防止图书页面也显示图标
        setHasOptionsMenu(false);
        mPresenter.getBookData();
    }

    private void initAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(baseFragmentActivity, 3);
        rlvBookShelfMain.setLayoutManager(layoutManager);
        rlvBookShelfMain.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(10)));
        bookShelfAdapter = new BookShelfAdapter(baseFragmentActivity, mListBook);
        rlvBookShelfMain.setAdapter(bookShelfAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListBook.clear();
        //解除持有的弱引用
        mPresenter.detacheView();
    }



    @Override
    public void showProgressView(Boolean b) {
        if (b) {
            showProgressDialog(getResources().getString(R.string.loading));
        } else {
            dismissProgressDialog();
        }
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
