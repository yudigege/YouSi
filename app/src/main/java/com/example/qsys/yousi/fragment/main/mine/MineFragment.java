package com.example.qsys.yousi.fragment.main.mine;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.activity.MineActivity;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.ActivityUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by hanshaokai on 2017/10/17 15:19
 */

public class MineFragment extends BaseFragment implements MinePageView {
    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.img_btn_action_include)
    ImageButton imgBtnActionInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.tv_nick_name_mine)
    TextView tvNickNameMine;
    @BindView(R.id.tv_nick_name_mine2)
    TextView tvNickNameMine2;
    @BindView(R.id.tv_mine_daily)
    TextView tvMineDaily;
    @BindView(R.id.tv_mine_read_pression)
    TextView tvMineReadPression;
    private MinePresenterExtend mPresenter = null;

    @Override
    public void showResponseData(BaseResponse response) {

    }

    @Override
    public void showMessage(String smg) {
        ToastUtils.showShort(smg);
    }

    @Override
    public void showEmptyViewByCode(int code) {
        switch (code) {

            case Constant.SERVER_ERROR:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.error_server_msg));
                break;
            case Constant.NET_UNABLE:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.net_error));
                break;
            case Constant.NO_CONTENT:
                llRoot.setVisibility(View.VISIBLE);
                errorTextView.setText(getResources().getString(R.string.no_content));
                break;
            default:
        }
    }

    @Override
    public void showProgressView(Boolean b) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mine_main, container, false);
        return view;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresenter = new MinePresenterExtend();
        mPresenter.setPresenterView(this);
        initToolBar(toolbarInclude, false, getResources().getString(R.string.mine), -1, false);
        initListener();
        mPresenter.getUserData();
        showViewByData();
    }

    private void showViewByData() {
        tvNickNameMine2.setText(CustomApplication.userEntity.getNick_name());
        tvNickNameMine.setText(CustomApplication.userEntity.getNick_name());
    }

    private void initListener() {
        appbarClude.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int visibility = tvNickNameMine2.getVisibility();
                tvNickNameMine.setVisibility(visibility == View.INVISIBLE ? View.VISIBLE : View.GONE);
                if (verticalOffset == 0) {
                    //展开时
                    tvNickNameMine.setVisibility(View.GONE);
                    tvTitleInclude.setTextColor(ContextCompat.getColor(baseFragmentActivity, R.color.black));
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //收缩完时
                    tvTitleInclude.setTextColor(ContextCompat.getColor(baseFragmentActivity, R.color.white));
                    tvNickNameMine.setVisibility(View.VISIBLE);
                } else if (Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() / 2) {
                    //滚动中间状态
                    /*返回值为0，visible；返回值为4，invisible；返回值为8，gone。*/
                    tvNickNameMine.setVisibility(View.VISIBLE);
                } else {
                    tvNickNameMine.setVisibility(View.GONE);

                }
            }
        });
        tvNickNameMine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(null, baseFragmentActivity, MineActivity.class);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除持有的弱引用
        mPresenter.detacheView();
    }


    @Override
    public Boolean isActive() {
        return isAdded();
    }


    public static Fragment newInstance() {
        MineFragment mineFragment = new MineFragment();
        return mineFragment;
    }


}
