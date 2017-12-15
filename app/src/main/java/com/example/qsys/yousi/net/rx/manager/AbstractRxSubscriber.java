package com.example.qsys.yousi.net.rx.manager;

import android.content.Context;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.common.util.NetworkUtils;
import com.example.qsys.yousi.common.util.ToastUtils;
import com.example.qsys.yousi.common.widget.LoadingDialog;
import com.example.qsys.yousi.fragment.BaseView;

import java.lang.ref.WeakReference;

import retrofit2.HttpException;
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
    }
    @Override
    public void onNext(T t) {
        getBindView().showProgressView(false);
        if (t instanceof BaseResponse) {
            //判断返回的数据在后台是否查询出错
            if (((BaseResponse) t).getErrors() != null) {
                ToastUtils.showLong((String) ((BaseResponse) t).getErrors());
                getBindView().showEmptyViewByCode(Constant.SERVER_ERROR, (String) ((BaseResponse) t).getErrors());
                LogUtils.d((String) ((BaseResponse) t).getErrors());
                getBindView().showProgressView(false);
                return;
            }
            // 返回内容 是否为空的代码 1 不为空 0 为空
            if (((BaseResponse) t).getResultsNotNull() != Constant.NOT_NULL) {
                getBindView().showEmptyViewByCode(Constant.NO_CONTENT, "");
            }
        }
        on_Next(t);
    }
    @Override
    public void onError(Throwable e) {
        getBindView().showProgressView(false);
        //网络没打开
        if (!NetworkUtils.isConnected()) {
            getBindView().showEmptyViewByCode(Constant.NET_UNABLE,"");
            getBindView().showMessage(CustomApplication.getAppContext().getString(R.string.no_net));
            return;
        }
        // 网络错误
        if (e instanceof HttpException) {
            HttpException httpEx = (HttpException) e;
            String responseMsg = httpEx.getMessage();
            getBindView().showEmptyViewByCode(Constant.SERVER_UNREACH, "");
            ToastUtils.showLong(CustomApplication.getAppContext().getResources().getString(R.string.error_server_msg, e.getMessage()));
        } else {
            // 其他错误
            ToastUtils.showLong(e.getMessage());
            LogUtils.d("非网络错误：" + e.getMessage());

        }

    }

    /**
     * onNext
     *
     * @param t
     */
    protected abstract void on_Next(T t);
}
        