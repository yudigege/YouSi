package com.example.qsys.yousi.fragment.idea.detailshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.fragment.BaseFragment;
import com.example.qsys.yousi.fragment.mine.minedetail.MineDetailPresenterExtend;

/**
 * @author hanshaokai
 * @date 2017/11/3 15:36
 */


public class IdeaDetailShowFragment extends BaseFragment implements IdeaDetailShowView {

    public MineDetailPresenterExtend mineDetailPresenterExtend;
    public String content;

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
        return isAdded();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        content = arguments.getString("content");

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        View inflate = inflater.inflate(R.layout.idea_detail_fragment, container, false);
        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mineDetailPresenterExtend = new MineDetailPresenterExtend();
        mineDetailPresenterExtend.setPresenterView(this);

    }

    public static IdeaDetailShowFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("content", content);
        IdeaDetailShowFragment fragment = new IdeaDetailShowFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
