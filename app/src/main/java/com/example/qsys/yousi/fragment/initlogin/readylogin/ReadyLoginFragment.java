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

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.MainActivity;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.ActivityUtils;
import com.example.qsys.yousi.common.util.SPUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.dialog.AppStyleDialog;
import com.example.qsys.yousi.fragment.BaseFragment;

import butterknife.BindView;

/**
 * @author hanshaokai
 * @date 2017/10/9 17:37
 */
public class ReadyLoginFragment extends BaseFragment implements ReadyLoginView {
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
    public AbstractReadyLoginPresenter mPresenter;
    public String account;
    public AppStyleDialog appStyleDialog;
    public String password;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_ready_login
                , container, false);
        mPresenter = new ReadyLoginPresenterExtend();
        //绑定view mPresenter 持有弱引用
        mPresenter.setPresenterView(this);
        return view;
    }

    @Override
    public void doViewLogic(Bundle savedInstanceState) {
        setListener();
        checKLoginState();

    }

    private void checKLoginState() {
        //如果已登录过 直接登录
        SPUtils spUtils = SPUtils.getInstance(Constant.LOGIN_DETAIL);
        boolean isLogin = spUtils.getBoolean(Constant.LOGIN_IS_OR_NOT, false);
        if (isLogin) {
            password = spUtils.getString(Constant.PASSWORD);
            account = spUtils.getString(Constant.ACCOUNT);
            mPresenter.toLogin(account, password);
        }
    }

    private void setListener() {
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
        account = atvAccount.getText().toString();
        password = etPassword.getText().toString();
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

    /**
     * 组合参数
     *
     * @param email
     * @return
     */
    private boolean isEmailValid(String email) {
        return true;
    }

    /**
     * 校验密码
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 0;
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
                    if (loginForm != null) {
                        loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                }
            });
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (loginProgress!=null){
                    loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);}
                }
            });
        } else {
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void showResponseData(BaseResponse user) {
        switch (user.getRequstCode()) {
            case Constant.USER_LOGIN_SUCESS:
                ToastUtils.showShort(getResources().getString(R.string.login_sucess));
                //登录成功后存起来登录的信息
                CustomApplication.userEntity = ((UserResponse) user).getResults();
                //将账号密码保存到缓存
                SPUtils spUtils = SPUtils.getInstance(Constant.LOGIN_DETAIL);
                spUtils.put(Constant.PASSWORD, password);
                spUtils.put(Constant.ACCOUNT, account);
                spUtils.put(Constant.LOGIN_IS_OR_NOT, true);
                //跳转到主页
                ActivityUtils.startActivity(baseFragmentActivity, MainActivity.class);
                baseFragmentActivity.finish();
                break;
            case Constant.USER_NOT_EXIT:
                ToastUtils.showShort(getResources().getString(R.string.user_not_exit));
                    appStyleDialog = new AppStyleDialog(baseFragmentActivity, -1, account) {
                        @Override
                        public void doConfirm() {
                            super.doConfirm();
                            mPresenter.toRegister(account);
                        }
                    };
                    appStyleDialog.show();
                break;
            case Constant.USER_PASSWORD_ERRO:
                ToastUtils.showShort(getResources().getString(R.string.password_errro));
                break;
            default:
        }

    }

    @Override
    public void showMessage(String smg) {
        ToastUtils.showShort(smg);
    }

    @Override

    public void showEmptyViewByCode(int code, String smg) {


    }


    @Override
    public Boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除持有的弱引用
        mPresenter.detacheView();
    }

    @Override
    public void doView() {

    }


}
