package com.example.qsys.yousi.net.rx.manager;

import android.content.Context;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.common.util.NetworkUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.LoadingDialog;
import com.example.qsys.yousi.net.rx.exception.ServerException;

import rx.Subscriber;

/**
 * Created by hanshaokai on 2017/10/13 10:37
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public RxSubscriber() {
        this(null, CustomApplication.getAppContext().getString(R.string.loading), true);
    }

    public RxSubscriber(Context context) {
        this(context, CustomApplication.getAppContext().getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, CustomApplication.getAppContext().getString(R.string.loading), showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        _onStart();
//6.0及6.0以上drawable indeterminateDrawable动画不起作用
       /* if (showDialog) {
            LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
        }*/
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
       /* if (showDialog)
            LoadingDialog.cancelDialogForLoading();*/
        e.printStackTrace();
        //网络
        if (!NetworkUtils.isConnected()) {
            _onError(CustomApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof ServerException) {
            _onError(e.getMessage());
            ToastUtils.showShort(CustomApplication.getAppContext().getResources().getString(R.string.error_server_msg, e.getMessage()));
        }
        //其它
        else {
            _onError(CustomApplication.getAppContext().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    protected abstract void _onStart();

}
        