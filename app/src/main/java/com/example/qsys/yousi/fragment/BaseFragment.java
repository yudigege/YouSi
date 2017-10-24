package com.example.qsys.yousi.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.activity.BaseActivity;
import com.example.qsys.yousi.common.util.LogUtils;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hanshaokai on 2017/10/9 15:42
 */
public abstract class BaseFragment extends RxFragment {
    /**
     * 基类 定义共公方法
     */
    public BaseActivity baseFragmentActivity;
    protected ProgressDialog progressDialog;
    public Unbinder unbinder;
    @BindView(R.id.errorImageView)
    protected ImageView errorImageView;
    @BindView(R.id.progressBar)
    protected  ProgressBar progressBar;
    @BindView(R.id.errorTextView)
    protected  TextView errorTextView;
    @BindView(R.id.clickLayout)
    protected RelativeLayout clickLayout;
    @BindView(R.id.ll_root)
    protected  LinearLayout llRoot;

    /*
    * 因为基类里有emptyview 控件引用 在所有继承基类的fragment的布局中都加上了 error2 布局控件
    * */
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.baseFragmentActivity = (BaseActivity) activity;
    }

    /**
     * 添加fragment
     * @param fragment
     */
    protected void addFragment(Fragment fragment) {
        if (null != fragment) {
            baseFragmentActivity.addFragment(fragment);
        }
    }

    /**
     * 移除fragment
     */
    protected void removeFragment() {
        baseFragmentActivity.removeFragemt();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        if (view != null) {
            doViewLogic(savedInstanceState);
        }
        LogUtils.d("onCreateView");
        return view;
    }
    public void initToolBar(Toolbar toolbar, boolean isBack, String title, int imageResourseId,
                            boolean isShow) {
        baseFragmentActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isBack);
        ((TextView) toolbar.findViewById(R.id.tv_title_include)).setText(title);
        if (imageResourseId != -1) {
            ((ImageButton) toolbar.findViewById(R.id.img_btn_action_include)).setImageResource(imageResourseId);
            if (isShow) {
                (toolbar.findViewById(R.id.img_btn_action_include)).setVisibility(View.VISIBLE);
            } else {
                (toolbar.findViewById(R.id.img_btn_action_include)).setVisibility(View.INVISIBLE);
            }
        }
        actionBar.setTitle("");
        LogUtils.d("initToolBar");
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 定义抽象方法 由继承来实现 初始化view
     * @param inflater
     * @param container
     * @return
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化view 的逻辑
     * @param savedInstanceState
     */
    public abstract void doViewLogic(Bundle savedInstanceState);


    /**
     * 显示加载窗口
     * @param msg
     */
    public void showProgressDialog(String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(baseFragmentActivity);
        progressDialog.setMessage(msg);
        try {
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 加载窗口消失
     */
    public void dismissProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing() == true) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 得到屏幕宽度
     *
     * @return 宽度
     */
    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        baseFragmentActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 得到屏幕高度
     *
     * @return 高度
     */
    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        baseFragmentActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }




}
