package com.example.qsys.yousi.fragment.idea.readpression;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
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

/**
 * @author hanshaokai
 * @date 2017/10/31 13:55
 */


public class WriteReadPressionFragment extends BaseFragment implements WriteReadPressionView {

    public AbstractWriteReadPressionPresenter mPresnter;
    @BindView(R.id.img_btn_action_include)
    ImageView imgBtnActionInclude;
    @BindView(R.id.toolbar_include)
    Toolbar toolbarInclude;
    @BindView(R.id.appbar_clude)
    AppBarLayout appbarClude;
    @BindView(R.id.et_write_title_read)
    EditText etWriteBookNameRead;
    @BindView(R.id.et_write_content_read)
    EditText etWriteContentRead;

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    public Dialog dialog;
    public long write_start_time;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_write_read_pression_idea, container, false);
        write_start_time = System.currentTimeMillis();

        return inflate;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {

        mPresnter = new WriteReadPressionPresenterExtend();
        mPresnter.setPresenterView(this);
        initToolBar();
        initListener();
    }

    private void initToolBar() {
        //不设置 图标不显示
        setHasOptionsMenu(true);
        baseFragmentActivity.setSupportActionBar(toolbarInclude);
        ActionBar actionBar = baseFragmentActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarInclude.setNavigationIcon(R.mipmap.ic_notification_hide);
        actionBar.setTitle(getResources().getString(R.string.write_read_pression));
        imgBtnActionInclude.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_default));

    }

    private void initListener() {
        etWriteContentRead.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //在指定位置之前插入
                if (s.length() == 0 && etWriteBookNameRead.getText().toString().length() == 0) {
                    imgBtnActionInclude.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_default));
                    imgBtnActionInclude.setEnabled(false);
                } else {
                    imgBtnActionInclude.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_light));
                    imgBtnActionInclude.setEnabled(true);
                }
                LogUtils.d("find", "afterTextChangedlength==" + s.length());
            }
        });
        etWriteBookNameRead.addTextChangedListener(new TextWatcher() {
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

                if (s.length() == 0 && etWriteContentRead.getText().toString().length() == 0) {
                    imgBtnActionInclude.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_default));
                    imgBtnActionInclude.setEnabled(false);
                } else {
                    imgBtnActionInclude.setImageDrawable(ContextCompat.getDrawable(baseFragmentActivity, R.mipmap.ic_send_light));
                    imgBtnActionInclude.setEnabled(true);

                }
                LogUtils.d("find", "afterTextChangedlength==" + s.length());
            }
        });
        imgBtnActionInclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresnter.postData(write_start_time,etWriteBookNameRead.getText().toString(), etWriteContentRead.getText().toString());
            }
        });
    }

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
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (
                        !etWriteContentRead.getText().toString().trim().equals("") ||
                                !etWriteBookNameRead.getText().toString().trim().equals("")
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
        mPresnter.detacheView();
    }

    public static WriteReadPressionFragment newInstance() {
        return new WriteReadPressionFragment();
    }

    @Override
    public void clearEtData() {
        write_start_time = System.currentTimeMillis();
        etWriteBookNameRead.setText("");
        etWriteContentRead.setText("");
    }
}
