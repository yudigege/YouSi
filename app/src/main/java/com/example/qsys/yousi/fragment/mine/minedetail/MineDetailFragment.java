package com.example.qsys.yousi.fragment.mine.minedetail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.widget.dialog.AppStyleDialog;
import com.example.qsys.yousi.fragment.BaseFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @author hanshaokai
 * @date 2017/10/31 18:01
 */


public class MineDetailFragment extends BaseFragment implements MineDetailView {
    public MineDetailPresenterExtend mPresnter;
    @BindView(R.id.tv_title_include)
    TextView tvTitleInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.tv_detail_name_mine)
    TextView tvDetailNameMine;
    @BindView(R.id.avatar_arrow)
    ImageView nameArrow;
    @BindView(R.id.tv_detail_nick_mine)
    TextView tvDetailNickMine;
    @BindView(R.id.avatar_arrow2)
    ImageView nickArrow2;
    @BindView(R.id.tv_detail_sex_mine)
    TextView tvDetailSexMine;
    @BindView(R.id.avatar_arrow3)
    ImageView sexArrow3;
    @BindView(R.id.tv_bio)
    TextView tvBio;
    @BindView(R.id.tv_detail_bio_mine)
    TextView tvDetailBioMine;
    @BindView(R.id.avatar_arrow4)
    ImageView bioArrow4;
    @BindView(R.id.tv_blog)
    TextView tvBlog;
    @BindView(R.id.tv_detail_blog_mine)
    TextView tvDetailBlogMine;
    @BindView(R.id.avatar_arrow5)
    ImageView blogArrow5;
    public UserResponse.ResultsBean user;
    public AppStyleDialog appStyleDialogNick;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_mine_detail, container, false);
        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        mPresnter = new MineDetailPresenterExtend();
        mPresnter.setPresenterView(this);
        initToolBar();
    }

    private void initToolBar() {
        setHasOptionsMenu(true);
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //设置溢出图标
        //toolbarInclude.setOverflowIcon(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_launcher));
        actionBar.setTitle(getResources().getString(R.string.mine_infor));
        showMineDetailData();
    }

    private void showMineDetailData() {
        tvDetailBioMine.setText(CustomApplication.userEntity.getBio());
        bioArrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvDetailBlogMine.setText(CustomApplication.userEntity.getBlog());
        blogArrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvDetailNameMine.setText(CustomApplication.userEntity.getReal_name());
        nameArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvDetailNickMine.setText(CustomApplication.userEntity.getNick_name());
        nickArrow2.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              if (appStyleDialogNick == null) {
                                                  appStyleDialogNick = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_NICK) {
                                                      @Override
                                                      public void doGetdata(String nick) {
                                                          super.doGetdata(nick);
                                                          user = new UserResponse.ResultsBean();
                                                          Map<String, String> map = new HashMap();
                                                          map.put("nick_name",nick);
                                                          user.setNick_name(nick);
                                                          mPresnter.updateUserInfor(map);
                                                      }
                                                  };
                                                  appStyleDialogNick.show();
                                              } else {
                                                  appStyleDialogNick.show();
                                              }
                                          }
                                      }
        );


        tvDetailSexMine.setText(CustomApplication.userEntity.getGender() == Constant.MAN ? getString(R.string.man) : getString(R.string.femal));
        sexArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
    public void showResponseData(BaseResponse response) {

    }

    @Override
    public void showMessage(String smg) {

    }

    @Override
    public void showEmptyViewByCode(int code, String smg) {

    }

    @Override
    public void showProgressView(Boolean b) {

    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }

    public static MineDetailFragment newInstance() {

        return new MineDetailFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresnter.detacheView();
    }

    @Override
    public void setUserInfor(int typeEdit) {
        switch (typeEdit) {
            case Constant.EDITE_NICK:
                tvDetailNickMine.setText(user.getNick_name());
                //把保存的类 属性信息也改了
                CustomApplication.userEntity.setNick_name(user.getNick_name());
            default:
        }


    }
}
