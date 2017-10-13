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

import com.example.qsys.yousi.activity.BaseActivity;
import com.example.qsys.yousi.fragment.initlogin.readylogin.ReadyLoginContract;
import com.example.qsys.yousi.fragment.initlogin.readylogin.ReadyLoginPresenter;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hanshaokai on 2017/10/9 15:42
 */
public abstract class BaseFragment extends RxFragment implements ReadyLoginContract.View {
    /**
     * 基类 定义共公方法
     */
    public BaseActivity baseFragmentActivity;
    protected ProgressDialog progressDialog;
    public Unbinder unbinder;
    public ReadyLoginContract.Presenter mPresenter;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.baseFragmentActivity = (BaseActivity) activity;
    }

    //添加fragment
    protected void addFragment(Fragment fragment) {
        if (null != fragment) {
            baseFragmentActivity.addFragment(fragment);
        }
    }

    //移除fragment
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
        mPresenter = new ReadyLoginPresenter();

        mPresenter.setPresenterView(this);//绑定view mPresenter 持有弱引用
        unbinder = ButterKnife.bind(this, view);
        if (view != null) {
            doViewLogic(savedInstanceState);
        }

        return view;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    //定义抽象方法 由继承来实现
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    public abstract void doViewLogic(Bundle savedInstanceState);

    //初始toolbar
    public void initToolBar(Toolbar toolbar, boolean isBack, String title, int imageResourseId,
                            boolean isShow) {
        baseFragmentActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isBack);
        actionBar.setTitle("");
    }

    //显示加载窗口
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

    //加载窗口消失
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
        //解除持有的弱引用
        mPresenter.detacheView();
    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }
}
