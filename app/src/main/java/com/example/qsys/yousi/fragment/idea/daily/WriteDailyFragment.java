package com.example.qsys.yousi.fragment.idea.daily;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.dialog.AppStyleDialog;
import com.example.qsys.yousi.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author hanshaokai
 * @date 2017/10/27 16:23
 */


public class WriteDailyFragment extends BaseFragment implements WriteDailyView {

    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.et_write_title_daily)
    EditText etWriteTitleDaily;
    @BindView(R.id.et_write_content_daily)
    EditText etWriteContentDaily;
    @BindView(R.id.img_btn_action_include)
    ImageView img_btn_action_include;
    Unbinder unbinder;
    public WriteDailyPresenterExtend mPresenter;
    public AppStyleDialog dialog;
    public long write_start_time;
    @Override
    public void showResponseData(BaseResponse response) {
        showMessage(response.getMessage());
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
        if (b) {
            showProgressDialog("正在上传");
        } else {
            dismissProgressDialog();
        }
    }

    @Override
    public Boolean isActive() {
        return isAdded();
    }

    @Override
    public void clearEtData() {
        etWriteContentDaily.setText("");
        etWriteTitleDaily.setText("");
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_write_daily_idea, container,false);
        write_start_time = System.currentTimeMillis();
        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {

        mPresenter = new WriteDailyPresenterExtend();
        mPresenter.setPresenterView(this);
        initToolBarThis();
        initListener();

    }

    private void initListener() {

        etWriteContentDaily.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //在指定位置之前插入
                if (s.length() == 0 && etWriteTitleDaily.getText().toString().length() == 0) {
                    img_btn_action_include.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_default));
                    img_btn_action_include.setEnabled(false);
                } else {
                    img_btn_action_include.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_light));
                    /*if (!s.toString().equals(" ") && s.length() == 1) {
                        s.insert(0, "   ");
                    }*/
                    img_btn_action_include.setEnabled(true);
                }
                LogUtils.d("find", "afterTextChangedlength==" + s.length());
            }
        });
        etWriteTitleDaily.addTextChangedListener(new TextWatcher() {
            /**
             * @param s
             * @param start 代表开始变化的位置
             * @param count 代表变化的字符长度
             * @param after 代表变化后字符该位置字符数量
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LogUtils.d("find", "beforeTextChangedlength==" + s.length() + ",start==" + start + ",count==" + count + ",after==" + after);
            }

            /**
             * 在文本变化时调用
             *
             * @param s      此时s的内容已发生改变
             * @param start  start代表开始变化的位置
             * @param before before代表变化前该位置字符数量
             * @param count  count代表变化了的字符长度
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtils.d("find", "onTextChangedlength==" + s.length() + ",start==" + start + ",before==" + before + ",count==" + count);
            }

            /**
             * 在文本变化后调用
             *
             * @param s s即为变化后的文本结果
             */
            @Override
            public void afterTextChanged(Editable s) {
                //在指定位置之前插入

                if (s.length() == 0 && etWriteContentDaily.getText().toString().length() == 0) {
                    img_btn_action_include.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_default));
                    img_btn_action_include.setEnabled(false);
                } else {
                    img_btn_action_include.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_light));
                    img_btn_action_include.setEnabled(true);
                 /*   if (!s.toString().equals(" ") && s.length() == 1) {
                        s.insert(0, "   ");
                    }*/
                }
                LogUtils.d("find", "afterTextChangedlength==" + s.length());
            }
        });
        img_btn_action_include.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.postData(write_start_time,etWriteTitleDaily.getText().toString(), etWriteContentDaily.getText().toString());
            }
        });
    }

    private void initToolBarThis() {
        //不设置 图标不显示
        setHasOptionsMenu(true);
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarInclude.setNavigationIcon(R.mipmap.ic_notification_hide);
        //设置溢出图标
        //toolbarInclude.setOverflowIcon(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_launcher));
        actionBar.setTitle(getResources().getString(R.string.write_daily));
        img_btn_action_include.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_default));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (
                        !etWriteContentDaily.getText().toString().trim().equals("") ||
                                !etWriteTitleDaily.getText().toString().trim().equals("")
                        ) {
                    if (dialog == null) {
                        dialog = new AppStyleDialog(baseFragmentActivity, -1, baseFragmentActivity.getResources().getString(R.string.is_quit_edit)
                                , baseFragmentActivity.getResources().getString(R.string.content_wont_store)
                                , baseFragmentActivity.getResources().getString(R.string.quit)
                                , baseFragmentActivity.getResources().getString(R.string.continue_quit)) {
                            @Override
                            public void doConfirm() {
                                removeFragment();
                                super.doConfirm();
                            }
                        };
                        dialog.show();
                    } else {
                        dialog.show();
                    }
                } else {
                    removeFragment();
                }
                break;
            case R.id.navigation_home:
                ToastUtils.showShort("点击了溢出图标item");
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.navigation, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detacheView();
    }

    public static Fragment newInstance() {

        return new WriteDailyFragment();
    }
}
