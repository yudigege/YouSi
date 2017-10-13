package com.example.qsys.yousi.fragment.initlogin.readylogin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by hanshaokai on 2017/10/9 17:37
 */

public class ReadyLoginFragment extends BaseFragment {
    //采用mvp模式
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.atv_account)
    AutoCompleteTextView atvAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @BindView(R.id.login_form)
    ScrollView loginForm;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_ready_login
                , container, false);
        return view;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * 登录
     */
    private void attemptLogin() {

        // 初始
        atvAccount.setError(null);
        etPassword.setError(null);
        String account = atvAccount.getText().toString();
        String password = etPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;
        // 检查有效性
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(account)) {
            atvAccount.setError(getString(R.string.error_field_required));
            focusView = atvAccount;
            cancel = true;
        } else if (!isEmailValid(account)) {
            atvAccount.setError(getString(R.string.error_invalid_email));
            focusView = atvAccount;
            cancel = true;
        }
        if (cancel) {
            // 形成错误提示
            focusView.requestFocus();
        } else {
            mPresenter.toLogin(account, password);
        }
    }

    //校验账户名
    private boolean isEmailValid(String email) {
        return true;
    }

    //校验密码
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    /**
     * 显示登录动图隐藏登录控件
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void showProgressView(final Boolean show) {
        //显示和取消登录动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            loginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void showUserData(UserResponse user) {
        switch (user.getRequstCode()) {
            case Constant.USER_LOGIN_SUCESS:
                ToastUtils.showShort(getResources().getString(R.string.login_sucess));
                break;
            case Constant.USER_NOT_EXIT:
                ToastUtils.showShort(getResources().getString(R.string.user_not_exit));
                break;
            case Constant.USER_PASSWORD_ERRO:
                ToastUtils.showShort(getResources().getString(R.string.password_errro));
                break;
        }

    }

    @Override
    public void showMessage(String smg) {
        Toast.makeText(baseFragmentActivity, smg, Toast.LENGTH_SHORT).show();
    }


}