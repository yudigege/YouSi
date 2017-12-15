package com.example.qsys.yousi.fragment.main.bookshelf;

import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.db.GreenDaoManager;
import com.example.qsys.yousi.net.rx.download.DownInfo;
import com.example.qsys.yousi.net.rx.exception.RetryWhenNetWorkException;
import com.example.qsys.yousi.net.rx.listener.HttpDownOnNextListener;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * @author hanshaokai
 * @date 2017/10/18 10:31
 */
public class BookShelfPresenterExtend extends AbstractBookShelfPresenter {


    @Override
    public void start() {

    }

    @Override
    void getBookData() {
        NetManager.getApiService().getAllFileName().compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                //开始和 错误回调统一再 AbstractRxSubscriber 中处理
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse bookResponse) {
                        (getBindView()).showResponseData(bookResponse);
                    }

                });
    }

    @Override
    public void toLoadBook(final String bookname) {
        LogUtils.d("上传的书籍名称" + bookname);
        NetManager.getDownLoadService().downLoadFile("", bookname)
                .compose(RxSchedulers.<ResponseBody>io_main())
                .compose(getBindView().<ResponseBody>bindToLifecycle())
                .unsubscribeOn(Schedulers.io())
                //失败后的retry配置
                .retryWhen(new RetryWhenNetWorkException()).map(new Func1<ResponseBody, DownInfo>() {
            @Override
            public DownInfo call(ResponseBody responseBody) {

                writeCaches(responseBody, bookname);

                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void toLoadCouldContinueBook(String bookname, final int positon) {
        String filePAth = Constant.BOOKPATH + File.separator + bookname;
        DownInfo downInfo = GreenDaoManager.getInstance().queryDownBy(bookname);
        if (downInfo == null) {
            downInfo = new DownInfo();
            //basurl 已经加入 所有书籍用的同一地址 根据bookname 获取对应的文本 这里保存的bookname 在service中表现为 参数
            downInfo.setUrl(bookname);
            //filePath 为手机本地保存地址
            downInfo.setSavePath(filePAth);
            LogUtils.d("不存在");
        }else {
            LogUtils.d("存在"+downInfo.toString());
        }
        downInfo.setListener(new HttpDownOnNextListener() {
            @Override
            public void onNext(Object o) {
            }
            @Override
            public void onStart() {
            }
            @Override
            public void onComplete() {

            }

            @Override
            public void updateProgress(long readLength, long countLength) {
                LogUtils.d("获取字节长度" + readLength + "\n本地的长度" + countLength);
                ((BookShelfView)getBindView()).showLoadePercent(readLength,countLength,positon);
            }
        });
        NetManager.getInstance().startDown(downInfo);

    }

    /**
     * 写入文件
     *
     * @param bookname
     * @throws IOException
     */
    public void writeCaches(ResponseBody responseBody, String bookname) {
        String textBookPath = Constant.BOOKPATH;
        InputStream inputStream = null;
        String filePath = textBookPath + File.separator + bookname;
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        inputStream = responseBody.byteStream();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }



}
