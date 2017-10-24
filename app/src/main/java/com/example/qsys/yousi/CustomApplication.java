package com.example.qsys.yousi;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.common.util.Utils;
import com.example.qsys.yousi.net.rx.base.BaseNetProvider;
import com.example.qsys.yousi.net.rx.manager.NetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanshaokai on 2017/10/9 15:02
 */

public class CustomApplication extends Application {

    /**
     * 这里定义全局变量 和方法
     * 存放活动状态的(未被销毁)的Activity列表
     */
    public static List<Activity> unDestroyActivities = new ArrayList<Activity>();
    public static CustomApplication customApplication;
    public static UserResponse.ResultsBean userEntity;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        customApplication = this;
        //在 BaseNetProvider 中实现了连接、读、写超时的时间处理，与请求和返回数据的请求头部处理。然后需要在 Application 中去注入 BaseNetProvider:
        NetManager.getInstance().registerProvider(new BaseNetProvider());
        Utils.init(this);
        initLog();

    }

    public static Context getAppContext() {
        return customApplication;
    }

    public static void initLog() {
        LogUtils.Builder builder = new LogUtils.Builder()
                // 设置log总开关，包括输出到控制台和文件，默认开
                .setLogSwitch(BuildConfig.DEBUG)
                // 设置是否输出到控制台开关，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)
                // 设置log全局标签，默认为空
                .setGlobalTag(null)
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                // 设置log头信息开关，默认为开
                .setLogHeadSwitch(true)
                // 打印log时是否存到文件的开关，默认关
                .setLog2FileSwitch(false)
                // 当自定义路径为空时，写入应用的/cache/log/目录中
                .setDir("")
                // 输出日志是否带边框开关，默认开
                .setBorderSwitch(true)
                // log的控制台过滤器，和logcat过滤器同理，默认Verbose
                .setConsoleFilter(LogUtils.V)
                // log文件过滤器，和logcat过滤器同理，默认Verbose
                .setFileFilter(LogUtils.V);
    }
}
