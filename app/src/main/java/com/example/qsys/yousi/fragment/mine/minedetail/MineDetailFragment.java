package com.example.qsys.yousi.fragment.mine.minedetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.FileProvider;
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
import com.example.qsys.yousi.common.util.FileUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.dialog.AppStyleDialog;
import com.example.qsys.yousi.common.widget.updatelisenner.UpdateMIneDetailObserver;
import com.example.qsys.yousi.fragment.BaseFragment;

import java.util.ArrayList;
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
                if (appStyleDialogNick == null) {
                    appStyleDialogNick = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_BIO) {
                        @Override
                        public void doGetBioDetail(String bio) {
                            super.doGetBioDetail(bio);
                            user = new UserResponse.ResultsBean();
                            Map<String, String> map = new HashMap(1);
                            map.put("bio", bio);
                            user.setBio(bio);
                            mPresnter.updateUserInfor(map, Constant.EDITE_BIO);
                            appStyleDialogNick = null;
                        }
                    };
                    appStyleDialogNick.show();
                } else {
                    appStyleDialogNick.show();
                }
            }
        });

        tvDetailBlogMine.setText(CustomApplication.userEntity.getBlog());
        blogArrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appStyleDialogNick == null) {
                    appStyleDialogNick = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_BLOG_URL) {
                        @Override
                        public void doGetBlogDetail(String blog) {
                            super.doGetBlogDetail(blog);
                            user = new UserResponse.ResultsBean();
                            Map<String, String> map = new HashMap(1);
                            map.put("blog", blog);
                            user.setBlog(blog);
                            mPresnter.updateUserInfor(map, Constant.EDITE_BLOG_URL);
                            //修改的弹窗用的同一个 不置空 会显示同一个
                            appStyleDialogNick = null;
                        }
                    };
                    appStyleDialogNick.show();
                } else {
                    appStyleDialogNick.show();
                }

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
                                                          Map<String, String> map = new HashMap(1);
                                                          map.put("nick_name",nick);
                                                          user.setNick_name(nick);
                                                          mPresnter.updateUserInfor(map, Constant.EDITE_NICK);
                                                          appStyleDialogNick = null;
                                                      }
                                                  };
                                                  appStyleDialogNick.show();
                                              } else {
                                                  appStyleDialogNick.show();
                                              }
                                          }
                                      }
        );
        tvDetailSexMine.setText(CustomApplication.userEntity.getGender() == Constant.MAN ? getString(R.string.man) : getString(R.string.female));
        sexArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appStyleDialogNick == null) {
                    appStyleDialogNick = new AppStyleDialog(baseFragmentActivity, -1, Constant.EDITE_SEX) {
                        @Override
                        public void doGetGender(int gender) {
                            super.doGetGender(gender);
                            if (gender == Constant.FEAMALE || gender == Constant.MAN) {
                                user = new UserResponse.ResultsBean();
                                Map<String, String> map = new HashMap(1);
                                map.put("gender", gender + "");
                                user.setGender(gender);
                                mPresnter.updateUserInfor(map, Constant.EDITE_SEX);
                                appStyleDialogNick = null;
                            }
                        }
                    };
                    appStyleDialogNick.show();
                } else {
                    appStyleDialogNick.show();
                }

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
        ToastUtils.showShort(smg);
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
                ArrayList<UpdateMIneDetailObserver.UpdateMineDetailLisener> baseFragments = UpdateMIneDetailObserver.baseFragments;
                for (UpdateMIneDetailObserver.UpdateMineDetailLisener updateMineDetailLisener : baseFragments) {
                    updateMineDetailLisener.updateMineDetail(user.getNick_name());
                }
                break;
            case Constant.EDITE_SEX:
                tvDetailSexMine.setText(user.getGender() == 1 ? baseFragmentActivity.getString(R.string.man) : baseFragmentActivity.getString(R.string.female));
                //把保存的类 属性信息也改了
                CustomApplication.userEntity.setGender(user.getGender());
                break;
            case Constant.EDITE_BIO:
                tvDetailBioMine.setText(user.getBio());
                //把保存的类 属性信息也改了
                CustomApplication.userEntity.setBio(user.getBio());
                break;
            case Constant.EDITE_BLOG_URL:
                tvDetailBlogMine.setText(user.getBlog());
                CustomApplication.userEntity.setBlog(user.getBlog());
            default:
        }
    }


    static final int INTENTFORCAMERA = 1;
    static final int INTENTFORPHOTO = 2;

    public void toCamera() {

        Intent intentNow = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //针对Android7.0，需要通过FileProvider封装过的路径，提供给外部调用
            //通过FileProvider创建一个content类型的Uri，进行封装
            uri = FileProvider.getUriForFile(baseFragmentActivity, "com.bw.dliao", FileUtils.getFileByPath(""));
        } else {
            uri = Uri.fromFile(FileUtils.getFileByPath(""));
        }

        intentNow.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        baseFragmentActivity.startActivityForResult(intentNow, INTENTFORCAMERA);


    }


}
