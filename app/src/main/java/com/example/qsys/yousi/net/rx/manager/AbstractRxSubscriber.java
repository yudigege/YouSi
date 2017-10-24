package com.example.qsys.yousi.net.rx.manager;

import android.content.Context;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.common.util.NetworkUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.LoadingDialog;
import com.example.qsys.yousi.fragment.BaseView;
import com.example.qsys.yousi.net.rx.exception.ServerException;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * Created by hanshaokai on 2017/10/13 10:37
 */

public abstract class AbstractRxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;
    private WeakReference<BaseView> mWeakRefView;


    public AbstractRxSubscriber(WeakReference<BaseView> weakRefView) {
        this.mWeakRefView = weakRefView;
    }

    public BaseView getBindView() {
        return mWeakRefView.get();
    }
    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public AbstractRxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public AbstractRxSubscriber() {
        this(null, CustomApplication.getAppContext().getString(R.string.loading), true);
    }

    public AbstractRxSubscriber(Context context) {
        this(context, CustomApplication.getAppContext().getString(R.string.loading), true);
    }

    public AbstractRxSubscriber(Context context, boolean showDialog) {
        this(context, CustomApplication.getAppContext().getString(R.string.loading), showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog) {
            LoadingDialog.cancelDialogForLoading();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getBindView().showProgressView(true);
        //on_Start();
//6.0及6.0以上drawable indeterminateDrawable动画不起作用
       /* if (showDialog) {
            LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
        }*/
    }


    @Override
    public void onNext(T t) {
        on_Next(t);
    }

    @Override
    public void onError(Throwable e) {
        getBindView().showProgressView(false);
        LogUtils.d("网络错误。。。。。。。。" + e.getCause());
       /* if (showDialog)
            LoadingDialog.cancelDialogForLoading();*/
        e.printStackTrace();
        LogUtils.d(e.toString());
        //网络
        if (!NetworkUtils.isConnected()) {
            // on_Error(CustomApplication.getAppContext().getString(R.string.no_net), Constant.NET_UNABLE);
            getBindView().showEmptyViewByCode(Constant.NET_UNABLE);
            getBindView().showMessage(CustomApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof ServerException) {
            // on_Error(e.getMessage(), Constant.SERVER_ERROR);
            getBindView().showEmptyViewByCode(Constant.SERVER_ERROR);
            getBindView().showMessage(e.getMessage());

            ToastUtils.showShort(CustomApplication.getAppContext().getResources().getString(R.string.error_server_msg, e.getMessage()));


        }
        //其它
        else {
            // on_Error(CustomApplication.getAppContext().getString(R.string.net_error), Constant.UN_RECOGNICTION);
            getBindView().showEmptyViewByCode(Constant.UN_RECOGNICTION);
            getBindView().showMessage(CustomApplication.getAppContext().getString(R.string.net_error));
        }

        getBindView().showProgressView(false);
    }

    /**
     * onNext
     *
     * @param t
     */
    protected abstract void on_Next(T t);

    /**
     * onError
     *
     * @param message
     * @param requstCode
     */
    // protected abstract void on_Error(String message, int requstCode);

    /**
     * onStart
     */
    // protected abstract void on_Start();

}
        